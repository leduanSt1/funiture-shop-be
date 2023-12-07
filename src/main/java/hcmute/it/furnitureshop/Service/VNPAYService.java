package hcmute.it.furnitureshop.Service;

import hcmute.it.furnitureshop.Config.VNPAYConfig;
import hcmute.it.furnitureshop.DTO.ProductCheckOutDTO;
import hcmute.it.furnitureshop.Entity.Order;
import hcmute.it.furnitureshop.Entity.Product;
import hcmute.it.furnitureshop.Entity.User;
import hcmute.it.furnitureshop.Repository.OrderRepository;
import hcmute.it.furnitureshop.Repository.ProductRepository;
import hcmute.it.furnitureshop.Repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class VNPAYService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;
    public String ProductIds(ProductCheckOutDTO productCheckOutDTO){
        String stringReturn="";
        for(int i=0;i<productCheckOutDTO.getProductIds().size();i++){
            stringReturn+=productCheckOutDTO.getProductIds().get(i).toString();
        }
        return stringReturn;
    }
    public String getPaymentUrl(Long price,ProductCheckOutDTO productCheckOutDTO,String token) throws UnsupportedEncodingException {
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";
        long amount = price*100;
        String bankCode = "NCB";

        String vnp_TxnRef = VNPAYConfig.getRandomNumber(8);
        String vnp_IpAddr = "127.0.0.1";

        String vnp_TmnCode = VNPAYConfig.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");

        vnp_Params.put("vnp_BankCode", bankCode);
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);

        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", VNPAYConfig.vnp_ReturnUrl
                +"?productIds="+productCheckOutDTO.getProductIds().toString().replace("[","").replace("]","")
                +"&counts="+productCheckOutDTO.getCounts().toString().replace("[","").replace("]","")
                +"&nameUser="+token + "&nowDelivery="+productCheckOutDTO.getNowDelivery().toString());
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VNPAYConfig.hmacSHA512(VNPAYConfig.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VNPAYConfig.vnp_PayUrl + "?" + queryUrl;

        return paymentUrl;
    }

    public void PaymentCallBack(Map<String, String> queryParams, HttpServletResponse response) throws IOException {
        String vnp_ResponseCode = queryParams.get("vnp_ResponseCode");
        String stringProductIds = queryParams.get("productIds");
        String nameUser = queryParams.get("nameUser");
        String nowDelivery = queryParams.get("nowDelivery");
        String stringCounts = queryParams.get("counts");
        List<String> listStringProductIds = new ArrayList<String>(Arrays.asList(stringProductIds.split(",")));
        List<String> listCounts = new ArrayList<String>(Arrays.asList(stringCounts.split(",")));
        if ("00".equals(vnp_ResponseCode)) {
            for(int i=0;i<listStringProductIds.size();i++){
                Order order=new Order();
                Optional<User> user=userRepository.findByUsername(nameUser);
                Optional<Product> product= productRepository.findById(Integer.valueOf(listStringProductIds.get(i).replace(" ","")));
                if (user.isPresent() && product.isPresent())
                {
                    order.setUser(user.get());
                    order.setProduct(product.get());
                    order.setState("processing");
                    order.setDate(new Date());
                    order.setCount(Integer.parseInt(listCounts.get(i).replace(" ","")));
                    order.setPaid(true);
                    order.setNowDelivery(Boolean.valueOf(nowDelivery));
                    orderRepository.save(order);
                }else {
                    response.sendRedirect("http://localhost:3000/checkout/fail");
                }
            }
            response.sendRedirect("http://localhost:3000/checkout/success");
        } else {
            response.sendRedirect("http://localhost:3000/checkout/fail");
        }
    }
}

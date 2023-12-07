package hcmute.it.furnitureshop.Controller;

import hcmute.it.furnitureshop.Config.JwtService;
import hcmute.it.furnitureshop.DTO.*;
import hcmute.it.furnitureshop.ModelMapper.ChangeToDTOService;
import hcmute.it.furnitureshop.Service.VNPAYService;
import hcmute.it.furnitureshop.Entity.*;
import hcmute.it.furnitureshop.Service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.UnsupportedEncodingException;
import java.util.*;

@RestController
@RequestMapping("/user")
@CrossOrigin( origins = "*" , allowedHeaders = "*")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    JwtService jwtService;
    @Autowired
    FavoriteService favoriteService;
    @Autowired
    ProductService productService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    ResponseReviewService responseReviewService;
    @Autowired
    OrderService orderService;
    @Autowired
    RatingService ratingService;
    @Autowired
    VNPAYService vnpayService;
    @Autowired
    NotificationService notificationService;
    @Autowired
    ChangeToDTOService changeToDTOService;
    public String getToken(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest()
                .getHeader("Authorization")
                .replace("Bearer ","");
    }

    @RequestMapping("/findByName")
    public ResponseEntity<UserDTO> findByName(){
        return ResponseEntity.status(200).body(changeToDTOService.changeUserToDTO(userService.findByName(jwtService.extractUserName(getToken()))));
    }
    @RequestMapping("/check")
        public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello User");
    }

    @RequestMapping("/addPhone/{phone}")
    public ResponseEntity<String> savePhoneOfUser(@PathVariable("phone")String phone){
        try{
            if(userService.findByPhone(phone).isPresent()){
                return ResponseEntity.status(204).body("Không có gì");
            }
            Optional<User> user=userService.findByName(jwtService.extractUserName(getToken()));
            userService.savePhoneOfUser(user.get(),phone);
            return ResponseEntity.status(200).body("Cập nhật thành công");
        }
        catch (Exception e){
            return ResponseEntity.status(200).body("Cập nhật không thành công");

        }
    }

    @PatchMapping("/saveUser")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UpdateUserDTO userDTO){
            Optional<User> userOld=userService.findByName(jwtService.extractUserName(getToken()));
            userOld.get().setName(userDTO.getName());
            userOld.get().setApartmentNumber(userDTO.getApartmentNumber());
            userOld.get().setCity(userDTO.getCity());
            userOld.get().setDistrict(userDTO.getDistrict());
            userOld.get().setWard(userDTO.getWard());
            userService.saveUser(userOld.get());
            return ResponseEntity.status(200).body(changeToDTOService.changeUserToDTO(userService.findById(userOld.get().getUserId())));
    }
    @PatchMapping("/saveImgUser")
    public ResponseEntity<UserDTO> saveImgUser(@RequestBody UpdateUserDTO userDTO){
        Optional<User> userOld=userService.findByName(jwtService.extractUserName(getToken()));
        userOld.get().setImage(userDTO.getImage());
        return ResponseEntity.status(200).body(changeToDTOService.changeUserToDTO(userService.findById(userOld.get().getUserId())));
    }
    @GetMapping("/favoriteByUser/{userId}")
    public ResponseEntity<Iterable<FavoriteDTO>> findFavoritesByUser(@PathVariable("userId") Integer userId){
        try{
            Optional<User> user=userService.findById(userId);
            return ResponseEntity.status(200).body(changeToDTOService.changeListFavoriteToDTO(favoriteService.findByUser(user.get())));
        }catch (Exception e){
            return ResponseEntity.ok(new ArrayList<FavoriteDTO>());
        }
    }

    @PostMapping("/saveFavorite/{userId}/{productId}")
    public void saveFavorite(@PathVariable("userId")Integer userId,@PathVariable("productId")Integer productId){
        try{
            favoriteService.saveFavorite(productId,userId);
        }
        catch (Exception e){
        }
    }
    @PostMapping("/deleteFavorite/{userId}/{productId}")
    public void deleteFavorite(@PathVariable("userId")Integer userId,@PathVariable("productId")Integer productId){
        try{
            favoriteService.deleteByUserAndProduct(userId,productId);
        }
        catch (Exception e){

        }
    }
    @GetMapping("/productByFavorite/{favoriteId}")
    public ResponseEntity<ProductDTO> findProductByFavoriteId(@PathVariable("favoriteId") Integer favoriteId){
        try{
            Optional<Favorite> favorite=favoriteService.findById(favoriteId);
            return ResponseEntity.ok(changeToDTOService.changeProductToDTO(favorite.get().getProduct()));
        }catch (Exception e){
            return ResponseEntity.ok(new ProductDTO());
        }
    }

    @PostMapping("/saveReview/{userId}/{productId}")
    public void saveReview(@PathVariable("userId")Integer userId,@PathVariable("productId")Integer productId,@RequestBody String content){
        reviewService.saveReview( userId,productId,content);
    }

    @PostMapping("/deleteReview/{reviewId}")
    public void deleteReview(@PathVariable("reviewId")Integer reviewId){
        try {
            reviewService.deleteById(reviewId);
        }catch (Exception e){

        }
    }

    @PostMapping("/saveResponseReview/{userId}/{reviewId}")
    public void saveResponseReview(@PathVariable("userId")Integer userId,@PathVariable("reviewId")Integer reviewId,@RequestBody String content){
        responseReviewService.save(userId,reviewId,content);
    }

    @PostMapping("/deleteResponseReview/{responseReviewId}")
    public void deleteResponseReview(@PathVariable("responseReviewId")Integer responseReviewId){
        try {
            responseReviewService.deleteById(responseReviewId);
        }catch (Exception e){

        }
    }

    @GetMapping("/getResponseReview/{reviewId}")
    public ResponseEntity<Iterable<ResponseReviewDTO>> getResponseReview(@PathVariable("reviewId")Integer reviewId){
        Optional<Review> review=reviewService.findById(reviewId);
        return ResponseEntity.ok(changeToDTOService.changeListResponseReviewToDTO(responseReviewService.findByReview(review.get())));
    }

    @PostMapping("/saveRating/{userId}/{productId}/{point}")
    public void saveRating(@PathVariable("userId")Integer userId,@PathVariable("productId")Integer productId,@PathVariable("point")Double point){
            ratingService.save(userId,productId,point);

    }

    @GetMapping("/getRating/{userId}/{productId}")
    public ResponseEntity<RatingDTO> getRating(@PathVariable("userId")Integer userId,@PathVariable("productId")Integer productId){
            return ResponseEntity.status(200).body(changeToDTOService.changeRatingToDTO(ratingService.findByUserAndProduct(userId,productId)));
    }

    @PostMapping("/saveOrder/{productId}")
    public ResponseEntity<String> saveOrder(@RequestBody OrderRequestDTO orderRequestDTO, @PathVariable("productId")Integer productId){
        try{
            Optional<User> user=userService.findByName(jwtService.extractUserName(getToken()));
            orderService.save(user.get(),orderRequestDTO,productId);
            return  ResponseEntity.status(204).body("Đặt hàng thành công");
        }catch (Exception e){
            return ResponseEntity.status(200).body("Hết hàng");
        }


    }
    @GetMapping("/findOrdersByUser")
    public ResponseEntity<Iterable<OrderDTO>> findOrdersByUser(){
        Optional<User> user=userService.findByName(jwtService.extractUserName(getToken()));
        return ResponseEntity.ok(changeToDTOService.changeListOrderToDTO(orderService.findByUser(user.get())));
    }
    @GetMapping("/findOrdersByUserAndState/{state}")
    public ResponseEntity<Iterable<OrderDTO>> findOrdersByUserProcessing(@PathVariable("state")String state){
        return ResponseEntity.ok(changeToDTOService.changeListOrderToDTO(orderService.findOrderByUserAndState(jwtService.extractUserName(getToken()),state)));

    }

    @GetMapping("/findProductByOrderId/{orderId}")
    public ResponseEntity<ProductDTO> findProductByOrderId(@PathVariable("orderId")Integer orderId){
        return ResponseEntity.ok(changeToDTOService.changeProductToDTO(productService.findProductByOrderId(orderId).get()));
    }

    @PostMapping("/pay/{price}")
    public String getPaymentUrl(@PathVariable("price") Long price, @RequestBody ProductCheckOutDTO productCheckOutDTO) throws UnsupportedEncodingException {
        String token=jwtService.extractUserName(getToken());
        return vnpayService.getPaymentUrl(price,productCheckOutDTO,token);
    }

    @GetMapping("/getNotification/{limit}")
    public Iterable<Notification> getNotificationByUser(@PathVariable("limit")Integer limit){
        Optional<User> user=userService.findByName(jwtService.extractUserName(getToken()));
        return notificationService.findByUser(user.get(),limit);
    }

    @PostMapping("/checkNotification/{notificationId}")
    public void saveNotificationByUser(@PathVariable("notificationId")Integer notificationId){
        Optional<Notification> notification=notificationService.findById(notificationId);
        notification.get().setState(true);
        notificationService.saveNotification(notification.get());
    }
    @PostMapping("/canceledOrder/{orderId}")
    public ResponseEntity<String> canceledOrder(@PathVariable("orderId")Integer orderId){
        return ResponseEntity.ok(orderService.CancelOrder(orderId));
    }

    @PostMapping("/restoredOrder/{orderId}")
    public ResponseEntity<String> restoredOrder(@PathVariable("orderId")Integer orderId){
            return ResponseEntity.ok(orderService.RestoreOrder(orderId));
    }
    @GetMapping("/getFavoritesByProduct/{productId}")
    public ResponseEntity<Iterable<FavoriteDTO>> getFavoritesByProduct(@PathVariable("productId")Integer productId){
        Optional<User> user=userService.findByName(jwtService.extractUserName(getToken()));
        return ResponseEntity.status(200).body(changeToDTOService.changeListFavoriteToDTO(favoriteService.findByProductAndUser(productId,user.get())));
    }
}

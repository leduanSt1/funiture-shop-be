package hcmute.it.furnitureshop.Service;

import hcmute.it.furnitureshop.DTO.DataChartDTO;
import hcmute.it.furnitureshop.DTO.OrderDTO;
import hcmute.it.furnitureshop.DTO.OrderDashboardDTO;
import hcmute.it.furnitureshop.DTO.OrderRequestDTO;
import hcmute.it.furnitureshop.Entity.Order;
import hcmute.it.furnitureshop.Entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    public <S extends Order> void save(User user, OrderRequestDTO orderRequestDTO, Integer productId);
    public String CancelOrder(Integer orderId);
    public String RestoreOrder(Integer orderId);
    public Iterable<Order> findByUser(User user);
    public Optional<Order> findById(Integer orderId);
    public Iterable<Order> findOrderByUserAndState(String username,String state);
    List<OrderDTO> getAllOrder();
    String deleteOrder(Integer orderId);
    OrderDTO getById(Integer orderId);
    String UpdateOrderState(Integer orderId);
    int totalOrder();
    long totalRevenueOrder();
    ArrayList<DataChartDTO> getDataChart();
    ArrayList<OrderDashboardDTO> get10RecentOrder();
}

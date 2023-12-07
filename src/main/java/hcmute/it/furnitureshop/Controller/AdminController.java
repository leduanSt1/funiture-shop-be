package hcmute.it.furnitureshop.Controller;

import hcmute.it.furnitureshop.DTO.*;
import hcmute.it.furnitureshop.Config.JwtService;
import hcmute.it.furnitureshop.Entity.Category;
import hcmute.it.furnitureshop.Entity.Order;
import hcmute.it.furnitureshop.Entity.Product;
import hcmute.it.furnitureshop.Entity.User;
import hcmute.it.furnitureshop.Service.CategoryService;
import hcmute.it.furnitureshop.Service.OrderService;
import hcmute.it.furnitureshop.Service.ProductService;
import hcmute.it.furnitureshop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@CrossOrigin( origins = "*" , allowedHeaders = "*")
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    OrderService orderService;
    @Autowired
    JwtService jwtService;
    public String getToken(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest()
                .getHeader("Authorization")
                .replace("Bearer ","");
    }
    @RequestMapping("/findByName")
    public Optional<User> findByName(){
        return userService.findByName(jwtService.extractUserName(getToken()));

    }
    @RequestMapping("/check")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello Admin");
    }

    //CRUD User
    @RequestMapping("/getUsers")
    public List<User> getAll(){
        return userService.getAll();
    }

    @RequestMapping("/getUserById/{userId}")
    public ResponseDTO<UserDTO> getUserById(@PathVariable("userId") Integer userId){
        UserDTO userDTO = userService.getById(userId);
        if(userDTO != null){
            return new ResponseDTO<>(userDTO, "Ok", "Lấy thông tin người dùng thành công");
        }
        else{
            return new ResponseDTO<>(null, "Fail", "Không tồn tại người dùng");
        }
    }

    @PostMapping("/createUser")
    public ResponseDTO<?> createUser(@RequestBody CreateUserDTO createUserDTO){
        User user = userService.createUser(createUserDTO);
        if(user != null){
            return new ResponseDTO<>(userService.createUser(createUserDTO), "Ok", "Thêm người dùng thành công");
        }
        else{
            return new ResponseDTO<>(null, "Fail", "Thêm người dùng thất bại ! Đã tồn tại user trong hệ thống");
        }
    }

    @PostMapping("/updateUserStatus/{userId}")
    public ResponseDTO<?> updateUser(@PathVariable("userId") Integer userId){
        String message = userService.updateStatusUser(userId);
        return new ResponseDTO<>(null, "Ok", message);
    }

    @RequestMapping("/deleteUser/{userId}")
    public ResponseDTO<?> deleteUser(@PathVariable("userId") Integer userId){
        String message = userService.deleteUser(userId);
        return new ResponseDTO<>(null, "Ok", message);
    }
    //CRUD Product
    @RequestMapping("/products")
    public Iterable<ProductDetailDTO> getAllProduct(){
        return productService.getAllProductsWithCategoryName();
    }

    @RequestMapping("/getProductById/{productId}")
    public ResponseDTO<ProductDetailDTO> getProductById(@PathVariable("productId") Integer productId){
        ProductDetailDTO productDetailDTO = productService.getById(productId);
        if(productDetailDTO != null){
            return new ResponseDTO<>(productDetailDTO, "Ok", "Lấy thông tin sản phẩm thành công");
        }
        else{
            return new ResponseDTO<>(null, "Fail", "Không tồn tại sản phẩm");
        }
    }

    @PostMapping("/createProduct")
    public ResponseDTO<?> createProduct(@RequestBody ProductDetailDTO createProductDTO){
        Product product = productService.createProduct(createProductDTO);
        if(product != null){
            return new ResponseDTO<>(productService.createProduct(createProductDTO), "Ok", "Thêm sản phẩm thành công");
        }
        else{
            return new ResponseDTO<>(null, "Fail", "Thêm sản phẩm thất bại ! Đã tồn tại sản phẩm trong hệ thống");
        }
    }

    @PostMapping("/updateProduct")
    public ResponseDTO<?> updateProduct(@RequestBody ProductDetailDTO productDTO){
        String message = productService.updateProduct(productDTO);
        return new ResponseDTO<>(null, "Ok", message);
    }

    @RequestMapping("/deleteProduct/{productId}")
    public ResponseDTO<?> deleteProduct(@PathVariable("productId") Integer productId){
        String message = productService.deleteProduct(productId);
        return new ResponseDTO<>(null, "Ok", message);
    }

    ////////////////////////Order
    @RequestMapping("/categories")
    public List<CategoryDTO> getAllCategory(){
        return categoryService.getListCate();
    }

    @RequestMapping("/getCategoryById/{cateId}")
    public ResponseDTO<CategoryDTO> getCategoryById(@PathVariable("cateId") Integer cateId){
        CategoryDTO categoryDTO = categoryService.getById(cateId);
        if(categoryDTO != null){
            return new ResponseDTO<>(categoryDTO, "Ok", "Lấy thông tin sản phẩm thành công");
        }
        else{
            return new ResponseDTO<>(null, "Fail", "Không tồn tại sản phẩm");
        }
    }
    @PostMapping("/createCategory")
    public ResponseDTO<?> createCategory(@RequestBody CategoryDTO categoryDTO){
        Category category = categoryService.createCategory(categoryDTO);
        if(category != null){
            return new ResponseDTO<>(categoryService.createCategory(categoryDTO), "Ok", "Thêm loại sản phẩm thành công");
        }
        else{
            return new ResponseDTO<>(null, "Fail", "Thêm loại sản phẩm thất bại ! Đã tồn tại sản phẩm trong hệ thống");
        }
    }

    @PostMapping("/updateCategory")
    public ResponseDTO<?> updateCategory(@RequestBody CategoryDTO categoryDTO){
        String message = categoryService.updateCategory(categoryDTO);
        return new ResponseDTO<>(null, "Ok", message);
    }

    @RequestMapping("/deleteCategory/{cateId}")
    public ResponseDTO<?> deleteCategory(@PathVariable("cateId") Integer cateId){
        String message = categoryService.deleteCategory(cateId);
        return new ResponseDTO<>(null, "Ok", message);
    }

    @RequestMapping("/getCategoryList")
    public List<Object> getCategoryList()
    {
        List<Object> cateList = new ArrayList<>();
        categoryService.getAll().forEach(category -> {
            cateList.add(category.getName());
        });
        return cateList;
    }
    //////////Order
    @RequestMapping("/orders")
    public List<OrderDTO> getAllOrder(){
        return orderService.getAllOrder();
    }
    @RequestMapping("/deleteOrder/{orderId}")
    public ResponseDTO<?> deleteOrder(@PathVariable("orderId") Integer orderId){
        String message = orderService.deleteOrder(orderId);
        return new ResponseDTO<>(null, "Ok", message);
    }
    @RequestMapping("/getOrderById/{orderId}")
    public ResponseDTO<OrderDTO> getOrderById(@PathVariable("orderId") Integer orderId){
        OrderDTO orderDTO = orderService.getById(orderId);
        if(orderDTO != null){
            return new ResponseDTO<>(orderDTO, "Ok", "Lấy thông tin đơn hàng thành công");
        }
        else{
            return new ResponseDTO<>(null, "Fail", "Không tồn tại đơn hàng");
        }
    }
    @PostMapping("/updateOrderState/{orderId}")
    public ResponseDTO<OrderDTO> updateOrderState(@PathVariable("orderId") Integer orderId){
        String message = orderService.UpdateOrderState(orderId);
        if(message != null){
            return new ResponseDTO<>(null, "Ok", message);
        }
        else{
            return new ResponseDTO<>(null, "Fail", "Không tồn tại đơn hàng");
        }
    }
    @RequestMapping("/dataCardDashboard")
    public ResponseDTO<DataCardDashboard> getDataForCardDashboard()
    {
        int totalNewUser = userService.getTotalNewUser();
        int totalOrder = orderService.totalOrder();
        long totalRevenue = orderService.totalRevenueOrder();
        DataCardDashboard dataCardDashboard = DataCardDashboard
                .builder().totalNewUser(totalNewUser).totalRevenue(totalRevenue).totalProductSold(totalOrder)
                .build();
        if(dataCardDashboard != null){
            return new ResponseDTO<>(dataCardDashboard, "Ok", "Lấy dữ liệu thành công");
        }
        else{
            return new ResponseDTO<>(dataCardDashboard, "Fail", "Không thành công");
        }
    }
    @RequestMapping("/dataChart")
    public ResponseDTO<ArrayList<DataChartDTO>> getDataLineChart()
    {

        ArrayList<DataChartDTO> dataChartDTOS = orderService.getDataChart();
        if(dataChartDTOS != null){
            return new ResponseDTO<>(dataChartDTOS, "Ok", "Lấy dữ liệu thành công");
        }
        else{
            return new ResponseDTO<>(dataChartDTOS, "Fail", "Không thành công");
        }
    }
    @RequestMapping("/10RecentOrder")
    public ResponseDTO<?> get10RecentOrder()
    {
        ArrayList<OrderDashboardDTO> orderDTOs = orderService.get10RecentOrder();
        if(orderDTOs != null){
            return new ResponseDTO<>(orderDTOs, "Ok", "Lấy dữ liệu thành công");
        }
        else{
            return new ResponseDTO<>(null, "Fail", "Thất bại");
        }
    }
    @RequestMapping("/top3BestUser")
    public ResponseDTO<?> top3BestUser()
    {
        ArrayList<BestUser> bestUsers = userService.getTop3User();
        if(bestUsers != null){
            return new ResponseDTO<>(bestUsers, "Ok", "Lấy thông tin thành công");
        }
        else{
            return new ResponseDTO<>(bestUsers, "Fail", "Thất bại");
        }
    }

}

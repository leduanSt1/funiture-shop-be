package hcmute.it.furnitureshop.Controller;

import hcmute.it.furnitureshop.DTO.*;
import hcmute.it.furnitureshop.ModelMapper.ChangeToDTOService;
import hcmute.it.furnitureshop.Service.VNPAYService;
import hcmute.it.furnitureshop.Entity.*;
import hcmute.it.furnitureshop.Service.*;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/guest")
@CrossOrigin( origins = "*" , allowedHeaders = "*")
public class GuestController {
    @Autowired
    RoomService roomService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Autowired
    ReviewService reviewService;
    @Autowired
    VNPAYService vnpayService;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ChangeToDTOService changeToDTOService;
    @Autowired
    FavoriteService favoriteService;
    @GetMapping("/room")
    public ResponseEntity<Iterable<Room>> getAllRoom(){
        Iterable<Room> rooms=roomService.getAll();
        return ResponseEntity.status(200).body(rooms);
    }
    @GetMapping("/products")
    public ResponseEntity<Iterable<Product>> getAllProduct(){
        Iterable<Product> rooms=productService.getAll();
        return ResponseEntity.status(200).body(rooms);
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<Optional<Room>> getRoomById(@PathVariable("roomId")Integer roomId){
        Optional<Room> room= roomService.getById(roomId);
        return ResponseEntity.status(200).body(room);
    }
    @RequestMapping("/room/categories/{id}")
    public ResponseEntity<Iterable<CategoryDTO>> getCategoriesByRoom(@PathVariable("id") Integer roomId){
        Iterable<Category> categories=categoryService.getCategoriesByRoom(roomId);
        return ResponseEntity.status(200).body(changeToDTOService.changeListCategoryToDTO(categories));
    }
    @RequestMapping("/room/products/{id}")
    public ResponseEntity<Iterable<ProductDTO>> getProductsByRoom(@PathVariable("id") Integer roomId){
        return ResponseEntity.status(200).body(changeToDTOService.changeListProductToDTO(productService.getProductByRoom(roomId)));
    }
    @RequestMapping("/category")
    public ResponseEntity<Iterable<CategoryDTO>> getAllCategory(){

        return ResponseEntity.status(200).body(changeToDTOService.changeListCategoryToDTO(categoryService.getAll()));
    }
    @RequestMapping("/product/top8Product")
    public ResponseEntity<Iterable<ProductDTO>> getTop8Product(){
        return ResponseEntity.status(200).body(changeToDTOService.changeListProductToDTO(productService.getTop8Product()));
    }
    @RequestMapping("/product/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("productId") Integer productId){
        return ResponseEntity.status(200).body(changeToDTOService.changeProductToDTO(productService.getProductById(productId).get()));
    }

    @RequestMapping("/product/containing/{name}")
    public ResponseEntity<Iterable<ProductDTO>> getProductByNameContaining(@PathVariable("name")String name){
        return ResponseEntity.status(200).body(changeToDTOService.changeListProductToDTO(productService.getProductByNameContaining(name)));
    }

    @RequestMapping("/productsByCategory/{categoryId}")
    public ResponseEntity<Iterable<ProductDTO>> getProductsByCategory(@PathVariable("categoryId")Integer categoryId){

        return ResponseEntity.status(200).body(changeToDTOService.changeListProductToDTO(productService.getProductsByCategory(categoryId)));
    }
    @RequestMapping("/productsByCategoryDesc/{categoryId}")
    public ResponseEntity<Iterable<ProductDTO>> getProductsByCategoryAndPriceDesc(@PathVariable("categoryId")Integer categoryId){
        Optional<Category> category=categoryService.findById(categoryId);
        return ResponseEntity.status(200).body(changeToDTOService.changeListProductToDTO(productService.getProductByCategoryAndPriceDesc(category.get())));
    }
    @RequestMapping("/productsByCategoryAsc/{categoryId}")
    public ResponseEntity<Iterable<ProductDTO>> getProductsByCategoryAndPriceAsc(@PathVariable("categoryId")Integer categoryId){
        Optional<Category> category=categoryService.findById(categoryId);
        return ResponseEntity.status(200).body(changeToDTOService.changeListProductToDTO(productService.getProductByCategoryAndPriceAsc(category.get())));
    }
    @RequestMapping("/productsByCategoryOrderDiscount/{categoryId}")
    public ResponseEntity<Iterable<ProductDTO>> getProductsByCategoryAndDiscount(@PathVariable("categoryId")Integer categoryId){
        return ResponseEntity.status(200).body(changeToDTOService.changeListProductToDTO(productService.getProductsByCategoryAndDiscount(categoryId)));
    }
    @RequestMapping("/getCategory/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("categoryId")Integer categoryId){
        return ResponseEntity.status(200).body(changeToDTOService.changeCategoryToDTO(categoryService.findById(categoryId).get()));
    }

    @RequestMapping("/ProductDescByRoom/{roomId}")
    public ResponseEntity<Iterable<ProductDTO>> getProductDescByRoom(@PathVariable("roomId") Integer roomId){
        return ResponseEntity.status(200).body(changeToDTOService.changeListProductToDTO(productService.findProductByRoomDesc(roomId)));
    }
    @RequestMapping("/ProductAscByRoom/{roomId}")
    public ResponseEntity<Iterable<ProductDTO>> getProductAscByRoom(@PathVariable("roomId") Integer roomId){
        return ResponseEntity.status(200).body(changeToDTOService.changeListProductToDTO(productService.findProductByRoomAsc(roomId)));
    }
    @RequestMapping("/ProductSaleByRoom/{roomId}")
    public ResponseEntity<Iterable<ProductDTO>> getProductSaleByRoom(@PathVariable("roomId") Integer roomId){
       return ResponseEntity.status(200).body(changeToDTOService.changeListProductToDTO(productService.getProductSaleByRoom(roomId)));
    }

    @RequestMapping("/checkPhone/{phone}")
    public boolean checkPhone(@PathVariable("phone")String phone){
        if(userService.findByName(phone).isPresent()){
            return true;
        }else{
            return false;
        }
    }
    @GetMapping("/reviewByProduct/{productId}")
    public ResponseEntity<Iterable<ReviewDTO>> findReviewsByProduct(@PathVariable("productId")Integer productId){
        Optional<Product> product=productService.findById(productId);
        return ResponseEntity.status(200).body(changeToDTOService.changeListReviewToDTO(reviewService.findByProduct(product.get())));
    }

    @GetMapping("/payment-callback")
    public void paymentCallback(@RequestParam Map<String, String> queryParams, HttpServletResponse response) throws IOException {
       vnpayService.PaymentCallBack(queryParams,response);
    }

    @GetMapping("/getProductByDiscountNotNull")
    public ResponseEntity<Iterable<ProductDTO>> getProductByDiscountIsNotNull(){
        return ResponseEntity.ok(changeToDTOService.changeListProductToDTO(productService.findByDiscountIsNotNull()));
    }
    @GetMapping("/getProductNearProduct/{productId}")
    public ResponseEntity<Iterable<ProductDTO>> getProductNearProduct(@PathVariable("productId")Integer productId){
        return ResponseEntity.ok(changeToDTOService.changeListProductToDTO(productService.findProductNearProduct(productId)));
    }
}

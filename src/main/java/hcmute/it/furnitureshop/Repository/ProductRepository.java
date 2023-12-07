package hcmute.it.furnitureshop.Repository;

import hcmute.it.furnitureshop.Entity.Category;
import hcmute.it.furnitureshop.Entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product,Integer> {

    @Transactional
    @Query(value="SELECT * FROM springserverdb.product ORDER BY number_product_sold desc limit 10", nativeQuery = true)
    Iterable<Product> findTop8ByProductSold();
    Iterable<Product> findByDiscountIsNotNull();
    Iterable<Product> findProductsByCategory_Room_RoomIdOrderByPriceDesc(Integer roomId);
    Iterable<Product> findProductsByCategory_Room_RoomIdOrderByPriceAsc(Integer roomId);
    Iterable<Product> findProductsByCategory_Room_RoomId(Integer roomId);
    @Transactional
    Iterable<Product> findProductsByNameContaining(String name);
    @Transactional
    Iterable<Product> findProductsByCategory(Category category);
    @Transactional
    Iterable<Product> findProductsByCategoryOrderByPriceDesc(Category category);
    @Transactional
    Iterable<Product> findProductsByCategoryOrderByPriceAsc(Category category);
    @Override
    Optional<Product> findById(Integer integer);

    Iterable<Product> findProductsByStatus(String status);

    Optional<Product> findProductByOrder_OrderId(Integer orderId);

    @Query(value="SELECT springserverdb.product.* FROM springserverdb.product " +
            "JOIN springserverdb.category " +
            "ON springserverdb.product.category_id = springserverdb.category.category_id " +
            "WHERE springserverdb.product.category_id = springserverdb.category.category_id;", nativeQuery = true)
    Iterable<Product> findAllProductsWithCategoryName();

    Optional<Product> findByName(String productName);
}

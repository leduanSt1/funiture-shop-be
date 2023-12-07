package hcmute.it.furnitureshop.Repository;

import hcmute.it.furnitureshop.Entity.Category;
import hcmute.it.furnitureshop.Entity.Product;
import hcmute.it.furnitureshop.Entity.Room;
import jakarta.transaction.Transactional;
import org.springframework.boot.autoconfigure.quartz.QuartzTransactionManager;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Integer> {
    @Transactional
    public Iterable<Category> findCategoriesByRoom(Optional<Room> room);

    @Override
    @Transactional
    Iterable<Category> findAll();

    @Override
    @Transactional
    Optional<Category> findById(Integer integer);

    Category findByName(String categoryName);

    Optional<Category> findCategoryByProduct(Product product);
}

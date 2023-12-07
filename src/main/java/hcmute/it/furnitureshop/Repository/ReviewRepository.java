package hcmute.it.furnitureshop.Repository;

import hcmute.it.furnitureshop.Entity.Product;
import hcmute.it.furnitureshop.Entity.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer> {
    public Iterable<Review> findReviewsByProductOrderByDateDesc(Product product);

    @Override
    void deleteById(Integer integer);
}

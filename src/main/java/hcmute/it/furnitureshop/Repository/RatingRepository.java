package hcmute.it.furnitureshop.Repository;

import hcmute.it.furnitureshop.Entity.Product;
import hcmute.it.furnitureshop.Entity.Rating;
import hcmute.it.furnitureshop.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends CrudRepository<Rating,Integer> {
    public Optional<Rating> findRatingByUserAndProduct(User user, Product product);
}

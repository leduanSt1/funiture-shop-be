package hcmute.it.furnitureshop.Service;

import hcmute.it.furnitureshop.Entity.Product;
import hcmute.it.furnitureshop.Entity.Review;

import java.util.Optional;

public interface ReviewService {
    public <S extends Review> void saveReview(Integer userId,Integer productId,String content);

    public Iterable<Review> findByProduct(Product product);

    public void deleteById(Integer reviewId);

    public Optional<Review> findById(Integer reviewId);
}

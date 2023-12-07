package hcmute.it.furnitureshop.Repository;

import hcmute.it.furnitureshop.Entity.ResponseReview;
import hcmute.it.furnitureshop.Entity.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseReviewRepository extends CrudRepository<ResponseReview,Integer> {
    public Iterable<ResponseReview> findResponseReviewsByReview(Review review);

    @Override
    void deleteById(Integer integer);
}

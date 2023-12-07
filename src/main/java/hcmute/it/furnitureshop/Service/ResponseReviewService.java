package hcmute.it.furnitureshop.Service;

import hcmute.it.furnitureshop.Entity.ResponseReview;
import hcmute.it.furnitureshop.Entity.Review;

public interface ResponseReviewService {
    public Iterable<ResponseReview> findByReview(Review review);

    public void deleteById(Integer responseReviewId);

    public <S extends  ResponseReview> void save(Integer userId,Integer reviewId,String content);
}

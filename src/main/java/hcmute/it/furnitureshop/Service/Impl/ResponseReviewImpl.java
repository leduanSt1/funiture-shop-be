package hcmute.it.furnitureshop.Service.Impl;

import hcmute.it.furnitureshop.Entity.ResponseReview;
import hcmute.it.furnitureshop.Entity.Review;
import hcmute.it.furnitureshop.Entity.User;
import hcmute.it.furnitureshop.Repository.ResponseReviewRepository;
import hcmute.it.furnitureshop.Repository.ReviewRepository;
import hcmute.it.furnitureshop.Repository.UserRepository;
import hcmute.it.furnitureshop.Service.ResponseReviewService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ResponseReviewImpl implements ResponseReviewService {
    @Autowired
    ResponseReviewRepository responseReviewRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Override
    public Iterable<ResponseReview> findByReview(Review review) {
        return responseReviewRepository.findResponseReviewsByReview(review);
    }

    @Override
    public void deleteById(Integer responseReviewId) {
        responseReviewRepository.deleteById(responseReviewId);
    }

    @Override
    public <S extends ResponseReview> void save(Integer userId,Integer reviewId,String content) {
        Optional<Review> review=reviewRepository.findById(reviewId);
        Optional<User> user=userRepository.findById(userId);
        ResponseReview responseReview=new ResponseReview();
        responseReview.setReview(review.get());
        responseReview.setContent(content);
        responseReview.setDate(new Date());
        responseReview.setUser(user.get());
        responseReviewRepository.save(responseReview);
    }
}

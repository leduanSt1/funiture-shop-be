package hcmute.it.furnitureshop.Service.Impl;

import hcmute.it.furnitureshop.Entity.Product;
import hcmute.it.furnitureshop.Entity.Rating;
import hcmute.it.furnitureshop.Entity.User;
import hcmute.it.furnitureshop.Repository.ProductRepository;
import hcmute.it.furnitureshop.Repository.RatingRepository;
import hcmute.it.furnitureshop.Repository.UserRepository;
import hcmute.it.furnitureshop.Service.RatingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RatingServiceImpl implements RatingService{
    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public <S extends Rating> void save(Integer userId,Integer productId,Double point) {
        Optional<User> user=userRepository.findById(userId);
        Optional<Product> product=productRepository.findById(productId);
        if(!findByUserAndProduct(userId,productId).isPresent()){
            Rating rating=new Rating();
            rating.setPoint(point);
            rating.setUser(user.get());
            rating.setProduct(product.get());
            ratingRepository.save(rating);
        }else{
            Optional<Rating> rating=findByUserAndProduct(userId,productId);
            rating.get().setPoint(point);
            ratingRepository.save(rating.get());
        }

    }

    @Override
    public Optional<Rating> findByUserAndProduct(Integer userId,Integer productId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Product> product = productRepository.findById(productId);
        return ratingRepository.findRatingByUserAndProduct(user.get(),product.get());
    }
}

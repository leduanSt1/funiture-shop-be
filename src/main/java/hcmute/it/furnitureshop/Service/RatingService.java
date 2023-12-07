package hcmute.it.furnitureshop.Service;

import hcmute.it.furnitureshop.Entity.Product;
import hcmute.it.furnitureshop.Entity.Rating;
import hcmute.it.furnitureshop.Entity.User;

import java.util.Optional;

public interface RatingService {
    public <S extends Rating> void save(Integer userId,Integer productId,Double point);

    public Optional<Rating> findByUserAndProduct (Integer userId,Integer productId);
}

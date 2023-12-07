package hcmute.it.furnitureshop.Service.Impl;

import hcmute.it.furnitureshop.Entity.Favorite;
import hcmute.it.furnitureshop.Entity.Product;
import hcmute.it.furnitureshop.Entity.User;
import hcmute.it.furnitureshop.Repository.FavoriteRepository;
import hcmute.it.furnitureshop.Repository.ProductRepository;
import hcmute.it.furnitureshop.Repository.UserRepository;
import hcmute.it.furnitureshop.Service.FavoriteService;
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
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    FavoriteRepository favoriteRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public Iterable<Favorite> findByUser(User user) {
        return favoriteRepository.findByUser(user);
    }

    @Override
    public Iterable<Favorite> findByProductAndUser(Integer productId,User user) {
        Optional<Product> product= productRepository.findById(productId);
        return favoriteRepository.findByProductAndUser(product.get(),user);
    }

    @Override
    public <S extends Favorite> void saveFavorite(Integer productId,Integer userId) {
        Optional<User> user=userRepository.findById(userId);
        Optional<Product> product=productRepository.findById(productId);
        Favorite favorite=new Favorite();
        favorite.setUser(user.get());
        favorite.setProduct(product.get());
        favoriteRepository.save(favorite);
    }

    @Override
    public void deleteByUserAndProduct(Integer userId,Integer productId) {
        Optional<User> user=userRepository.findById(userId);
        Optional<Product> product=productRepository.findById(productId);
        favoriteRepository.deleteByUserAndProduct(user.get(),product.get());
    }

    @Override
    public Optional<Favorite> findById(Integer favoriteId) {
        return favoriteRepository.findById(favoriteId);
    }


}

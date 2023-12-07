package hcmute.it.furnitureshop.Service;

import hcmute.it.furnitureshop.Entity.Favorite;
import hcmute.it.furnitureshop.Entity.Product;
import hcmute.it.furnitureshop.Entity.User;

import java.util.Optional;

public interface FavoriteService {
    public Iterable<Favorite> findByUser(User user);
    public Iterable<Favorite> findByProductAndUser(Integer productId,User user);

    public <S extends Favorite> void saveFavorite(Integer productId,Integer userId);
    void deleteByUserAndProduct(Integer userId,Integer productId);

    public Optional<Favorite> findById(Integer favoriteId);
}

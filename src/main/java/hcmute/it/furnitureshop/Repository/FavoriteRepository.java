package hcmute.it.furnitureshop.Repository;

import hcmute.it.furnitureshop.Entity.Favorite;
import hcmute.it.furnitureshop.Entity.Product;
import hcmute.it.furnitureshop.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoriteRepository extends CrudRepository<Favorite,Integer> {
    Iterable<Favorite> findByUser(User user);
    Iterable<Favorite> findByProductAndUser(Product product,User user);
    @Override
    <S extends Favorite> S save(S entity);
    void deleteByUserAndProduct(User user,Product product);


}

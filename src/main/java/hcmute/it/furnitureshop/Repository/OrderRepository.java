package hcmute.it.furnitureshop.Repository;

import hcmute.it.furnitureshop.Entity.Order;
import hcmute.it.furnitureshop.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order,Integer> {
    public Iterable<Order> findOrdersByUserOrderByDateDesc(User user);
}

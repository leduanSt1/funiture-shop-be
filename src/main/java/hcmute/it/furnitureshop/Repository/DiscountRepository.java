package hcmute.it.furnitureshop.Repository;

import hcmute.it.furnitureshop.Entity.Discount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountRepository extends CrudRepository<Discount,Integer> {
    Optional<Discount> findByPercentDiscount(Double percentDiscount);
}

package hcmute.it.furnitureshop.Repository;

import hcmute.it.furnitureshop.Entity.Category;
import hcmute.it.furnitureshop.Entity.Room;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface RoomRepository extends CrudRepository<Room, Integer> {
    @Override
    @Transactional
    Iterable<Room> findAll();

    @Override
    @Transactional
    Optional<Room> findById(Integer integer);

}

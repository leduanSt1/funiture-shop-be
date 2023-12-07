package hcmute.it.furnitureshop.Repository;

import hcmute.it.furnitureshop.Entity.User;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Override
    @Transactional
    List<User> findAll();

    Optional<User> findByUsername(String username);

    @Override
    <S extends User> S save(S entity);

    Optional<User> findByPhone(String phone);

    Optional<User> findById(Integer integer);
}

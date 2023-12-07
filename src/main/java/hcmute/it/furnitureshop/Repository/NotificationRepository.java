package hcmute.it.furnitureshop.Repository;

import hcmute.it.furnitureshop.Entity.Notification;
import hcmute.it.furnitureshop.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NotificationRepository extends CrudRepository<Notification,Integer> {
    public Iterable<Notification> findNotificationsByUserOrderByDateDesc(User user);
}

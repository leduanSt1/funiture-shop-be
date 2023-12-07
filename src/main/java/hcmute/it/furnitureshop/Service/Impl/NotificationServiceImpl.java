package hcmute.it.furnitureshop.Service.Impl;

import hcmute.it.furnitureshop.Entity.Notification;
import hcmute.it.furnitureshop.Entity.User;
import hcmute.it.furnitureshop.Repository.NotificationRepository;
import hcmute.it.furnitureshop.Service.NotificationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public Iterable<Notification> findByUser(User user,Integer limit) {
        Iterable<Notification> notifications=notificationRepository.findNotificationsByUserOrderByDateDesc(user);
        AtomicInteger index= new AtomicInteger();
        ArrayList<Notification> notificationArrayList=new ArrayList<>();
        notifications.forEach(notification -> {
            if(index.get() <limit){
                notificationArrayList.add(notification);
            }
            index.addAndGet(1);
        });
        return notificationArrayList;
    }

    @Override
    public <S extends Notification> void saveNotification(Notification notification) {
        notificationRepository.save(notification);
    }

    @Override
    public Optional<Notification> findById(Integer notificationId) {
        return notificationRepository.findById(notificationId);
    }
}

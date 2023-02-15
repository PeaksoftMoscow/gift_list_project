package com.peaksoft.repository;


import com.peaksoft.model.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long > {

    @Query("select n from Notification n join  n.receivers r where r.id=?1")
    List<Notification> getAllNotificationByUserId(Long id);

    @Query("select n from Notification n join n.receivers r where n.receivers=?1")
    Notification getNotificationByUserId(Long id);
    @Query("select n from Notification n join n.receivers r where n.isRead= true and r.id=?1")
    List<Notification> getAllIsReadNotification(Long id);

    @Query("select n from Notification n join n.receivers r where n.isRead=false and r.id=?1")
    List<Notification> getAllUnReadNotification(Long id);
}

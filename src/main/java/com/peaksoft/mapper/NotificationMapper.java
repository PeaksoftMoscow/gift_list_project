package com.peaksoft.mapper;

import com.peaksoft.dto.NotificationResponse;
import com.peaksoft.exception.UserNotFoundException;
import com.peaksoft.model.entity.Notification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class NotificationMapper {

    public NotificationResponse notificationResponse(Notification notification){
        if(notification == null){
            throw new UserNotFoundException("Not found notification user email");
        }
        NotificationResponse notificationResponse = new NotificationResponse();
        notificationResponse.setId(notification.getId());
        notificationResponse.setFirstName(notification.getUser().getFirstName());
        notificationResponse.setLastName(notification.getUser().getLastName());
        notificationResponse.setGiftId(notification.getGiftId());
        notificationResponse.setGiftName(notification.getGiftName());
        notificationResponse.setNotificationStatus(notification.getNotificationStatus());
        notificationResponse.setCreatedAd(LocalDate.now());
        notificationResponse.setRead(notification.isRead());
        return notificationResponse;
    }

}

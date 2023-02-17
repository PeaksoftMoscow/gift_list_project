package com.peaksoft.service;

import com.peaksoft.dto.NotificationResponse;
import com.peaksoft.exception.NotificationNotFoundException;
import com.peaksoft.mapper.NotificationMapper;
import com.peaksoft.model.User;
import com.peaksoft.model.entity.Notification;
import com.peaksoft.model.entity.enums.NotificationStatus;
import com.peaksoft.repository.NotificationRepository;
import com.peaksoft.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final UserRepository userRepository;

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    public List<NotificationResponse> getAllNotification() {
        User user = getAuthenticationUser();
        List<Notification> notifications = notificationRepository.getAllNotificationByUserId(user.getId());
        if (notifications.isEmpty()){
            throw  new NotFoundException("Notifications not found");
        }
        notifications.stream().forEach(x -> x.setRead(true) );
        notificationRepository.saveAll(notifications);
        return view(notifications);

    }

    private List<NotificationResponse> view(List<Notification> notifications) {
        List<NotificationResponse> responses = new ArrayList<>();
        for (Notification notification : notifications){
            responses.add(notificationMapper.notificationResponse(notification));
        }
        return responses;
    }

    public User getAuthenticationUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        return userRepository.findByEmail(login).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public List<NotificationResponse> getAllIsReadNotification() {
        User user = getAuthenticationUser();
        List<Notification> notifications  = notificationRepository.getAllIsReadNotification(user.getId());
        if (notifications.isEmpty()){
            throw new NotificationNotFoundException("Read Notification not found");
        }
        return view(notifications);

    }

    public List<NotificationResponse> getAllUnReadNotification() {
        User user = getAuthenticationUser();
        List<Notification> notifications = notificationRepository.getAllUnReadNotification(user.getId());
        if (notifications.isEmpty()){
            throw  new NotificationNotFoundException(" Read Notification not found");
        }
        return view(notifications);
    }

    public NotificationResponse getNotificationByUserId(Long id){
        User user = getAuthenticationUser();
        Notification notification = notificationRepository.getNotificationByUserId(user.getId());
        if (notification == null){
            throw new NotificationNotFoundException("Notification is not found with id"+ id);
        }
        notification.setRead(true);
        notificationRepository.save(notification);
        return notificationMapper.notificationResponse(notification);
    }

    public Notification sendNotification (User user, List<User> receivers){
        Notification notification = new Notification();
        notification.setCreatedAt(LocalDate.now());
        notification.setUser(notification.getUser());
        notification.setReceivers(receivers);
        notification.setNotificationStatus(NotificationStatus.REQUEST_TO_FRIEND);
        return notification;
    }
    public Notification acceptToNotification (User user, List<User> receivers){
        Notification notification = new Notification();
        notification.setCreatedAt(LocalDate.now());
        notification.setUser(notification.getUser());
        notification.setReceivers(receivers);
        notification.setNotificationStatus(NotificationStatus.ACCEPT_YOUR_REQUEST);
        return notification;
    }


    public String deleteAllNotification() {
        User user = getAuthenticationUser();
        List<Notification> notifications = notificationRepository.getAllNotificationByUserId(user.getId());
        if (notifications.isEmpty()){
            throw new NotificationNotFoundException("not found Notification");
        }
        for (Notification notification : notifications){
            notification.deleteUser(user);
        }
        notificationRepository.saveAll(notifications);
        return "Successfully deleted all Notifications";
    }
}

package com.peaksoft.controller;

import com.peaksoft.dto.NotificationResponse;
import com.peaksoft.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("all")
    public List<NotificationResponse> getAllNotification() {
        return notificationService.getAllNotification();
    }

    @GetMapping("is_Read")
    public List<NotificationResponse> getAllIsReadNotification() {
        return notificationService.getAllIsReadNotification();
    }

    @GetMapping("un_Read")
    public List<NotificationResponse> getAllUnReadNotification(){
        return notificationService.getAllUnReadNotification();
    }

    @GetMapping("id")
    public NotificationResponse getNotificationById(@PathVariable Long id){
        return notificationService.getNotificationByUserId(id);
    }

    public String deleteAllNotification(){
        return notificationService.deleteAllNotification();
    }
}

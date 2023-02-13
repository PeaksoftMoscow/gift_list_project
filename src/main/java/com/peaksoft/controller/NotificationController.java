package com.peaksoft.controller;

import com.peaksoft.dto.NotificationResponse;
import com.peaksoft.repository.NotificationRepository;
import com.peaksoft.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public List<NotificationResponse> getAllNotification(){
        return notificationService.getAllNotification();
    }
}

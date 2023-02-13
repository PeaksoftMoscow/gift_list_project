package com.peaksoft.service;

import com.peaksoft.dto.NotificationResponse;
import com.peaksoft.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public List<NotificationResponse> getAllNotification() {


        return

    }
}

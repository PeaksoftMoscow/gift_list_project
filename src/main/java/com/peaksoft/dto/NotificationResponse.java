package com.peaksoft.dto;

import com.peaksoft.model.entity.enums.NotificationStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class NotificationResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private NotificationStatus notificationStatus;
    private Long giftId;
    private String giftName;
    private LocalDate createdAd;
    private boolean isRead;
}

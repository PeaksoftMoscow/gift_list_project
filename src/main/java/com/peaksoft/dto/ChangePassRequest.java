package com.peaksoft.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePassRequest {
    private String oldPassword;
    private String newPassword;
}

package com.peaksoft.model.entity.enums;

import com.peaksoft.model.User;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

public enum RoleE implements GrantedAuthority {
    ADMIN,
    USER;


    @Override
    public String getAuthority() {
        return name();
    }
}

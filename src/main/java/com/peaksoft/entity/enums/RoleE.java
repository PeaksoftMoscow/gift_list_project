package com.peaksoft.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum RoleE implements GrantedAuthority {
    ADMIN,
    USER;


    @Override
    public String getAuthority() {
        return name();
    }
}

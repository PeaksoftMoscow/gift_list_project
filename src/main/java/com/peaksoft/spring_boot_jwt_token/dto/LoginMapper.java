package com.peaksoft.spring_boot_jwt_token.dto;

import com.peaksoft.model.User;

import com.peaksoft.model.entity.enums.RoleE;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class LoginMapper {

    public LoginResponse loginView(String token, String message, User user) {
        var loginResponse = new LoginResponse();
        if (user != null) {
            try {
                setAuthority(loginResponse, Collections.singletonList(user.getRoleES()));
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
            loginResponse.setJwtToken(token);
            loginResponse.setMessage(message);
        }
        return loginResponse;
    }

    private void setAuthority(LoginResponse loginResponse, List<RoleE> roles) {
        Set<String> authorities = new HashSet<>();
        for (RoleE role : roles) {
            if (role != null) {
                authorities.add(role.getAuthority());
            } else throw new RuntimeException("");
        }
        String join = String.join("", authorities);
        loginResponse.setAuthorities(join);
    }
}

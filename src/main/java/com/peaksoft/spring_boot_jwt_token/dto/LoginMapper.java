package com.peaksoft.spring_boot_jwt_token.dto;

import com.peaksoft.model.User;
import com.peaksoft.model.entity.enums.Role;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class LoginMapper {

    public LoginResponse loginView(String token,String message, User user){
        var loginResponse = new LoginResponse();
        if(user != null){
            setAutority(loginResponse, user.getRoles());
        }
        loginResponse.setJwtToken(token);
        loginResponse.setMessage(message);
        return loginResponse;
    }

    private void setAutority(LoginResponse loginResponse, List<Role> roles){
        Set<String> authoriries = new HashSet<>();
        for(Role role: roles){
            authoriries.add(role.getRoleName());
        }
        loginResponse.setAuthorities(authoriries);
    }
}

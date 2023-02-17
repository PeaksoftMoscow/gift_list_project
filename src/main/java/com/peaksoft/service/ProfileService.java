package com.peaksoft.service;

import com.peaksoft.config.jwt.JwTokenUtil;
import com.peaksoft.dto.ProfileRequest;
import com.peaksoft.dto.ProfileResponse;
import com.peaksoft.mapper.ProfileMapper;
import com.peaksoft.model.entity.User;
import com.peaksoft.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

    @Component
    @RequiredArgsConstructor
    @Slf4j

    public class ProfileService {
        private final UserRepository userRepository;
        private final ProfileMapper profileMapper;
        private final BCryptPasswordEncoder encoder;
        private final JwTokenUtil jwTokenUtil;


        public ProfileResponse editProfile(ProfileRequest profileRequest) {
            User user = getAuthenticatedUser();
            profileMapper.mapToEntity(user,profileRequest);
            return profileMapper.mapToResponse(userRepository.save(user));
        }


      /*  public AuthResponse changePassword(ChangePassRequest request) {
            User user = getAuthenticatedUser();
            if (!encoder.matches(request.getOldPassword(), user.getPassword())) {
                log.error("Password don't match");
                throw new IncorrectLoginException("Passwords don't match");
            } else {
                System.out.println(request.getNewPassword());
                user.setPassword(encoder.encode(request.getNewPassword()));
                AuthResponse authResponse = new AuthResponse();
                authResponse.setId(user.getId());
                authResponse.setFirstName(user.getFirstName());
                authResponse.setLastName(user.getLastName());
                authResponse.setEmail(user.getEmail());
                authResponse.setAuthorities(String.valueOf(user.getAuthorities()));
                authResponse.setMessage("Password successfully changed");
                authResponse.setJwtToken(jwTokenUtil.generateToken(user));
                userRepository.save(user);
                return authResponse;
            }*/



        public User getAuthenticatedUser() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String login = authentication.getName();
            log.info("User: " + authentication.getName());
            return userRepository.findByEmail(login);
        }
    }





package com.peaksoft.controller;

import com.peaksoft.dto.AuthResponse;
  import com.peaksoft.dto.ChangePassRequest;
  import com.peaksoft.dto.ProfileRequest;
  import com.peaksoft.dto.ProfileResponse;
import com.peaksoft.model.entity.User;
import com.peaksoft.service.ProfileService;
  import lombok.RequiredArgsConstructor;
  import org.springframework.security.core.Authentication;
  import org.springframework.security.core.context.SecurityContextHolder;
  import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
  import org.springframework.web.bind.annotation.*;

          import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;

          @RestController
  @RequiredArgsConstructor
  @RequestMapping("/api/user")
  public class ProfileController {
              private final ProfileService profileService;

              @PutMapping("edit_profile")
              public ProfileResponse update(@RequestBody ProfileRequest userRequest) {
                  return profileService.editProfile(userRequest);
              }

              @PostMapping("change_password")
              public AuthResponse changePassword(@RequestBody ChangePassRequest changePasswordRequest) {
                  return profileService.changePassword(changePasswordRequest);
              }

              @RequestMapping(value = "/logout", method = RequestMethod.GET)
              public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
                  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                  if (auth != null) {
                      new SecurityContextLogoutHandler().logout(request, response, auth);
                  }
                  return "User has logged out!" ;
              }
          }

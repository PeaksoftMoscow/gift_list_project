package com.peaksoft.service;

import com.peaksoft.config.jwt.JwTokenUtil;
import com.peaksoft.dto.AuthResponse;
import com.peaksoft.dto.Mail;
import com.peaksoft.dto.ValidationType;
import com.peaksoft.exception.IncorrectLoginException;
import com.peaksoft.model.entity.ResetPasswordToken;
import com.peaksoft.model.User;
import com.peaksoft.repository.ResetPasswordTokenRepository;
import com.peaksoft.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ResetPasswordService {

    private final BCryptPasswordEncoder encoder;

    private final ResetPasswordTokenRepository resetPasswordTokenRepository;

    private final EmailService emailService;
    private final UserRepository userRepository;

    private final JwTokenUtil jwtTokenUtil;
    public String processForgotPassword(String email, HttpServletRequest request) {

        User user = userRepository.findByEmail(email).get();
        if (user == null){
            throw new UsernameNotFoundException("User with email" + email + "not found");
        }
        ResetPasswordToken resetPasswordToken = new ResetPasswordToken();
        resetPasswordToken.setUser(user);
        resetPasswordToken.setToken(jwtTokenUtil.generateToken(user));
        resetPasswordToken.setExpirationTime(LocalDateTime.now().plusMinutes(30));
        Mail mail = new Mail();
        mail.setFrom("ssaidibakas@gmail.com");
        mail.setTo(user.getEmail());
        mail.setSubject("Password reset request");
        Map<String ,Object> mailModel = new HashMap<>();
        mailModel.put("token",resetPasswordToken);
        mailModel.put("user",user);
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/api/jwt";
        mailModel.put("resetUrl",url + "/reset_password?token=" + resetPasswordToken.getToken());
        System.out.println(url + " " + resetPasswordToken.getToken());
        String URL = url + "/reset_password?token=" + resetPasswordToken.getToken();
        mail.setModel(mailModel);
        resetPasswordTokenRepository.save(resetPasswordToken);
        emailService.sendEmail(mail, URL);
        return ValidationType.SUCCESSFUL;
    }

    public AuthResponse save(String token, String password, String confirmPassword) {
        ResetPasswordToken resetToken = resetPasswordTokenRepository.findByToken(token);
        User user = resetToken.getUser();
        if (password.equals(confirmPassword)) {
            user.setPassword(password);
        }else {
            throw new IncorrectLoginException("Password don't match");
        }
        updatePassword(user);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setId(user.getId());
        authResponse.setFirstName(user.getFirstName());
        authResponse.setLastName(user.getLastName());
        authResponse.setEmail(user.getEmail());
        authResponse.setJwtToken(token);
        authResponse.setMessage(ValidationType.SUCCESSFUL);
        authResponse.setAuthorities(String.valueOf(user.getRoleES()));
        return authResponse;
    }

    private void updatePassword(User user) {
        User user1= userRepository.findById(user.getId()).get();
        user1.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user1);
    }
}

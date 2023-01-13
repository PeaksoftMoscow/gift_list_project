package com.peaksoft.spring_boot_jwt_token.service;

import com.peaksoft.spring_boot_jwt_token.dto.UserRequest;
import com.peaksoft.spring_boot_jwt_token.dto.UserResponse;
import com.peaksoft.model.User;
import com.peaksoft.spring_boot_jwt_token.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserResponse createUser(UserRequest request){
        User user = mapToEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return mapToResponse(user);
    }

    public UserResponse updateUser(Long id, UserRequest request){
        Optional<User> users = userRepository.findById(id);
        if(users.isEmpty()){
            throw new UsernameNotFoundException(format("User not found", id));
        }
        mapToUpdate(request,users.get());
        return mapToResponse(userRepository.save(users.get()));
    }

    public UserResponse deleteById(Long id){
        Optional<User> users = userRepository.findById(id);
        if(users.isEmpty()){
            throw new UsernameNotFoundException(format("User not found", id));
        }
        userRepository.deleteById(id);
        return mapToResponse(users.get());
    }

    public UserResponse findById(Long id){
        Optional<User> users = userRepository.findById(id);
        if(users.isEmpty()){
            throw new UsernameNotFoundException(format("User not found", id));
        }
        userRepository.findById(id);
        return mapToResponse(users.get());
    }

    public User mapToEntity(UserRequest request){
        User user  = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return user;
    }

    public User mapToUpdate(UserRequest request,User user){
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return user;
    }

    public UserResponse mapToResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }
}

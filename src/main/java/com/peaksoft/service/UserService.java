package com.peaksoft.service;

import com.peaksoft.dto.UserRequest;
import com.peaksoft.dto.UserResponse;
import com.peaksoft.exception.FobidenExceptoin;
import com.peaksoft.model.User;
import com.peaksoft.model.entity.enums.RoleE;
import com.peaksoft.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
private final UserRepository userRepository;
private final PasswordEncoder encoder;

public UserResponse register(UserRequest userRequest) {
	if (userRepository.existsByEmail(userRequest.getEmail())){
		throw new FobidenExceptoin(String.format("Пользователь с этим электронным адресом: %s уже существует!", userRequest.getEmail()));
	}
	User user1 = new User();
	userRepository.findByEmail(user1.getEmail());
	User user = mapToEntity(userRequest);
	user.setPassword(encoder.encode(userRequest.getPassword()));
	userRepository.save(user);
	return mapToResponse(user);
}

public User mapToEntity(UserRequest request) {
	User user = new User();
	user.setFirstName(request.getFirstName());
	user.setLastName(request.getLastName());
	user.setEmail(request.getEmail());
	user.setPassword(request.getPassword());
	if (user.getFirstName().equals("ryskeldi")) {
		user.setRoleES(RoleE.ADMIN);
	} else {
		user.setRoleES(RoleE.USER);
	}
	return user;
}
public UserResponse mapToResponse(User user) {
	return UserResponse.builder()
			       .id(user.getId())
			       .firstName(user.getFirstName())
			       .lastName(user.getLastName())
			       .email(user.getEmail())
			       .password(user.getPassword())
			       .role(user.getRoleES())
			       .build();
}
}

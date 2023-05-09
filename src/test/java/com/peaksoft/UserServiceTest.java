package com.peaksoft;

import com.peaksoft.dto.UserRequest;
import com.peaksoft.model.User;
import com.peaksoft.repository.UserRepository;
import com.peaksoft.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    private AutoCloseable autoCloseable;
    private UserService userService;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository, null);
    }


    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    @Order(1)
    void saveTest() {
        UserRequest request = new UserRequest();
        request.setFirstName("Timur");
        request.setFirstName("Kadyrbekov");
        request.setEmail("timur@gmail.com");
        User user = new User();
        userService.mapToEntity(request);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        userService.register(request);
        assertTrue(user.equals(user));
    }

    @Test
    public void testPasswordEncoder() {
        String password = "myPassword123";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        assertTrue(passwordEncoder.matches(password, hashedPassword));
    }

}

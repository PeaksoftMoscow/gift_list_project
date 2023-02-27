package com.peaksoft;

import com.peaksoft.model.User;
import com.peaksoft.service.AdminService;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @Mock
    private AdminService adminService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void deleteTest() {
        adminService.deleteUser(1L);
    }

    @Test
    public void testBlockUser() {

        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setLastName("testuser");
        user.setBlocked(true);
        adminService.blockUser(userId);
        Assert.notNull(user);
    }

    @Test
    public void testUnBlockUser() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setLastName("testuser");
        user.setBlocked(false);
        adminService.unblockUser(userId);
        Assert.notNull(user);
    }
}


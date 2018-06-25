package com.example.order;

import com.example.user.SecurityUser;
import com.example.user.UserService;
import com.example.utils.JwtTokenUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtTokenUtilsTests {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Test
    public void testGenerateToken() {
        SecurityUser securityUser = (SecurityUser) userService.loadUserByUsername("u2");
    }
}

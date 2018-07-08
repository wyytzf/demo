package com.example.user;

import com.example.DemoApplication;
import com.example.security.user.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class UserApplicationServiceTest {

    @Autowired
    UserApplicationService userApplicationService;


    @Test
    public void testAddUser() {

        User user = new User();
        user.setEmail("t");
        user.setPhone("t");
        user.setPassword("t");
        user.setAccount("t");
        user.setRealname("t");
        Role role = new Role();
        role.setName("ROLE_USER");
        List<Role> list = new ArrayList<>();
        list.add(role);
        user.setRoles(list);

        userApplicationService.saveUser(user);
    }
}

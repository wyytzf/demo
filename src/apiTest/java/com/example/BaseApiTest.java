package com.example;

import com.example.security.Role;
import com.example.security.user.RoleService;
import com.example.user.SecurityUser;
import com.example.user.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseApiTest {

    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";


    @Autowired
    protected TestRestTemplate testRestTemplate;
    @Autowired
    protected JwtTokenService jwtTokenService;
    @Autowired
    protected RoleService roleService;


    protected HttpEntity<Object> constructEntity(String token, Object body) {
        HttpHeaders headers = new HttpHeaders();
        if (token != null) {
            headers.set("Authorization", format("Bearer %s", token));
        }
        return new HttpEntity<>(body, headers);
    }

    protected User createUser(String info, String m_role) {
        User user = new User();
        user.setAccount(info);
        user.setPassword(info);
        user.setRealname(info);
        user.setEmail(info);
        user.setPhone(info);

        // Role 从数据库获取
        Role role = new Role();
        if (m_role.equals("ROLE_USER"))
            role.setId(1L);
        else if (m_role.equals("ROLE_ADMIN"))
            role.setId(2L);
        else
            return null;
        role.setName(m_role);
//        Role role = roleService.getRole(1L);
        List<Role> list = new ArrayList<>();
        list.add(role);
        user.setRoles(list);
        return user;
    }

    protected String createToken(User user) {
        SecurityUser securityUser = new SecurityUser(0, user.getAccount(), user.getPassword(), user.getRoles().
                stream().map((Role role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
        return jwtTokenService.generateToken(securityUser);
    }


}

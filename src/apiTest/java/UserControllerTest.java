import com.example.DemoApplication;
import com.example.role.Role;
import com.example.user.User;
import com.example.user.UserService;
import com.example.utils.JwtTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;
    private User acuser = new User();
    private String u1_user_token;
    private String u2_admin_token;
    private ObjectMapper mapper;
    private ObjectWriter ow;
    private long current_id;

    @Before
    public void setUp() {
        acuser.setAccount("test_post");
        acuser.setPassword("test_post");
        acuser.setRealname("test_post");
        acuser.setEmail("test_post");
        acuser.setPhone("test_post");
        acuser.setRegistertime(new Date());

        Role role = new Role();
        role.setName("ROLE_USER");
        List<Role> list = new ArrayList<>();
        list.add(role);
        acuser.setRoles(list);
        UserDetails userDetails_u1 = userService.loadUserByUsername("u1");
        UserDetails userDetails_u2 = userService.loadUserByUsername("u2");
        u1_user_token = jwtTokenUtil.generateToken(userDetails_u1);
        u2_admin_token = jwtTokenUtil.generateToken(userDetails_u2);
        mapper = new ObjectMapper();
        ow = mapper.writer().withDefaultPrettyPrinter();
    }

    @Test
    public void testAddUser() throws URISyntaxException {

        RequestEntity<User> requestEntity = RequestEntity
                .post(new URI("http://localhost:8080/user"))
                .header("Authorization", "Bearer " + u1_user_token)
                .body(acuser);

        ResponseEntity<String> entity = testRestTemplate.postForEntity("http://localhost:8080/user", requestEntity, String.class);
        HttpStatus statusCode = entity.getStatusCode();
        Assert.assertEquals(201, statusCode.value());
    }

    @Test
    public void testGetUser() throws Exception {
        /// 所有
        RequestEntity requestEntity = RequestEntity
                .get(new URI("http://localhost:8080/user")).accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + u2_admin_token).build();
//
//        ResponseEntity<List> entity = testRestTemplate.exchange(requestEntity, List.class);
//        List body = entity.getBody();
//        Assert.assertEquals(body.size(), 4);
        /// 某个
        requestEntity = RequestEntity.get(new URI("http://localhost:8080/user/" + current_id))
                .accept(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + u2_admin_token)
                .build();
        ResponseEntity<User> r1 = testRestTemplate.exchange(requestEntity, User.class);
        User user = r1.getBody();
        Assert.assertEquals(acuser.getAccount(), user.getAccount());
    }

    @Test
    public void testUpdateUser() throws Exception {
        acuser.setId(current_id);
        acuser.setPassword("test_put");
        acuser.setRealname("test_put");
        RequestEntity requestEntity = RequestEntity
                .put(new URI("http://localhost:8080/user")).accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + u1_user_token).body(acuser);
        ResponseEntity<String> result = testRestTemplate.exchange(requestEntity, String.class);
        HttpStatus statusCode = result.getStatusCode();
        Assert.assertEquals(204, statusCode.value());
    }

    @Test
    public void testDeleteUser() throws Exception {
        RequestEntity requestEntity = RequestEntity
                .delete(new URI("http://localhost:8080/user/" + current_id)).accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + u2_admin_token).build();

        ResponseEntity<String> result = testRestTemplate.exchange(requestEntity, String.class);
        HttpStatus statusCode = result.getStatusCode();
        Assert.assertEquals(204, statusCode.value());
    }

    @Test
    public void testGetOrders() throws Exception {
        RequestEntity requestEntity = RequestEntity
                .get(new URI("http://localhost:8080/user/orders")).accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + u1_user_token).build();

        ResponseEntity<String> result = testRestTemplate.exchange(requestEntity, String.class);
        HttpStatus statusCode = result.getStatusCode();
        String body = result.getBody();
    }


}
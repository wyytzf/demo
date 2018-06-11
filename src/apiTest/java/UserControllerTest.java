
import com.example.DemoApplication;
import com.example.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Before
    public void setUp(){

    }

    @Test
    public void testUserControllerPost() {

        User user = new User();
        user.setAccount("test_post");
        user.setAge(18);
        user.setPassword("test_post");
        user.setName("test_post");

        ResponseEntity<User> entity = testRestTemplate.postForEntity("http://localhost:8080/user", user, User.class);
        HttpStatus statusCode = entity.getStatusCode();
        Assert.assertEquals(201, statusCode.value());

    }

    @Test
    public void testUserControllerGet() throws Exception {
        ResponseEntity<List> entity = testRestTemplate.getForEntity("http://localhost:8080/user", List.class);
        HttpStatus statusCode = entity.getStatusCode();
        List body = entity.getBody();


        List<User> ex_list = new ArrayList<>();
        User user1 = new User();
        user1.setId(1L);
        user1.setAccount("test1");
        user1.setAge(18);
        user1.setPassword("test1");
        user1.setName("test1");

        User user2 = new User();
        user2.setId(2L);
        user2.setAccount("test2");
        user2.setAge(15);
        user2.setPassword("test2");
        user2.setName("test2");

        ex_list.add(user1);
        ex_list.add(user2);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String exceptJson = ow.writeValueAsString(ex_list);
        String actualJson = ow.writeValueAsString(body);
        Assert.assertEquals(200, statusCode.value());
        Assert.assertEquals(exceptJson, actualJson);

        ResponseEntity<User> userEntity = testRestTemplate.getForEntity("http://localhost:8080/user/{id}", User.class, 1);
        HttpStatus statusCode1 = userEntity.getStatusCode();
        User body1 = userEntity.getBody();

        exceptJson = ow.writeValueAsString(user1);
        actualJson = ow.writeValueAsString(body1);

        Assert.assertEquals(200, statusCode1.value());
        Assert.assertEquals(exceptJson, actualJson);
    }

    @Test
    public void testUserControllerDelete() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String> result = testRestTemplate.exchange("http://localhost:8080/user/4", HttpMethod.DELETE, entity, String.class);
        HttpStatus statusCode = result.getStatusCode();
        Assert.assertEquals(204, statusCode.value());


    }

    @Test
    public void testUserControllerPut() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setAccount("test1");
        user.setAge(28);
        user.setPassword("test1");
        user.setName("test1");


        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<User>(user, headers);
        ResponseEntity<String> result = testRestTemplate.exchange("http://localhost:8080/user", HttpMethod.PUT, entity, String.class, user);

        HttpStatus statusCode = result.getStatusCode();
        Assert.assertEquals(204, statusCode.value());

    }


}
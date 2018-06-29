package com.example.user;

import com.example.BaseApiTest;
import com.example.security.jwt.JwtTokenService;
import com.example.security.jwt.MyUserDetailService;
import com.example.security.jwt.UserPrincipal;
import com.example.security.user.Role;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserControllerTest extends BaseApiTest {


    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private MyUserDetailService userDetailService;
    private User acuser = new User();
    private String userToken;

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

        UserPrincipal userPrincipal = new UserPrincipal(1, "u1", "ROLE_USER");
        userToken = jwtTokenService.generateToken(userPrincipal);
    }


//    1. 测试方法的命名大约是这样的
//    2. 测试run的时候的端口是任意的，你这里指明8080，根本连接不到
//    3. 构造请求实体可以单独列出来一个方法，因为这个几乎每个测试都要用到，这个方法甚至应该放到测试父类。
//    4. 同样的道理，每个测试都要写@RunWith，那也可以构造一个父类，所有测试继承即可
//    5. TestRestTemplate大家都要用的，那也可以直接注入到父类
//    6. 注意变量的命名
//    7. current_id应该是一个局部变量，每个测试之间是独立地
//    8. current_id这里可以不assert返回的body，因为你也不知道它是多少；或者可以直接判断它是否非空

    @Test
    public void should_add_user_successfully() throws URISyntaxException {
        ResponseEntity<Long> response = testRestTemplate.postForEntity("/user", constructEntity(userToken, acuser), Long.class);
        HttpStatus statusCode = response.getStatusCode();
        assertEquals(201, statusCode.value());
        assertNotNull(response.getBody());
    }

//    1. 每个测试间是独立地，所以应该自己先加入用户，再来获取到它的返回结果。
//    2. 获取列表api的设计应该是复数，上次说过了吧
    @Test
    public void should_get_user_successfully() throws Exception {
        /// 所有
//        RequestEntity requestEntity = RequestEntity
//                .get(new URI("http://localhost:8080/user")).accept(MediaType.APPLICATION_JSON)
//                .header("Authorization", "Bearer " + u2_admin_token).build();
//
//        ResponseEntity<List> entity = testRestTemplate.exchange(requestEntity, List.class);
//        List body = entity.getBody();
//        assertEquals(body.size(), 4);
//        /// 某个
//        requestEntity = RequestEntity.get(new URI("http://localhost:8080/user/" + current_id))
//                .accept(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + u2_admin_token)
//                .build();
//        ResponseEntity<User> r1 = testRestTemplate.exchange(requestEntity, User.class);
//        User user = r1.getBody();
//        assertEquals(acuser.getAccount(), user.getAccount());
    }

//    1. 每个测试间是独立地，所以应该自己先加入用户，再来更新这个用户。
    @Test
    public void testUpdateUser() throws Exception {
//        acuser.setId(current_id);
//        acuser.setPassword("test_put");
//        acuser.setRealname("test_put");
//        RequestEntity requestEntity = RequestEntity
//                .put(new URI("http://localhost:8080/user")).accept(MediaType.APPLICATION_JSON)
//                .header("Authorization", "Bearer " + u1_user_token).body(acuser);
//        ResponseEntity<String> result = testRestTemplate.exchange(requestEntity, String.class);
//        HttpStatus statusCode = result.getStatusCode();
//        assertEquals(204, statusCode.value());
    }

    //    1. 每个测试间是独立地，所以应该自己先加入用户，再来删除这个用户。
    @Test
    public void testDeleteUser() throws Exception {
//        RequestEntity requestEntity = RequestEntity
//                .delete(new URI("http://localhost:8080/user/" + current_id)).accept(MediaType.APPLICATION_JSON)
//                .header("Authorization", "Bearer " + u2_admin_token).build();
//
//        ResponseEntity<String> result = testRestTemplate.exchange(requestEntity, String.class);
//        HttpStatus statusCode = result.getStatusCode();
//        assertEquals(204, statusCode.value());
    }
}
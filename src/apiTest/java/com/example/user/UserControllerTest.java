package com.example.user;

import com.example.BaseApiTest;
import com.example.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

public class UserControllerTest extends BaseApiTest {


    @Autowired
    private UserService userService;
    private User acuser = new User();
    private String u1_user_token;
    private String u2_admin_token;
    private ObjectMapper mapper;
    private ObjectWriter ow;

    @Before
    public void setUp() {
//        acuser.setAccount("test_post");
//        acuser.setPassword("test_post");
//        acuser.setRealname("test_post");
//        acuser.setEmail("test_post");
//        acuser.setPhone("test_post");
//        acuser.setRegistertime(new Date());
//
//        Role role = new Role();
//        role.setName("ROLE_USER");
//        List<Role> list = new ArrayList<>();
//        list.add(role);
//        acuser.setRoles(list);

        UserDetails userDetails_u1 = userService.loadUserByUsername("user");
        UserDetails userDetails_u2 = userService.loadUserByUsername("admin");
        u1_user_token = jwtTokenService.generateToken(userDetails_u1);
        u2_admin_token = jwtTokenService.generateToken(userDetails_u2);
        mapper = new ObjectMapper();
        ow = mapper.writer().withDefaultPrettyPrinter();
    }


//    1. 测试方法的命名大约是这样的
//    2. 测试run的时候的端口是任意的，你这里指明8080，根本连接不到
//    3. 构造请求实体可以单独列出来一个方法，因为这个几乎每个测试都要用到，这个方法甚至应该放到测试父类。
//    4. 同样的道理，每个测试都要写@RunWith，那也可以构造一个父类，所有测试继承即可
//    5. TestRestTemplate大家都要用的，那也可以直接注入到父类
//    6. 注意变量的命名
//    7. current_id应该是一个局部变量，每个测试之间是独立地
//    8. current_id这里可以不assert返回的body，因为你也不知道它是多少；或者可以直接判断它是否非空

    private static String getRandomString(int length) {
        String string = "abcdefghijklmnopqrstuvwxyz";
        StringBuffer sb = new StringBuffer();
        int len = string.length();
        for (int i = 0; i < length; i++) {
            sb.append(string.charAt((int) Math.round(Math.random() * (len - 1))));
        }
        return sb.toString();
    }


    @Test
    public void should_add_user_successfully() throws URISyntaxException {
        String info = getRandomString(10);
        User user = createUser(info, "ROLE_USER");
        ResponseEntity<Result> response = testRestTemplate.postForEntity("/user", constructEntity(null, user), Result.class);
        Result body = response.getBody();
        assertEquals(200, body.getCode().intValue());

        /// 增加一个具有相同用户名的用户

        response = testRestTemplate.postForEntity("/user", constructEntity(null, user), Result.class);
        body = response.getBody();
        assertEquals(400, body.getCode().intValue());
//        assertNotNull(response.getBody());
    }

    //    1. 每个测试间是独立地，所以应该自己先加入用户，再来获取到它的返回结果。
//    2. 获取列表api的设计应该是复数，上次说过了吧
    @Test
    public void should_get_users_successfully() throws Exception {

        /**
         * 先添加一个ROLE_USER权限用户和一个ROLE_ADMIN权限用户
         *
         * 未完成
         */

        //具有ADMIN权限
        ResponseEntity<Result> response = testRestTemplate.exchange("/user", HttpMethod.GET, constructEntity(u2_admin_token, null), Result.class);
        Result body = response.getBody();
        assertEquals(200, body.getCode().intValue());
        //具有USER权限,403直接拒绝
        response = testRestTemplate.exchange("/user", HttpMethod.GET, constructEntity(u1_user_token, null), Result.class);
        assertEquals(403, response.getStatusCodeValue());
        //不具有权限,403直接拒绝
        response = testRestTemplate.exchange("/user", HttpMethod.GET, constructEntity(null, null), Result.class);
        assertEquals(403, response.getStatusCodeValue());

        //具有ADMIN权限，得到某一存在的用户
        response = testRestTemplate.exchange("/user/1", HttpMethod.GET, constructEntity(u2_admin_token, null), Result.class);
        body = response.getBody();
        assertEquals(200, body.getCode().intValue());

        //具有ADMIN权限，得到不存在的用户
        response = testRestTemplate.exchange("/user/999", HttpMethod.GET, constructEntity(u2_admin_token, null), Result.class);
        body = response.getBody();
        assertEquals(400, body.getCode().intValue());


        //具有ADMIN权限，用户id不为数字,400badrequest
        response = testRestTemplate.exchange("/user/asd", HttpMethod.GET, constructEntity(u2_admin_token, null), Result.class);
        assertEquals(400, response.getStatusCodeValue());

        //具有USER权限，得到某一存在的用户,403直接拒绝
        response = testRestTemplate.exchange("/user/1", HttpMethod.GET, constructEntity(u1_user_token, null), Result.class);
        assertEquals(403, response.getStatusCodeValue());


        //具有USER权限，得到不存在的用户,403直接拒绝
        response = testRestTemplate.exchange("/user/999", HttpMethod.GET, constructEntity(u1_user_token, null), Result.class);
        assertEquals(403, response.getStatusCodeValue());

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
    public void should_update_user_successfully() throws Exception {

        String info = getRandomString(10);
        User user = createUser(info, "ROLE_USER");
        String token = createToken(user);
        ResponseEntity<Result> response = testRestTemplate.postForEntity("/user", constructEntity(null, user), Result.class);

        user.setPassword("new" + info);
        user.setEmail("new" + info);

        response = testRestTemplate.exchange("/user", HttpMethod.PUT, constructEntity(token, user), Result.class);
        Result body = response.getBody();
        assertEquals(200, body.getCode().intValue());
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
    public void should_delete_user_successfully() throws Exception {

        String info = getRandomString(10);
        User user = createUser(info, "ROLE_ADMIN");
        String token = createToken(user);
        ResponseEntity<Result> response = testRestTemplate.postForEntity("/user", constructEntity(null, user), Result.class);
        Result body = response.getBody();
//        user = (User) body.getData();
        response = testRestTemplate.exchange("/user/" + body.getData(), HttpMethod.DELETE, constructEntity(token, null), Result.class);
        body = response.getBody();
        assertEquals(200, body.getCode().intValue());
//        assertEquals(204, response.getStatusCode().value());
//        RequestEntity requestEntity = RequestEntity
//                .delete(new URI("http://localhost:8080/user/" + current_id)).accept(MediaType.APPLICATION_JSON)
//                .header("Authorization", "Bearer " + u2_admin_token).build();
//
//        ResponseEntity<String> result = testRestTemplate.exchange(requestEntity, String.class);
//        HttpStatus statusCode = result.getStatusCode();
//        assertEquals(204, statusCode.value());
    }
}
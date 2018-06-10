package com.example.user;

import com.example.DemoApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class UserControllerTest {
    @Autowired
    private WebApplicationContext demoApplication;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(demoApplication).build();
    }

    @Test
    public void testUserControllerPost() throws Exception {
        RequestBuilder request;

        User user = new User();
        user.setAccount("test1");
        user.setAge(18);
        user.setPassword("test1");
        user.setName("test1");
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(user);

        request = post("/user").contentType(MediaType.APPLICATION_JSON).content(json);
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(201, status);
    }

    @Test
    public void testUserControllerGet() throws Exception {

        RequestBuilder request;
        request = get("/user");
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

    @Test
    public void testUserControllerDelete() throws Exception {
        RequestBuilder request;
        request = delete("/user/3");
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(204, status);
    }

    @Test
    public void testUserControllerPut() throws Exception {
        RequestBuilder request;

        User user = new User();
        user.setId(4L);
        user.setAccount("testPut");
        user.setAge(18);
        user.setPassword("testPut");
        user.setName("testPut");
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(user);

        request = put("/user").contentType(MediaType.APPLICATION_JSON).content(json);
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(204, status);
    }


}

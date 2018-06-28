package com.example;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static java.lang.String.format;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseApiTest {

    @Autowired
    protected TestRestTemplate testRestTemplate;

    protected HttpEntity<Object> constructEntity(String token, Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", format("Bearer %s", token));
        return new HttpEntity<>(body, headers);
    }
}

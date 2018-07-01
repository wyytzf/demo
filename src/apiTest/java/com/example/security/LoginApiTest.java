package com.example.security;

import com.example.BaseApiTest;
import com.example.security.user.UsernamePasswordLoginCommand;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LoginApiTest extends BaseApiTest {

    @Test
    public void should_get_token_successfully() {
        UsernamePasswordLoginCommand user = new UsernamePasswordLoginCommand("u1", "u1");
        ResponseEntity<String> response = testRestTemplate.postForEntity("/login", user, String.class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response);
    }
}

package com.example.token;

import com.example.user.User;
import com.example.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
public class TokenController {

    private TokenService tokenService;
    private UserService userService;


    @Autowired
    public TokenController(TokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity login(@RequestParam String account, @RequestParam String password) {
        User user = userService.getUserByAccount(account);
        if (user == null || !user.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Token token = tokenService.createToken(user.getId());
        return ResponseEntity.ok(token);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity logout(@PathVariable Long id) {
        tokenService.deleteToken(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

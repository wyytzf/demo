package com.example.security.user;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private LoginApplicationService loginApplicationService;

    public LoginController(LoginApplicationService loginApplicationService) {
        this.loginApplicationService = loginApplicationService;
    }

    /*
    * 用这个注解更简洁
    * 一般刷新token，都是在验证通过之后，所以刷新的时候不传入用户名和密码，直接根据登录者信息生产即可
    * 这里生成token，我们暂时就传入body
    * */
    @PostMapping(value = "/login")
    public String login(@RequestBody UsernamePasswordLoginCommand command) {
        return loginApplicationService.authenticateUser(command);
    }
}
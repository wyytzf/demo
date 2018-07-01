package com.example.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserApplicationService applicationService;

    @Autowired
    public UserController(UserApplicationService applicationService) {
        this.applicationService = applicationService;
    }


    /// @RequestBody用作application/json或者是application/xml等
    /// 不加@RequestBody form-data,x-www-form-urlencoded等。
    ///
    ///
    //
    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<Long> addUser(@RequestBody User user) {
        Long id = applicationService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        applicationService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @PreAuthorize(value = "hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Void> updateUser(@RequestBody User user) {
        applicationService.updateUser(user);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable("id") Long id) {
        User userById = applicationService.getUser(id);
        if (userById == null) {
            return null;
        }
        return userById;
    }

    // 数据库里需要定义成ROLE_USER
    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<List<User>> getUser() {
        List<User> users = applicationService.getUserList();
        if (users == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(users);
    }
}

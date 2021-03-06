package com.example.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    long saveUser(User user);

    long updateUser(User user);

    void deleteUser(Long id);

    User getUser(Long id);

    User getUserByAccount(String account);

    List<User> listUser();
}

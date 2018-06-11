package com.example.user;

import com.example.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);

    User getUser(Long id);

    List<User> listUser();
}

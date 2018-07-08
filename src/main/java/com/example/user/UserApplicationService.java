package com.example.user;

import com.example.security.user.Role;
import com.example.security.user.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserApplicationService {
    private UserService userService;
    private RoleRepository roleRepository;

    public UserApplicationService(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public Long saveUser(User user) {
        long uid = userService.saveUser(user);
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            Role t = roleRepository.findByName(role.getName());
            if (t == null) {
                // 抛异常
                System.out.println("t == null");
            }
            roleRepository.addRole(uid, t.getId());
        }

        return uid;
    }

    @Transactional
    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }

    @Transactional
    public void updateUser(User user) {
        userService.updateUser(user);
    }

    @Transactional
    public User getUser(Long id) {
        return userService.getUser(id);
    }

    @Transactional
    public List<User> getUserList() {
        return userService.listUser();
    }
}

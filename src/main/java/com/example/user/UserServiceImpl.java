package com.example.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public long saveUser(User user) {
        String account = user.getAccount();
        User userByAccount = getUserByAccount(account);
        if (userByAccount == null) {
            String password = user.getPassword();
            user.setPassword(passwordEncoder.encode(password).trim());
            return userRepository.save(user).getId();
        } else {
            return -1;
        }

    }

    @Override
    @Transactional
    public long updateUser(User user) {
        String account = user.getAccount();
        User userByAccount = getUserByAccount(account);
        if (userByAccount != null) {
            String password = user.getPassword();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setId(userByAccount.getId());
            user.setPassword(encoder.encode(password).trim());
            return userRepository.save(user).getId();
        } else {
            return -1;
        }

    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUser(Long id) {
        User one = userRepository.findUserById(id);
        return one;
    }

    @Override
    public User getUserByAccount(String account) {
        return userRepository.findByAccount(account);
    }

    @Override
    public List<User> listUser() {
        return userRepository.findAll();
    }
}

package com.example.user;

import com.example.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public long saveUser(User user) {
        String account = user.getAccount();
        User userByAccount = getUserByAccount(account);
        if (userByAccount == null) {
            String password = user.getPassword();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(password).trim());
            return userRepository.save(user).getId();
        } else {
            return -1;
        }

    }

    @Override
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


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByAccount(username);
        if (user == null) {
            throw new UsernameNotFoundException("UsernameNotFoundException");
        } else {
            return new SecurityUser(user.getId(), user.getAccount(), user.getPassword(), user.getRoles().
                    stream().map((Role role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
        }

    }
}

package com.example.security.jwt;

import com.example.security.user.Role;
import com.example.user.User;
import com.example.user.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class MyUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    public MyUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByAccount(username);
        if (user == null) {
            throw new UsernameNotFoundException("UsernameNotFoundException");
        } else {
            return new UserPrincipal(user.getId(), user.getAccount(), user.getPassword(), "ROLE_USER", user.getRoles().
                stream().map((Role role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
        }
    }
}

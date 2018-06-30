package com.example.security.user;

import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    Role getRole(Long id);
}

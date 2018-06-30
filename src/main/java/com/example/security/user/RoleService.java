package com.example.security;

import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    Role getRole(Long id);
}

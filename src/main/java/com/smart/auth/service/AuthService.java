package com.smart.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.auth.domain.Role;
import com.smart.auth.domain.User;
import com.smart.auth.payload.CreateUserRequest;
import com.smart.auth.repository.RoleRepository;
import com.smart.auth.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    // =========================
    // USER REGISTRATION
    // =========================
    @Transactional
    public User registerUser(CreateUserRequest request) {

        // 1. Check duplicate user
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        // 2. Create user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(true);

        // 3. Assign DEFAULT ROLE_USER
        Role userRole = roleRepository.findByName("ROLE_USER")
            .orElseThrow(() ->
                new IllegalStateException("ROLE_USER not found in DB"));

        user.getRoles().add(userRole);

        // 4. Save
        return userRepository.save(user);
    }
}

package com.smart.auth.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.auth.domain.Role;
import com.smart.auth.domain.User;
import com.smart.auth.repository.RoleRepository;
import com.smart.auth.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    // =========================
    // ADMIN-ONLY OPERATION
    // =========================
    @Transactional
    public void assignRoleToUser(Long userId, String roleName) {

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Role role = roleRepository.findByName(roleName)
            .orElseThrow(() -> new IllegalArgumentException("Role not found"));

        user.getRoles().add(role);

        userRepository.save(user);
    }
}

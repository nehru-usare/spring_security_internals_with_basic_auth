package com.smart.auth.config;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.smart.auth.domain.Role;
import com.smart.auth.domain.User;
import com.smart.auth.repository.RoleRepository;
import com.smart.auth.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

	private final RoleRepository roleRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Bean
	CommandLineRunner initData() {
		return args -> initialize();
	}

	@Transactional
	public void initialize() {

		// =========================
		// 1. Create Roles (If Not Exists)
		// =========================
		Role adminRole = createRoleIfNotExists("ROLE_ADMIN");
		Role userRole = createRoleIfNotExists("ROLE_USER");

		// =========================
		// 2. Create Admin User (If Not Exists)
		// =========================
		Optional<User> adminOpt = userRepository.findByUsername("admin");

		if (adminOpt.isEmpty()) {

			User admin = new User();
			admin.setUsername("admin");
			admin.setPassword(passwordEncoder.encode("password"));
			admin.setEnabled(true);

			// Assign ROLE_ADMIN
			admin.getRoles().add(adminRole);

			userRepository.save(admin);
		}
	}

	// =========================
	// Helper Method
	// =========================
	private Role createRoleIfNotExists(String roleName) {

		return roleRepository.findByName(roleName).orElseGet(() -> {
			Role role = new Role();
			role.setName(roleName);
			return roleRepository.save(role);
		});
	}
}

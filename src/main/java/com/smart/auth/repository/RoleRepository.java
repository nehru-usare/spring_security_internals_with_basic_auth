package com.smart.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smart.auth.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(String name);

}

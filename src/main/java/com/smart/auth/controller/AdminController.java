package com.smart.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smart.auth.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminController {

	private final UserService userService;

	@PostMapping("/users/roles")
	public ResponseEntity<String> assignRole(@RequestParam(name = "userId") Long userId,
			@RequestParam(name = "roleName") String roleName) {

		userService.assignRoleToUser(userId, roleName);
		return ResponseEntity.ok("Role assigned successfully");
	}

}

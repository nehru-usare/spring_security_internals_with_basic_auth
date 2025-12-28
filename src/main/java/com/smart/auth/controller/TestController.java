package com.smart.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

	@GetMapping("/public")
	public String publicHello() {
		return "Public API - No Auth Required";
	}

	@GetMapping("/secure")
	public String secureHello() {
		return "You are authenticated!";
	}
}

package com.smart.auth.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateUserResponse {

	private Long id;
	private String username;
}

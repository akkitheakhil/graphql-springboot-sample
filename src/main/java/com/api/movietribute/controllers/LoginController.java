package com.api.movietribute.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.movietribute.models.LoginUser;
import com.api.movietribute.security.jwt.JwtGenerator;

@RestController
@RequestMapping("/login")
public class LoginController {

	private JwtGenerator jwtGenerator;
	
	public LoginController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }
	
	@PostMapping
	public String generate(@RequestBody final LoginUser jwtUser) {
		return jwtGenerator.generate(jwtUser);
	}
}

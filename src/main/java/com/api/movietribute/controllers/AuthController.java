package com.api.movietribute.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.movietribute.dto.LoginUser;
import com.api.movietribute.dto.RegisterUser;
import com.api.movietribute.dto.StatusMessage;
import com.api.movietribute.dto.Token;
import com.api.movietribute.services.IAuthService;

@RestController
@RequestMapping("authenticate")
public class AuthController {

	@Autowired
	public IAuthService authService;
	
	@RequestMapping("register")
	@PostMapping
	public StatusMessage registerUser(@RequestBody RegisterUser user) {
		return authService.Register(user);
	}
	
	
	@RequestMapping("login")
	@GetMapping
	public Token validateUser(@RequestBody LoginUser user) {
		return authService.validateUser(user);
	}
	
}

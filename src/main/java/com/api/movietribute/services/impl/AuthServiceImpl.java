package com.api.movietribute.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.api.movietribute.dto.LoginUser;
import com.api.movietribute.dto.RegisterUser;
import com.api.movietribute.dto.StatusMessage;
import com.api.movietribute.dto.Token;
import com.api.movietribute.models.User;
import com.api.movietribute.repository.IUserDao;
import com.api.movietribute.security.AuthUserService;
import com.api.movietribute.security.jwt.JwtUtil;
import com.api.movietribute.services.IAuthService;

@Service
public class AuthServiceImpl implements IAuthService{

	
	@Autowired
	IUserDao userDao;
	
	@Autowired
	AuthUserService authUserService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Override
	public StatusMessage Register(RegisterUser user) {
		
		StatusMessage statusMessage = new StatusMessage();
		User userData = new User();
		
		try{
			userData = MapRegisterUserToUser(user);
			userDao.save(userData);
			statusMessage.setStatus("Success");
			statusMessage.setMessage("Successfully Registered");
			
		}catch(Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
		
		return statusMessage;
	}

	@Override
	public RegisterUser getUserData(String username) {
		RegisterUser userData = new RegisterUser();
		
		try{
			User user = new User(); 
			user = userDao.findByUsername(username);
			userData.setUsername(user.getUsername());
			userData.setEmail(user.getEmail());
			userData.setPassword(user.getPassword());
			
		}catch(Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
		
		return userData;
	}

	@Override
	public User MapRegisterUserToUser(RegisterUser user) {
		User userData = new User();
		userData.setUsername(user.getUsername());
		userData.setEmail(user.getEmail());
		userData.setPassword(user.getPassword());
		return userData;
	}

	@Override
	public Token validateUser(LoginUser user) {
	
		Token token = new Token();
		
		try{
			User userData = new User(); 
			userData = userDao.findByUsername(user.getUsername());
			
			if(null!=userData && user.getUsername().equals(userData.getUsername()) && user.getPassword().equals(userData.getPassword()) ) {
				
				final UserDetails userDetails = authUserService.loadUserByUsername(user.getUsername());
				final String jwt = jwtUtil.generateToken(userDetails);
				token.setAccessToken(jwt);
				
			}else {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Username or password do not match");
			}
			
			
		}catch(Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	
		
		return token;
	}

}

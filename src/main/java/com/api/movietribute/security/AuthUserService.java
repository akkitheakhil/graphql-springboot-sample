package com.api.movietribute.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.movietribute.dto.RegisterUser;
import com.api.movietribute.services.IAuthService;

@Service
public class AuthUserService implements UserDetailsService {

	@Autowired
	public IAuthService authService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		RegisterUser userInfo = new RegisterUser();
		userInfo = authService.getUserData(username);
		
		return new User(userInfo.getUsername(), userInfo.getPassword(), new ArrayList<>());
	}


}

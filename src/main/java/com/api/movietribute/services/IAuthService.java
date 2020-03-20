package com.api.movietribute.services;

import com.api.movietribute.dto.LoginUser;
import com.api.movietribute.dto.RegisterUser;
import com.api.movietribute.dto.StatusMessage;
import com.api.movietribute.dto.Token;
import com.api.movietribute.models.User;

public interface IAuthService {
	public StatusMessage Register(RegisterUser user);
	public RegisterUser getUserData(String username); 
	public User MapRegisterUserToUser(RegisterUser user);
	public Token validateUser(LoginUser user);
}

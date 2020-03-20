package com.api.movietribute.services;

import java.util.List;
import java.util.Optional;

import com.api.movietribute.dto.UserData;
import com.api.movietribute.models.User;

public interface IUserService {

	public List<User> getAllUserDetails();
	public Optional<User> getUser(final int id);
	public List<UserData> getAllUserDataDetails();
	public UserData mapUserToUserData(User user);
	public Optional<UserData> getUserData(final int id);

}

package com.api.movietribute.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.api.movietribute.dto.UserData;
import com.api.movietribute.models.User;
import com.api.movietribute.repository.IUserDao;
import com.api.movietribute.services.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	public IUserDao userDao;
	
	@Override
	public List<User> getAllUserDetails() {
		return userDao.findAll();
	}
	
	@Override
    public Optional<User> getUser(final int id) {
        return this.userDao.findById(id);
    }

	@Override
	public List<UserData> getAllUserDataDetails() {
		List<UserData> userDataList = new ArrayList<UserData>();
		List<User> userList = new ArrayList<User>();
		userList = getAllUserDetails();
		userDataList.addAll(userList.parallelStream().map(usr -> mapUserToUserData(usr)).collect(Collectors.toList()));
		return userDataList;
	}

	@Override
	public UserData mapUserToUserData(User user) {
		UserData userData = new UserData();
		userData.setId(user.getId());
		userData.setUsername(user.getUsername());
		userData.setEmail(user.getEmail());
		return userData;
	}

	@Override
	public Optional<UserData> getUserData(int id) {
		Optional<UserData> userData;
		userData = getUser(id).map(m -> mapUserToUserData(m));
		return userData;
	}
	
	
}

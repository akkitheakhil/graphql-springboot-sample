package com.api.movietribute.query;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.movietribute.dto.UserData;
import com.api.movietribute.models.User;
import com.api.movietribute.services.IUserService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@Component
public class UserQuery implements GraphQLQueryResolver {

	@Autowired
	public IUserService userService;

	public List<UserData> getUserDatas(){
		return userService.getAllUserDataDetails();
	}

	public Optional<UserData> getUserData(final int id) {
		return userService.getUserData(id);
	}
}

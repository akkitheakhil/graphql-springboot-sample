package com.api.movietribute.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.movietribute.models.User;

public interface IUserDao extends JpaRepository<User, Integer> {

	public User findByUsername(String username);

}

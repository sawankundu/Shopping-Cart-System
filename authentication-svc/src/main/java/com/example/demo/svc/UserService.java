package com.example.demo.svc;

import java.util.Set;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.UserRole;

public interface UserService {

	// creating user
	public User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	//get user
	public User getUser(String username);

}

package com.example.demo.svc.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.model.UserRole;
import com.example.demo.repo.RoleRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.svc.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private RoleRepo roleRepo;
	
	//creating user
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception{
		User local = userRepo.findByEmail(user.getEmail());
		if(local!=null) {
			System.out.println("Email id already exist");
			throw new Exception("Email id already exist");
		}else {
			for (UserRole role : userRoles) {
				roleRepo.save(role.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			local = userRepo.save(user);
		}
		return local;
	}

	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return userRepo.findByUsername(username);
	}

}

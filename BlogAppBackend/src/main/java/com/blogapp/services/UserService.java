package com.blogapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blogapp.dto.request.DtoPasswordChange;
import com.blogapp.model.User;
import com.blogapp.repo.UserRepository;
@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	CustomUserDetails customUserDetails;
	
	@Autowired
	PasswordEncoder encoder;
	
	//Fetching user info using user ID
	@Transactional(readOnly = true)
	public User getUser(String id) 
	{
		Optional<User> user=userRepository.findById(id);
		return user.get();
	}
	
	//change password for logged in user.
	public Boolean changeUserPassword(String id,DtoPasswordChange dtoPasswordChange)
	{
		Optional<User> user=userRepository.findById(id);
		if(user.get().getUsername() == customUserDetails.getCurrentUser().getUsername() && encoder.matches(dtoPasswordChange.getOldpassword(),user.get().getPassword())==true) {
			user.get().setPassword(encoder.encode(dtoPasswordChange.getNewpassword()));
			userRepository.save(user.get());
			return true;
		}
		else
		{
			return false;
		}
		
	}
}

package com.blogapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.blogapp.dto.request.DtoPasswordChange;
import com.blogapp.model.User;
import com.blogapp.repo.UserRepository;

/**
 * This class contains method for fetching user details and changing password
 * 
 * @author Varsha
 *
 */
@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	CustomUserDetails customUserDetails;

	@Autowired
	PasswordEncoder encoder;

	/**
	 * This method Fetches user info using user ID
	 * 
	 * @param id
	 * @return user object
	 */
	public User getUser(String id) {
		Optional<User> user = userRepository.findById(id);
		return user.get();
	}

	/**
	 * This method changes password for logged in user.
	 * 
	 * @param id
	 * @param dtoPasswordChange
	 * @return Boolean
	 */
	public Boolean changeUserPassword(String id, DtoPasswordChange dtoPasswordChange) {
		Optional<User> user = userRepository.findById(id);
		if (user.get().getUsername() == customUserDetails.getCurrentUser().getUsername()
				&& encoder.matches(dtoPasswordChange.getOldpassword(), user.get().getPassword()) == true) {
			user.get().setPassword(encoder.encode(dtoPasswordChange.getNewpassword()));
			userRepository.save(user.get());
			return true;
		}
		return false;

	}
}

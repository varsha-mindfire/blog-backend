package com.blogapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.blogapp.dto.request.DtoPasswordChange;
import com.blogapp.model.User;
import com.blogapp.repo.UserRepository;

/**
 * This class contains method for fetching user details and changing password
 * 
 * @author Varsha
 * @since 15/03/2021
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
	public User getAuthUser(String id) {
		// fetching userdetails by userId
		Optional<User> user = userRepository.findById(id);
		user.orElseThrow(() -> new UsernameNotFoundException("User name not found"));
		return user.get();
	}

	/**
	 * This method changes password for logged in user.
	 * 
	 * @param id
	 * @param dtoPasswordChange
	 * @return Boolean
	 */
	public void changeUserPassword(String id, DtoPasswordChange dtoPasswordChange) {
		// fetching userdetails by userId
		Optional<User> user = userRepository.findById(id);
		user.orElseThrow(() -> new UsernameNotFoundException("User name not found"));
		if (user.get().getUsername() == customUserDetails.getCurrentUser().getUsername()
				&& encoder.matches(dtoPasswordChange.getOldpassword(), user.get().getPassword()) == true) {
			user.get().setPassword(encoder.encode(dtoPasswordChange.getNewpassword()));
			userRepository.save(user.get());
		}
	}
}

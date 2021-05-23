package com.blogapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.blogapp.dto.request.DtoPasswordChange;
import com.blogapp.model.User;
import com.blogapp.services.CustomUserDetails;
import com.blogapp.services.UserService;
/**
 * 
 * @author Varsha
 * @since 15/03/2021
 *
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class UserController {
	@Autowired
	CustomUserDetails customUserDetails;

	@Autowired
	UserService userService;

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}

	/**
	 * Method responsible for displaying user info
	 * 
	 * @param id
	 * @return userdetails username,role,blogcount.
	 */
	@RequestMapping(value = "id/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getByUserId(@PathVariable String id) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.getAuthUser(id));
	}

	/**
	 * Method responsible for changing password
	 * 
	 * @param id, dtoPasswordChange
	 * @return Boolean
	 */
	@PutMapping("/{id}")
	public boolean changePassword(@PathVariable("id") String id, @RequestBody DtoPasswordChange dtoPasswordChange) {
		userService.changeUserPassword(id, dtoPasswordChange);
		return true;
	}
}
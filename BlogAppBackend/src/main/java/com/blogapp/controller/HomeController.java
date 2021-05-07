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

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class HomeController {
	@Autowired
	CustomUserDetails customUserDetails;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
	
	//API for displaying user info
	@RequestMapping(value = "id/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getBlogByUserId(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(id));
	}
	
	//API for changing password
	 @PutMapping("/{id}")
	 public Boolean changePassword(@PathVariable("id") String id,@RequestBody DtoPasswordChange dtoPasswordChange){
		 if(userService.changeUserPassword(id,dtoPasswordChange)==true) {
			 return true;
		 }
		 else {
			 return false;
		 } 
	 }
}
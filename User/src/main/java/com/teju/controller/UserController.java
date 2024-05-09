package com.teju.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teju.model.User;
import com.teju.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	
	@Autowired
	UserService use;
	int x;
	
	@GetMapping("/profile")
	public ResponseEntity<User> getprofile(@RequestHeader("Authorization") String jwt)
	{
		System.out.println("email");
	  User u=use.getuserProfile(jwt);
	  System.out.println(u);
	  return new ResponseEntity<>(u,HttpStatus.OK);
	  
		
	}
	
	@GetMapping("/users")
	
	public ResponseEntity<List<User>> findAllUsersByUserRole(

			@RequestHeader("Authorization") String jwt)  {

		List<User> users = use.findAllUsersByUserRole();


		return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/dlete/{uid}")
	public ResponseEntity<?> deleteuser(@PathVariable Long uid)
	{
		
		String s=use.deletebyId(uid);
		if(s=="No")
			return new ResponseEntity<>("No user  with this id", HttpStatus.BAD_REQUEST);
			
		return new ResponseEntity<>("user deleted", HttpStatus.OK);
	}
}

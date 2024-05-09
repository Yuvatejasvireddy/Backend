package com.teju.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teju.config.JwtProvider;
import com.teju.exceptions.UserException;
import com.teju.model.User;
import com.teju.repository.UserRepository;
import com.teju.request.LoginRequest;
import com.teju.response.AuthResponse;
import com.teju.service.CustomUserServiceImplementation;



@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserRepository ur;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CustomUserServiceImplementation customUserDetails;
	
	
	


	

	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException
	{
		String email=user.getEmail();
		String password=user.getPassword();
		String fullName=user.getFullname();
		String role=user.getRole();
		
		User isEmailExist=ur.findbyEmail(email);
		
		if(isEmailExist!=null)
		{
			throw new UserException("Email is already in use");
		}
		
		//create new user
		
	     User n=new User();
	     n.setEmail(email);
	     n.setFullname(fullName);
	     n.setRole(role);
	     n.setPassword(passwordEncoder.encode(password));
	     
	     User s=ur.save(n);
		
		
	     //Authentication 
	     Authentication authentication =new UsernamePasswordAuthenticationToken(email,password);
	     SecurityContextHolder.getContext().setAuthentication(authentication);
	     
	     String token=JwtProvider.generateToken(authentication);
	     
	     
	     // after creating the jwt token we sendoing a resposnse (may be to front end and we are going to store this in local browser)
	     AuthResponse au=new AuthResponse();
	     au.setJwt(token);
	     au.setMessgae("Register Success");
	     au.setStatus("true");
	     
	     return new ResponseEntity<AuthResponse>(au,HttpStatus.CREATED);
	}
	
	
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest lr)
	{
		String username=lr.username;
		String password=lr.password;
		System.out.println(username + "auth");
		Authentication authentication =authenticate(username,password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		AuthResponse ar=new AuthResponse();
		String token=JwtProvider.generateToken(authentication);
		ar.setJwt(token);
		ar.setMessgae("Login Success");
		ar.setStatus("true");
		
		
		return new ResponseEntity<AuthResponse> (ar,HttpStatus.OK);
		
		
		
	}


	private Authentication authenticate(String username, String password) {
		
		UserDetails ud=customUserDetails.loadUserByUsername(username);
		System.out.println(ud.getUsername() + "authenti");
		
		if(ud==null)
		{
			throw  new BadCredentialsException("Invalid username");
		}
		if(!passwordEncoder.matches(password,ud.getPassword()))
				{
			       throw  new BadCredentialsException("Wrong Credentials");
				}
		
		
		
		return new UsernamePasswordAuthenticationToken(ud.getUsername(),ud.getUsername());
	}
	
	
}

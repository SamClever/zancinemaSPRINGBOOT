package com.zancinema.Zancinema_official.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zancinema.Zancinema_official.request.UserRequest;
import com.zancinema.Zancinema_official.services.UserService;


@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	// @Autowired
	// private AuthenticationManager authenticationManager;

	// @Autowired
	// private JWTService jwtService;

	@PostMapping("/addNew")
	public ResponseEntity<String> addNewUser(@RequestBody UserRequest userEntryDto) {
		try {
			String result = userService.addUser(userEntryDto);
			return new ResponseEntity<>(result, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// @PostMapping("/getToken")
	// public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
	// 	Authentication authentication = authenticationManager.authenticate(
	// 			new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

	// 	if (authentication.isAuthenticated()) {
	// 		return jwtService.generateToken(authRequest.getUsername());
	// 	}

	// 	throw new UsernameNotFoundException("invalid user details.");
	// }
}

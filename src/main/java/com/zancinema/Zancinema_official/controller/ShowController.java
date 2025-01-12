package com.zancinema.Zancinema_official.controller;


import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zancinema.Zancinema_official.request.ShowSeatRequest;
import com.zancinema.Zancinema_official.services.ShowService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/show")
public class ShowController {

	@Autowired
	private ShowService showService;

	@PostMapping("/addNew")
	public ResponseEntity<String> addShow(@RequestBody SavedRequest showRequest) {
		try {
			String result = showService.addShow(showRequest);
			return new ResponseEntity<>(result, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/associateSeats")
	public ResponseEntity<String> associateShowSeats(@RequestBody ShowSeatRequest showSeatRequest) {
		try {
			String result = showService.associateShowSeats(showSeatRequest);
			return new ResponseEntity<>(result, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}

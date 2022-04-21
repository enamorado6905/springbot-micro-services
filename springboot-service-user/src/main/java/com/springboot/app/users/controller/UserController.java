package com.springboot.app.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.springboot.app.users.exception.ResourceBadRequestException;
import com.springboot.app.users.exception.ResourceNotFoundException;
import com.springboot.app.users.model.UserModel;
import com.springboot.app.users.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	private ResponseEntity<List<UserModel>> getAllUsers() {
		return userService.findAllUsers();
	}

	@GetMapping("/users/{id}")
	private ResponseEntity<UserModel> getUserById(@PathVariable(value = "id") Long userId)
			throws ResourceNotFoundException {
		return userService.findUserById(userId);
	}

	@PostMapping("/users")
	private ResponseEntity<UserModel> saveUser(@Validated @RequestBody UserModel user)
			throws ResourceBadRequestException {
		return userService.saveUser(user);
	}

	@PutMapping("/users/{id}")
	private ResponseEntity<UserModel> updateUser(@PathVariable(value = "id") Long userId,
			@Validated @RequestBody UserModel userDetails)
			throws ResourceBadRequestException, ResourceNotFoundException {
		return userService.updateUser(userId, userDetails);
	}

	@PatchMapping("/users/{id}")
	private ResponseEntity<UserModel> patchUsers(@PathVariable(value = "id") Long userId,
			@Validated @RequestBody UserModel userDetails)
			throws ResourceBadRequestException, ResourceNotFoundException {
		return userService.patchUser(userId, userDetails);
	}

	@DeleteMapping("/users/{id}")
	private ResponseEntity<UserModel> deleteUser(@PathVariable(value = "id") Long userId)
			throws ResourceNotFoundException {
		return userService.deleteUser(userId);
	}
}

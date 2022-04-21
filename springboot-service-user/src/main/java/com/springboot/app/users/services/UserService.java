package com.springboot.app.users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.app.users.exception.ResourceBadRequestException;
import com.springboot.app.users.exception.ResourceNotFoundException;
import com.springboot.app.users.interfaces.IUser;
import com.springboot.app.users.model.UserModel;
import com.springboot.app.users.repository.UserRepository;

import java.util.List;

@Service
public class UserService implements IUser {

	@Autowired
	private UserRepository userRepository;

	public ResponseEntity<List<UserModel>> findAllUsers() {
		List<UserModel> users = userRepository.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	public ResponseEntity<UserModel> findUserById(Long userId) throws ResourceNotFoundException {
		UserModel user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	public ResponseEntity<UserModel> saveUser(UserModel user) throws ResourceBadRequestException {

		if (user.getEmail() == null || user.getEmail().equals("")) {
			throw new ResourceBadRequestException("Email is required!");
		}
		if (user.getName() == null || user.getName().equals("")) {
			throw new ResourceBadRequestException("Name is required!");
		}
		if (user.getPassword() == null || user.getPassword().equals("")) {
			throw new ResourceBadRequestException("Password is required!");
		}

		UserModel newUser = userRepository.save(user);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);

	}

	public ResponseEntity<UserModel> updateUser(Long userId, UserModel userDetails)
			throws ResourceBadRequestException, ResourceNotFoundException {
		UserModel user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

		if (userDetails.getEmail() == null || userDetails.getEmail().equals("")) {
			throw new ResourceBadRequestException("Email is required!");
		}
		if (userDetails.getName() == null || userDetails.getName().equals("")) {
			throw new ResourceBadRequestException("Name is required!");
		}

		user.setEmail(userDetails.getEmail());
		user.setName(userDetails.getName());

		final UserModel updatedUser = userRepository.save(user);
		return new ResponseEntity<>(updatedUser, HttpStatus.ACCEPTED);

	}

	public ResponseEntity<UserModel> patchUser(Long userId, UserModel userDetails)
			throws ResourceBadRequestException, ResourceNotFoundException {
		UserModel user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

		if (userDetails.getEmail() == null || userDetails.getEmail().equals("") || userDetails.getName() == null
				|| userDetails.getName().equals("")) {
			throw new ResourceBadRequestException("All parameters are null!");
		}

		if (userDetails.getEmail() != null) {
			user.setEmail(userDetails.getEmail());
		}
		if (userDetails.getName() != null) {
			user.setName(userDetails.getName());
		}

		final UserModel updatedUser = userRepository.save(user);
		return new ResponseEntity<>(updatedUser, HttpStatus.ACCEPTED);
	}

	public ResponseEntity<UserModel> deleteUser(Long userId) throws ResourceNotFoundException {
		UserModel removedUser = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

		userRepository.delete(removedUser);
		return new ResponseEntity<>(removedUser, HttpStatus.OK);
	}

}

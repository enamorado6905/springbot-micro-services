package com.springboot.app.users.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.springboot.app.users.exception.ResourceBadRequestException;
import com.springboot.app.users.exception.ResourceNotFoundException;
import com.springboot.app.users.model.UserModel;

public interface IUser {
	public abstract ResponseEntity<List<UserModel>> findAllUsers();

	public abstract ResponseEntity<UserModel> findUserById(Long userId) throws ResourceNotFoundException;

	public abstract ResponseEntity<UserModel> saveUser(UserModel user) throws ResourceBadRequestException;

	public abstract ResponseEntity<UserModel> updateUser(Long userId, UserModel userDetails)
			throws ResourceBadRequestException, ResourceNotFoundException;

	public abstract ResponseEntity<UserModel> patchUser(Long userId, UserModel userDetails)
			throws ResourceBadRequestException, ResourceNotFoundException;

	public abstract ResponseEntity<UserModel> deleteUser(Long userId) throws ResourceNotFoundException;
}

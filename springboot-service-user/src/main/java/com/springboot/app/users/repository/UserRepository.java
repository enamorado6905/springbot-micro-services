package com.springboot.app.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.app.users.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

}

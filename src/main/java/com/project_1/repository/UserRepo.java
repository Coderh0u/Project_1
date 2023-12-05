package com.project_1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project_1.model.User;

public interface UserRepo extends JpaRepository<User, String> {
  Optional<User> findByUserEmail(String email);
}
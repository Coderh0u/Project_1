package com.project_1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project_1.model.Users;

public interface UserRepo extends JpaRepository<Users, String> {
  Optional<Users> findByUserEmail(String email);
}
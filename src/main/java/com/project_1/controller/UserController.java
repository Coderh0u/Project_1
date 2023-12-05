package com.project_1.controller;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project_1.model.User;
import com.project_1.repository.UserRepo;
import com.project_1.resources.UserReq;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class UserController {
  private final UserRepo userRepo;

  public UserController(UserRepo userRepo) {
    this.userRepo = userRepo;
  }

  @PutMapping("/register")
  public ResponseEntity<?> addUser(@RequestBody UserReq request) {

    Optional<User> repeatedUser = userRepo.findByUserEmail(request.getUserEmail());
    if (repeatedUser.isPresent()) {
      return ResponseEntity.status(409).body("Email already taken");
    }

    User newUser = new User();
    newUser.setUserEmail(request.getUserEmail());

    String salt = BCrypt.gensalt(12);
    String hashPwd = BCrypt.hashpw(request.getPassword(), salt);
    newUser.setHashPwd(hashPwd);

    return ResponseEntity.status(200).body(this.userRepo.save(newUser));
  }

  @GetMapping("/all")
  public ResponseEntity<?> getUsers() {
    return ResponseEntity.ok(this.userRepo.findAll());
  }

}

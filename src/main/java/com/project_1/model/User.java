package com.project_1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "app_user")
public class User {
  @Id
  private String userEmail;

  private String hashPwd;

  public User() {
  }

  public User(String userEmail, String hashPwd) {
    this.userEmail = userEmail;
    this.hashPwd = hashPwd;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public String getHashPwd() {
    return hashPwd;
  }

  public void setHashPwd(String hashPwd) {
    this.hashPwd = hashPwd;
  }

}

package com.project_1.resources;

public class UserReq {
  private String userEmail;
  private String password;

  public String getUserEmail() {
    return userEmail;
  }

  public UserReq() {
  }

  public UserReq(String userEmail, String password) {
    this.userEmail = userEmail;
    this.password = password;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}

package com.qlbv.model;



public class User {

  private long userId;
  private String displayName;
  private String username;
  private String password;

  public User() {
  }

  public User(long userId, String displayName, String username, String password) {
    this.userId = userId;
    this.displayName = displayName;
    this.username = username;
    this.password = password;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  @Override
  public String toString(){
    return String.format("User: userId = %s, displayName = %s, username = %s", userId, displayName, username);
  }
}

package com.solvd.bin.user;

import com.solvd.bin.Coordinate;
import com.solvd.bin.user.passwordValidation.PasswordValidator;

import java.util.Objects;

public class User {
  private String userName;
  private String password;
  private Coordinate position;
  private PasswordValidator validator;

  public User(String userName, String password, Coordinate position) {
    this.userName = userName;
    this.password = password;
    validator.validateAtributes(userName, password);
    this.position = position;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Coordinate getPosition() {
    return position;
  }

  public void setPosition(Coordinate position) {
    this.position = position;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj.getClass() != this.getClass()) {
      return false;
    }
    final User other = (User) obj;
    if (!Objects.equals(this.userName, other.userName)) {
      return false;
    }
    if (this.password != other.password) {
      return false;
    }
    if (this.position != other.position) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash(userName, password, position);
  }

  @Override
  public String toString() {
    return "Benefit{" +
        "id=" + userName +
        ", benefit='" + password + '\'' +
        ", clients=" + position +
        '}';
  }
}

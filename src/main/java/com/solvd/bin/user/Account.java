package com.solvd.bin.user;

import com.solvd.bin.user.passwordValidation.PasswordValidator;

import java.util.Objects;

public class Account {
  private String userName;
  private String password;
  private PasswordValidator validator;

  public Account(String userName, String password) {
    this.userName = userName;
    this.password = password;
    validator.validateAtributes(userName, password);
  }

  public Account() {

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

  public PasswordValidator getValidator() {
    return validator;
  }

  public void setValidator(PasswordValidator validator) {
    this.validator = validator;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj.getClass() != this.getClass()) {
      return false;
    }
    final Account other = (Account) obj;
    if (!Objects.equals(this.userName, other.userName)) {
      return false;
    }
    if (this.password != other.password) {
      return false;
    }
    if (this.validator != other.validator) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash(userName, password, validator);
  }

  @Override
  public String toString() {
    return "Benefit{" +
        "id=" + userName +
        ", benefit='" + password +
        ", benefit='" + validator +
        '}';
  }
}

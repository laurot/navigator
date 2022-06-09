package com.solvd.bin.user;

import com.solvd.bin.user.passwordValidation.PasswordValidator;

import java.util.Objects;

public class Account {
  private int id;
  private String userName;
  private String password;
  private PasswordValidator validator;

  public Account(String userName, String password) {
    this.userName = userName;
    this.password = password;
    validator.validateAtributes(userName, password);
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

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Account account = (Account) o;
    return id == account.id && Objects.equals(userName, account.userName) && Objects.equals(password, account.password) && Objects.equals(validator, account.validator);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userName, password, validator);
  }

  @Override
  public String toString() {
    return "Account{" +
            "id=" + id +
            ", userName='" + userName + '\'' +
            ", password='" + password + '\'' +
            ", validator=" + validator +
            '}';
  }
}

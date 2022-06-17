package com.solvd.bin.user;

import java.util.Objects;

public class Account {
  private int id;
  private String userName;
  private String password;

  public Account(String userName, String password) {
    this.userName = userName;
    this.password = password;
    //new PasswoerdValidator.validateAtributes(userName, password);
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
    return id == account.id && Objects.equals(userName, account.userName) && Objects.equals(password, account.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userName, password);
  }

  @Override
  public String toString() {
    return "Account{" +
        "id=" + id +
        ", userName='" + userName + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}

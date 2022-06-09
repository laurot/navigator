package com.solvd.bin.user;

import com.solvd.bin.Coordinate;

import java.util.Objects;

public class User {
  private int id;
  private Coordinate position;
  private Account account;

  public User(Coordinate position, Account account) {
    this.position = position;
    this.account = account;
  }

  public User() {

  }

  public Coordinate getPosition() {
    return position;
  }

  public void setPosition(Coordinate position) {
    this.position = position;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
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
    User user = (User) o;
    return id == user.id && Objects.equals(position, user.position) && Objects.equals(account, user.account);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, position, account);
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", position=" + position +
            ", account=" + account +
            '}';
  }
}

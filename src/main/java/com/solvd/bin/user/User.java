package com.solvd.bin.user;

import com.solvd.bin.Coordinate;

import java.util.Objects;

public class User {
  private Coordinate position;
  private Account account;

  public User(Coordinate position, Account account) {
    this.position = position;
    this.account = account;
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

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj.getClass() != this.getClass()) {
      return false;
    }
    final User other = (User) obj;
    if (!Objects.equals(this.position, other.position)) {
      return false;
    }
    if (this.account != other.account) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash(position, account);
  }

  @Override
  public String toString() {
    return "Benefit{" +
        "id=" + position +
        ", benefit='" + account +
        '}';
  }
}

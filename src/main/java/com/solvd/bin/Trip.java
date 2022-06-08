package com.solvd.bin;

import com.solvd.bin.user.User;

public class Trip {
  private Transport transport;
  private User user;
  private Path path;
  
  public Transport getTransport() {
    return transport;
  }
  public void setTransport(Transport transport) {
    this.transport = transport;
  }
  public User getUser() {
    return user;
  }
  public void setUser(User user) {
    this.user = user;
  }
  public Path getPath() {
    return path;
  }
  public void setPath(Path path) {
    this.path = path;
  }
}
package com.solvd.bin;

import com.solvd.bin.user.User;

import java.util.Objects;

public class Trip {
  private int id;
  private Transport transport;
  private User user;
  private Path path;

  public Trip(Transport transport, User user, Path path) {
    this.transport = transport;
    this.user = user;
    this.path = path;
  }

  public Trip() {
  }

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
    Trip trip = (Trip) o;
    return id == trip.id && Objects.equals(transport, trip.transport) && Objects.equals(user, trip.user) && Objects.equals(path, trip.path);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, transport, user, path);
  }

  @Override
  public String toString() {
    return "Trip{" +
            "id=" + id +
            ", transport=" + transport +
            ", user=" + user +
            ", path=" + path +
            '}';
  }
}
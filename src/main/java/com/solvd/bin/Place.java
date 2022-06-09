package com.solvd.bin;

import java.util.Objects;

public class Place {
  private int id;
  private String name;
  private Coordinate location;

  public Place(String name, Coordinate location) {
    this.name = name;
    this.location = location;
  }

  public Place() {
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Coordinate getLocation() {
    return location;
  }
  public void setLocation(Coordinate location) {
    this.location = location;
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
    Place place = (Place) o;
    return id == place.id && Objects.equals(name, place.name) && Objects.equals(location, place.location);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, location);
  }

  @Override
  public String toString() {
    return "Place{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", location=" + location +
            '}';
  }
}

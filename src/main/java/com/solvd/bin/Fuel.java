package com.solvd.bin;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Fuel {
  @JsonProperty("id")
  private int id;
  @JsonProperty("type")
  private String type;
  @JsonProperty("price")
  private double price;

  public Fuel(String type, int price) {
    this.type = type;
    this.price = price;
  }

  public Fuel() {
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
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
    Fuel fuel = (Fuel) o;
    return id == fuel.id && price == fuel.price && Objects.equals(type, fuel.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, price);
  }

  @Override
  public String toString() {
    return "Fuel{" +
        "id=" + id +
        ", type='" + type + '\'' +
        ", price=" + price +
        '}';
  }
}
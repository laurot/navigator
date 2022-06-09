package com.solvd.bin;

import java.util.Objects;

public class Transport {
  private String type;
  private int speed;
  private double consumption;
  private Fuel fuel;

  public Transport(String type, int speed, double consumption, Fuel fuel) {
    this.type = type;
    this.speed = speed;
    this.consumption = consumption;
    this.fuel = fuel;
  }

  public Transport() {
  }

  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public int getSpeed() {
    return speed;
  }
  public void setSpeed(int speed) {
    this.speed = speed;
  }
  public double getConsumption() {
    return consumption;
  }
  public void setConsumption(double consumption) {
    this.consumption = consumption;
  }
  public Fuel getFuel() {
    return fuel;
  }
  public void setFuel(Fuel fuel) {
    this.fuel = fuel;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Transport transport = (Transport) o;
    return speed == transport.speed && Double.compare(transport.consumption, consumption) == 0 && Objects.equals(type, transport.type) && Objects.equals(fuel, transport.fuel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, speed, consumption, fuel);
  }

  @Override
  public String toString() {
    return "Transport{" +
            "type='" + type + '\'' +
            ", speed=" + speed +
            ", consumption=" + consumption +
            ", fuel=" + fuel +
            '}';
  }
}

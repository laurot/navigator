package com.solvd.bin;

public class Transport {
  private String type;
  private int speed;
  private double consumption;
  private Fuel fuel;
  
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
}

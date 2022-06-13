package com.solvd.bin;

import java.util.Objects;

public class FuelType {
    private String gasoline;
    private String diesel;
    private String ethanol;

    public FuelType(String gasoline, String diesel, String ethanol) {
        this.gasoline = gasoline;
        this.diesel = diesel;
        this.ethanol = ethanol;
    }

    public FuelType() {
    }

    public String getGasoline() {
        return gasoline;
    }

    public void setGasoline(String gasoline) {
        this.gasoline = gasoline;
    }

    public String getDiesel() {
        return diesel;
    }

    public void setDiesel(String diesel) {
        this.diesel = diesel;
    }

    public String getEthanol() {
        return ethanol;
    }

    public void setEthanol(String ethanol) {
        this.ethanol = ethanol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuelType fuelType = (FuelType) o;
        return Objects.equals(gasoline, fuelType.gasoline) && Objects.equals(diesel, fuelType.diesel) && Objects.equals(ethanol, fuelType.ethanol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gasoline, diesel, ethanol);
    }

    @Override
    public String toString() {
        return "FuelType{" +
                "gasoline='" + gasoline + '\'' +
                ", diesel='" + diesel + '\'' +
                ", ethanol='" + ethanol + '\'' +
                '}';
    }
}

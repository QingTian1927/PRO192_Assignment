package com.example.models;

public class Car implements Comparable<Car> {
    private String carId;
    private Brand brand;
    private String color;
    private String frameId;
    private String engineId;

    public Car() {}

    public Car(String carId, Brand brand, String color, String frameId, String engineId) {
        this.carId = carId;
        this.brand = brand;
        this.color = color;
        this.frameId = frameId;
        this.engineId = engineId;
    }

    public String getCarId() {
        return this.carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public Brand getBrand() {
        return this.brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFrameId() {
        return this.frameId;
    }

    public void setFrameId(String frameId) {
        this.frameId = frameId;
    }

    public String getEngineId() {
        return this.engineId;
    }

    public void setEngineId(String engineId) {
        this.engineId = engineId;
    }

    @Override
    public String toString() {
        return String.format(
                "%s, %s, %s, %s, %s",
                this.carId,
                this.brand.getBrandID(),
                this.color,
                this.frameId,
                this.engineId
        );
    }

    public String screenString() {
        return String.format(
                "%s\n%s, %s, %s, %s",
                this.brand,
                this.carId,
                this.color,
                this.frameId,
                this.engineId
        );
    }

    @Override
    public int compareTo(Car other) {
        int brandComparison = (
                this.brand.getBrandName().compareTo(other.brand.getBrandName())
        );
        if (brandComparison != 0) {
            return brandComparison;
        }
        return this.carId.compareTo(other.carId);
    }
}

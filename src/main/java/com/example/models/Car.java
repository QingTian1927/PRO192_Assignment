package com.example.models;

public class Car implements Comparable<Car> {
    public static final String CAR_PROPERTY_SEPARATOR = ", ";
    public static final char FRAMEID_PREFIX = 'F';
    public static final int FRAMEID_LENGTH = 5;
    public static final char ENGINEID_PREFIX = 'E';
    public static final int ENGINEID_LENGTH = 5;

    private String carId;
    private Brand brand;
    private String color;
    private String frameId;
    private String engineId;

    private static boolean isNotNumber(char ch) {
        return (ch < '0' || ch > '9');
    }

    public static boolean isValidFrameId(String frameId) {
        if (frameId.isEmpty()) {
            return false;
        }

        if (frameId.charAt(0) != FRAMEID_PREFIX) {
            return false;
        }

        if (frameId.length() != FRAMEID_LENGTH) {
            return false;
        }

        for (int i = 1; i < FRAMEID_LENGTH; i++) {
            if (isNotNumber(frameId.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidEngineId(String engineId) {
        if (engineId.isEmpty()) {
            return false;
        }

        if (engineId.charAt(0) != ENGINEID_PREFIX) {
            return false;
        }

        if (engineId.length() != ENGINEID_LENGTH) {
            return false;
        }

        for (int i = 1; i < ENGINEID_LENGTH; i++) {
            if (isNotNumber(engineId.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public Car() {}

    public Car(String carId, Brand brand, String color, String frameId, String engineId) {
        this.carId = carId;
        this.brand = brand;
        this.color = color;
        this.frameId = frameId;
        this.engineId = engineId;
    }

    public Car (Car car) {
        this(car.carId, car.brand, car.color, car.frameId, car.engineId);
    }

    public Car getCopy() {
        return new Car(this);
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

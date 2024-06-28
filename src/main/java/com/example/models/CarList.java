package com.example.models;

import java.util.ArrayList;

public class CarList {
    private BrandList brandList;
    private ArrayList<Car> carList;

    public CarList(BrandList brandList) {
        this.carList = new ArrayList<Car>();
        this.brandList = brandList;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Car car : carList) {
            result.append(car.toString()).append('\n');
        }
        return result.toString();
    }

    public String screenString() {
        StringBuilder result = new StringBuilder();
        for (Car car : carList) {
            result.append(car.screenString()).append('\n');
        }
        return result.toString();
    }

    public int searchId(String carId) {
        for (int i = 0; i < this.carList.size(); i++) {
            if (this.carList.get(i).getCarId().equals(carId)) {
                return i;
            }
        }
        return -1;
    }

    public int searchFrame(String frameId) {
        for (int i = 0; i < this.carList.size(); i++) {
            if (this.carList.get(i).getFrameId().equals(frameId)) {
                return i;
            }
        }
        return -1;
    }

    public int searchEngine(String engineId) {
        for (int i = 0; i < this.carList.size(); i++) {
            if (this.carList.get(i).getEngineId().equals(engineId)) {
                return i;
            }
        }
        return -1;
    }

    public void addCar() {

    }
}

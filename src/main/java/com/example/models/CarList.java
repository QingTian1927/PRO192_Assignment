package com.example.models;

import com.example.ui.Menu;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CarList {
    private final BrandList brandList;
    private final ArrayList<Car> carList;

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

    public void listCars() {
        ArrayList<Car> clonedCars = new ArrayList<Car>(this.carList.size());
        for (Car car : this.carList) {
            clonedCars.add(car.getCopy());
        }
        Collections.sort(clonedCars);

        for (Car car : clonedCars) {
            System.out.println(car.screenString());
        }
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

    public void printBasedBrandName() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a part of brand name: ");
        String query = sc.nextLine();

        int count = 0;
        for (Car car : this.carList) {
            if (car.getBrand().getBrandName().contains(query)) {
                System.out.println(car.screenString());
                count++;
            }
        }

        if (count == 0) {
            System.out.println("No car is detected!");
        }
    }

    private Brand inputBrand() {
        System.out.println("Choose a car brand from the following list:\n");
        Brand brand = (Brand) Menu.ref_getChoice(this.brandList.getBrandList());
        System.out.println();
        return brand;
    }

    private String inputColor() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the car color: ");
        String color = sc.nextLine();
        System.out.println();
        return color;
    }

    private String inputFrameId() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a new frame ID: ");
        int searchResult = -1;
        String frameId = null;

        do {
            frameId = sc.nextLine();
            if (!Car.isValidFrameId(frameId)) {
                System.out.println("Frame ID must be in the F0000 format!");
                continue;
            }

            searchResult = this.searchFrame(frameId);
            if (searchResult != -1) {
                System.out.println("Frame ID must not be duplicated!");
            }
        } while (searchResult != -1);
        System.out.println();

        return frameId;
    }

    private String inputEngineId() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a new engine ID: ");
        int searchResult = -1;
        String engineId = null;

        do {
            engineId = sc.nextLine();
            if (!Car.isValidEngineId(engineId)) {
                System.out.println("Engine ID must be in the E0000 format!");
                continue;
            }

            searchResult = this.searchEngine(engineId);
            if (searchResult != -1) {
                System.out.println("Engine ID must not be duplicated!");
            }
        } while (searchResult != -1);
        System.out.println();

        return engineId;
    }

    public void addCar() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a new car ID: ");
        int searchResult = -1;
        String carId = null;

        do {
            carId = sc.nextLine();
            searchResult = this.searchId(carId);

            if (searchResult != -1) {
                System.out.println("Car ID must not be duplicated!");
            }
        } while (searchResult != -1);
        System.out.println();

        Brand brand = inputBrand();
        String color = inputColor();
        String frameId = inputFrameId();
        String engineId = inputEngineId();

        this.carList.add(new Car(carId, brand, color, frameId, engineId));
    }

    public boolean removeCar() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the car ID: ");
        String carId = sc.nextLine();

        int index = this.searchId(carId);
        if (index == -1) {
            System.out.println("Not found!");
            return false;
        }

        this.carList.remove(index);
        return true;
    }

    public boolean updateCar() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the car ID: ");
        String carId = sc.nextLine();

        int index = this.searchId(carId);
        if (index == -1) {
            System.out.println("Not found!");
            return false;
        }

        Brand brand = inputBrand();
        String color = inputColor();
        String frameId = inputFrameId();
        String engineId = inputEngineId();

        Car updatedCar = this.carList.get(index);
        updatedCar.setBrand(brand);
        updatedCar.setColor(color);
        updatedCar.setFrameId(frameId);
        updatedCar.setEngineId(engineId);

        return true;
    }

    public boolean saveToFile(String fileName) throws IOException {
        Path saveFile = new File(fileName).toPath();
        try (
                BufferedWriter bw = Files.newBufferedWriter(saveFile)
        ) {
            for (Car car : this.carList) {
                bw.write(car.toString());
                bw.newLine();
            }
        }
        return true;
    }

    public boolean readFromFile(String fileName) throws IOException {
        File saveFile = new File(fileName);
        if (!saveFile.exists()) {
            throw new FileNotFoundException("Save file does not exist");
        }

        boolean hasCorruptedEntries = false;
        try (
                FileReader fr = new FileReader(saveFile);
                BufferedReader br = new BufferedReader(fr)
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(Car.CAR_PROPERTY_SEPARATOR);

                String carId = fields[0];
                String brandId = fields[1];
                String color = fields[2];
                String frameId = fields[3];
                String engineId = fields[4];

                int index = this.brandList.searchId(brandId);
                if (index == -1) {
                    hasCorruptedEntries = true;
                    continue;
                }
                Brand brand = this.brandList.getBrandList().get(index);

                this.carList.add(new Car(carId, brand, color, frameId, engineId));
            }
        }

        return hasCorruptedEntries;
    }
}

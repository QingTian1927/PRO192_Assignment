package com.example;

import com.example.models.BrandList;
import com.example.models.CarList;
import com.example.ui.UserInterface;

import java.io.IOException;
import java.util.Scanner;

public class CarManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BrandList brandList = new BrandList();
        CarList carList = new CarList(brandList);
        UserInterface.clearScreen();

        System.out.println(System.getProperty("user.dir"));
        try {
            brandList.readFromFile("resources/brands.txt");
        } catch (IOException e) {
            System.out.println("FATAL: Failed to read brands.txt");
            System.out.println("FATAL: " + e);
            System.exit(1);
        }

        try {
            carList.readFromFile("resources/cars.txt");
        } catch (IOException e) {
            System.out.println("FATAL: Failed to read cars.txt");
            System.out.println("FATAL: " + e);
            System.exit(1);
        }

        boolean isRunning = true;
        while (isRunning) {
            UserInterface.printTitleCard();
            int choice = UserInterface.mainMenuOptions();

            switch (choice) {
                case 1:
                    manageBrands(brandList);
                    break;
                case 2:
                    manageCars(carList);
                    break;
                case 0:
                    isRunning = false;
                    break;
            }
        }
    }

    public static void manageBrands(BrandList brandList) {
        while (true) {
            UserInterface.clearScreen();
            UserInterface.printTitleCard();
            int choice = UserInterface.brandMenuOptions();
            System.out.println();

            switch (choice) {
                case 1:
                    brandList.listBrands();
                    UserInterface.pressEnterToContinue();
                    break;
                case 2:
                    brandList.addBrand();
                    UserInterface.pressEnterToContinue();
                    break;
                case 3:
                    brandList.updateBrand();
                    UserInterface.pressEnterToContinue();
                    break;
                case 4:
                    brandList.searchBrand();
                    UserInterface.pressEnterToContinue();
                    break;
                case 5:
                    break;
                case 0:
                    return;
            }
        }
    }

    public static void manageCars(CarList carList) {
        while (true) {
            UserInterface.clearScreen();
            UserInterface.printTitleCard();
            int choice = UserInterface.brandMenuOptions();

            switch (choice) {
                case 1:
                    carList.listCars();
                    UserInterface.pressEnterToContinue();
                    break;
                case 2:
                    carList.addCar();
                    UserInterface.pressEnterToContinue();
                    break;
                case 3:
                    carList.updateCar();
                    UserInterface.pressEnterToContinue();
                    break;
                case 4:
                    carList.removeCar();
                    UserInterface.pressEnterToContinue();
                    break;
                case 5:
                    carList.printBasedBrandName();
                    UserInterface.pressEnterToContinue();
                    break;
                case 6:
                    break;
                case 0:
                    return;
            }
        }
    }
}

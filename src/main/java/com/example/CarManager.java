package com.example;

import com.example.models.BrandList;
import com.example.models.CarList;
import com.example.ui.UserInterface;

import java.io.IOException;

public class CarManager {
    private static final String BRANDS_SOURCE_FILE = "brands.txt";
    private static final String CARS_SOURCE_FILE = "cars.txt";

    public static void main(String[] args) {
        BrandList brandList = new BrandList();
        CarList carList = new CarList(brandList);
        System.out.println(System.getProperty("user.dir"));

        try {
            brandList.readFromFile(CarManager.BRANDS_SOURCE_FILE);
        } catch (IOException e) {
            System.out.println("FATAL: Failed to read brands.txt");
            System.out.println("CAUSE: " + e);
            System.exit(1);
        }

        try {
            carList.readFromFile(CarManager.CARS_SOURCE_FILE);
        } catch (IOException e) {
            System.out.println("FATAL: Failed to read cars.txt");
            System.out.println("CAUSE: " + e);
            System.exit(1);
        }

        String message = null;
        boolean isRunning = true;

        while (isRunning) {
            UserInterface.clearScreen();
            UserInterface.printTitleCard();
            if (message != null) {
                UserInterface.printMessage(message);
                message = null;
            }

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
                default:
                    message = "Invalid choice! Try again!";
            }
        }
    }

    public static void manageBrands(BrandList brandList) {
        String message = null;

        while (true) {
            UserInterface.clearScreen();
            UserInterface.printTitleCard();
            if (message != null) {
                UserInterface.printMessage(message);
                message = null;
            }

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
                    saveBrandsHandler(brandList);
                    break;
                case 0:
                    if (!brandList.isModified()) {
                        return;
                    }

                    System.out.println("You have unsaved changes!");
                    boolean goSaveFile = UserInterface.yesNoQuestion("Do you want to save them? [y/n]: ");
                    if (goSaveFile) {
                        saveBrandsHandler(brandList);
                    }
                    return;
                default:
                    message = "Invalid choice! Try again!";
            }
        }
    }

    public static void saveBrandsHandler(BrandList brandList) {
        System.out.printf("\nSaving contents to %s...\n", CarManager.BRANDS_SOURCE_FILE);
        try {
            brandList.saveToFile(CarManager.BRANDS_SOURCE_FILE);
            System.out.println("Successfully saved contents to file.\n");
            UserInterface.pressEnterToContinue();
        } catch (IOException e) {
            System.out.println("FATAL: Failed to saved contents to file!");
            System.out.println("CAUSE: " + e.getMessage());
            System.exit(1);
        }
    }

    public static void manageCars(CarList carList) {
        String message = null;

        while (true) {
            UserInterface.clearScreen();
            UserInterface.printTitleCard();
            if (message != null) {
                UserInterface.printMessage(message);
                message = null;
            }

            int choice = UserInterface.carMenuOptions();
            System.out.println();

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
                    saveCarsHandler(carList);
                    break;
                case 0:
                    if (!carList.isModified()) {
                        return;
                    }

                    System.out.println("You have unsaved changes!");
                    boolean goSaveFile = UserInterface.yesNoQuestion("Do you want to save them? [y/n]: ");
                    if (goSaveFile) {
                        saveCarsHandler(carList);
                    }
                    return;
                default:
                    message = "Invalid choice! Try again!";
            }
        }
    }

    public static void saveCarsHandler(CarList carList) {
        System.out.printf("\nSaving contents to %s...\n", CarManager.CARS_SOURCE_FILE);
        try {
            carList.saveToFile(CarManager.CARS_SOURCE_FILE);
            System.out.println("Successfully saved contents to file.\n");
            UserInterface.pressEnterToContinue();
        } catch (IOException e) {
            System.out.println("FATAL: Failed to saved contents to file!");
            System.out.println("CAUSE: " + e.getMessage());
            System.exit(1);
        }
    }
}

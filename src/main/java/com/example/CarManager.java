package com.example;

import com.example.models.BrandList;
import com.example.models.CarList;
import com.example.ui.UserInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CarManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BrandList brandList = new BrandList();
        CarList carList = new CarList(brandList);

        try {
            brandList.readFromFile("src/main/resources/brands.txt");
        } catch (IOException e) {
            System.out.println("FATAL: Failed to read brands.txt");
            System.out.println("FATAL: " + e);
            System.exit(1);
        }

        try {
            carList.readFromFile("src/main/resources/cars.txt");
        } catch (IOException e) {
            System.out.println("FATAL: Failed to read cars.txt");
            System.out.println("FATAL: " + e);
            System.exit(1);
        }

        UserInterface.printTitleCard();
        int choice = UserInterface.mainMenuOptions();

        switch (choice) {
            case 1:
                break;
            case 2:
                break;
            case 0:
                break;
        }
    }
}

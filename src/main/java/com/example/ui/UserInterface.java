package com.example.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class UserInterface {
    private UserInterface() {}

    public static void printTitleCard() {
        System.out.println("----------------------------");
        System.out.println("PRO192 Minh Trang CarManager");
        System.out.println("----------------------------\n");
    }

    public static int mainMenuOptions() {
        System.out.println("1. Manage brands");
        System.out.println("2. Manage cars");
        System.out.println("0. Exit\n");

        Scanner sc = new Scanner(System.in);
        int choice = -1;

        while (choice == -1) {
            try {
                System.out.print("Select an option: ");
                choice = sc.nextInt();
                sc.nextLine();

                if (choice < '0' || choice > '2') {
                    System.out.println("Invalid choice! Try again!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice! Try again!");
                sc.nextLine();
            }

        }
        return choice;
    }
}

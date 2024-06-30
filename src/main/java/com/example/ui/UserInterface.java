package com.example.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class UserInterface {
    private UserInterface() {}

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void pressEnterToContinue() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Press <ENTER> to continue: ");
        sc.nextLine();
    }

    public static boolean yesNoQuestion(String prompt) {
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine().toLowerCase();

        while (true) {
            System.out.print(prompt);
            if (choice.equals("y") || choice.equals("yes")) {
                return true;
            }
            if (choice.equals("n") || choice.equals("no")) {
                return false;
            }
            System.out.println("Invalid choice! Try again!");
        }
    }

    public static void printTitleCard() {
        System.out.println("----------------------------");
        System.out.println("PRO192 Minh Trang CarManager");
        System.out.println("----------------------------\n");
    }

    public static void printMessage(String message) {
        System.out.println("> " + ((message == null) ? "" : message) + "\n");
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

                if (choice < 0 || choice > 2) {
                    System.out.println("Invalid choice! Try again!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice! Try again!");
                sc.nextLine();
            }

        }
        return choice;
    }

    public static int brandMenuOptions() {
        System.out.println("1. List all brands");
        System.out.println("2. Add a brand");
        System.out.println("3. Update a brand");
        System.out.println("4. Search for a brand");
        System.out.println("5. Save to file");
        System.out.println("0. Exit\n");

        Scanner sc = new Scanner(System.in);
        int choice = -1;

        while (choice == -1) {
            try {
                System.out.print("Select an option: ");
                choice = sc.nextInt();
                sc.nextLine();

                if (choice < 0 || choice > 5) {
                    System.out.println("Invalid choice! Try again!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice! Try again!");
                sc.nextLine();
            }

        }
        return choice;
    }

    public static int carMenuOptions() {
        System.out.println("1. List all cars");
        System.out.println("2. Add a car");
        System.out.println("3. Update a car");
        System.out.println("4. Remove a car");
        System.out.println("5. Search for cars based on brand");
        System.out.println("6. Save to file");
        System.out.println("0. Exit\n");

        Scanner sc = new Scanner(System.in);
        int choice = -1;

        while (choice == -1) {
            try {
                System.out.print("Select an option: ");
                choice = sc.nextInt();
                sc.nextLine();

                if (choice < 0 || choice > 6) {
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

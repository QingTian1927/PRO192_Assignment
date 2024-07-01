package com.example.ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public final class Menu {
    private Menu() {
    }

    public static <E> void listOptions(ArrayList<E> options) {
        if (options.isEmpty()) {
            return;
        }

        for (int i = 0; i < options.size(); i++) {
            System.out.printf(
                    "%d. %s\n",
                    i + 1,
                    options.get(i).toString()
            );
        }

        System.out.println();
        System.out.printf("Please choose an option 1..%d: ", options.size());
    }

    public static <E> int int_getChoice(ArrayList<E> options) {
        if (options.isEmpty()) {
            return -1;
        }

        listOptions(options);
        Scanner sc = new Scanner(System.in);
        int choice = -1;

        while (choice == -1) {
            try {
                choice = sc.nextInt();
                sc.nextLine();

                if (choice < 0 || choice > options.size()) {
                    System.out.println("Invalid choice! Try again!");
                    sc.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice! Try again!");
                sc.nextLine();
            }
        }
        return choice;
    }

    public static <E> E ref_getChoice(ArrayList<E> options) {
        if (options.isEmpty()) {
            return null;
        }

        int choice;
        do {
            choice = int_getChoice(options);
        } while (choice < 0 || choice > options.size());

        return options.get(choice - 1);
    }
}

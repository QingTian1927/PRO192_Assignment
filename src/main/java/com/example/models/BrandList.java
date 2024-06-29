package com.example.models;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BrandList {
    private final ArrayList<Brand> brandList;

    public BrandList(ArrayList<Brand> brandList) {
        this.brandList = brandList;
    }

    public BrandList() {
        this(new ArrayList<Brand>());
    }

    public ArrayList<Brand> getBrandList() {
        return brandList;
    }

    public int searchId(String brandId) {
        for (int i = 0; i < this.brandList.size(); i++) {
            if (this.brandList.get(i).getBrandID().equals(brandId)) {
                return i;
            }
        }
        return -1;
    }

    private String inputBrandName() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter brand name: ");
        System.out.println();
        return sc.nextLine();
    }

    private String inputSoundBrand() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter sound brand: ");
        System.out.println();
        return sc.nextLine();
    }

    private double inputPrice() {
        Scanner sc = new Scanner(System.in);
        double price = 0;

        boolean isGettingInput = true;
        while (isGettingInput) {
            try {
                System.out.print("Enter price: ");
                price = sc.nextDouble();

                if (price <= 0) {
                    System.out.println("Price must be a positive real number!");
                    sc.nextLine();
                    continue;
                }

                sc.nextLine();
                isGettingInput = false;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Try again!");
                sc.nextLine();
            }
        }
        System.out.println();

        return price;
    }

    public void addBrand() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a new brand ID: ");
        int searchResult = -1;
        String brandId = null;

        do {
            brandId = sc.nextLine();
            searchResult = this.searchId(brandId);

            if (searchResult != -1) {
                System.out.println("Brand ID must not be duplicated!");
            }
        } while (searchResult != -1);
        System.out.println();

        String brandName = inputBrandName();
        String soundBrand = inputSoundBrand();
        double price = inputPrice();

        this.brandList.add(new Brand(brandId, brandName, soundBrand, price));
    }

    public void updateBrand() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the brand ID: ");
        String brandId = sc.nextLine();

        int index = this.searchId(brandId);
        if (index == -1) {
            System.out.println("Not found!");
            return;
        }

        String brandName = inputBrandName();
        String soundBrand = inputSoundBrand();
        double price = inputPrice();

        Brand updatedBrand = this.brandList.get(index);
        updatedBrand.setBrandName(brandName);
        updatedBrand.setSoundBrand(soundBrand);
        updatedBrand.setPrice(price);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Brand brand : this.brandList) {
            result.append(brand.toString()).append('\n');
        }
        return result.toString();
    }

    public void listBrands() {
        System.out.println(this.toString());
    }

    public boolean saveToFile(String fileName) throws IOException {
        Path saveFile = new File(fileName).toPath();
        try (
                BufferedWriter bw = Files.newBufferedWriter(saveFile)
        ) {
            for (Brand brand : this.brandList) {
                bw.write(brand.toString());
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

        boolean hasNoCorruptedEntries = true;
        try (
                FileReader fr = new FileReader(saveFile);
                BufferedReader br = new BufferedReader(fr)
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(Brand.BRAND_PROPERTY_SEPARATOR);

                String id = fields[0];
                String name = fields[1];
                String sound = fields[2];
                double price = Double.parseDouble(br.readLine());

                this.brandList.add(new Brand(id, name, sound, price));
            }
        }
        return hasNoCorruptedEntries;
    }
}

package com.example.models;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

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

    public void addBrand(Brand brand) {
        if (brand.getBrandName().isEmpty() || brand.getBrandName() == null) {
            throw new IllegalArgumentException("Brand name must not be blank");
        }
        if (brand.getSoundBrand().isEmpty() || brand.getSoundBrand() == null) {
            throw new IllegalArgumentException("Sound brand must not be blank");
        }
        if (brand.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be a positive real number");
        }
        if (this.searchId(brand.getBrandID()) != -1) {
            throw new IllegalArgumentException("Brand ID can not exist in the list");
        }

        this.brandList.add(brand);
    }

    public void updateBrand(Brand brand) {
        int index = this.searchId(brand.getBrandID());

        if (index == -1) {
            throw new IllegalArgumentException("Brand ID does not exist");
        }
        if (brand.getBrandName().isEmpty() || brand.getBrandName() == null) {
            throw new IllegalArgumentException("Brand name must not be blank");
        }
        if (brand.getSoundBrand().isEmpty() || brand.getSoundBrand() == null) {
            throw new IllegalArgumentException("Sound brand must not be blank");
        }
        if (brand.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be a positive real number");
        }

        this.brandList.set(index, brand);
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

                try {
                    this.addBrand(new Brand(id, name, sound, price));
                } catch (IllegalArgumentException e) {
                    hasNoCorruptedEntries = false;
                }
            }
        }
        return hasNoCorruptedEntries;
    }
}

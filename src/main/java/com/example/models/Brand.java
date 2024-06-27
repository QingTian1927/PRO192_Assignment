package com.example.models;

public class Brand {
    public static final String BRAND_PROPERTY_SEPARATOR = ", ";

    private String brandID;
    private String brandName;
    private String soundBrand;
    private double price;

    public Brand() {}

    public Brand(String brandID, String brandName, String soundBrand, double price) {
        this.brandID = brandID;
        this.brandName = brandName;
        this.soundBrand = soundBrand;
        this.price = price;
    }

    public String getBrandID() {
        return this.brandID;
    }

    @Override
    public String toString() {
        return String.format(
                "%s, %s, %s, %f",
                this.brandID,
                this.brandName,
                this.soundBrand,
                this.price
        );
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public String getBrandName() {
        return this.brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSoundBrand() {
        return this.soundBrand;
    }

    public void setSoundBrand(String soundBrand) {
        this.soundBrand = soundBrand;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

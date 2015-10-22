package com.example.abcd.carreminders;


public class Car {
    private int ID;
    private String licence;
    private String brand;
    private String usage;
    private String insurance;
    private String inspection;
    private String tax;
    private String fire;
    private String medical;
    private String rate;

    public Car(){

    }

    public Car(String licence, String brand, String usage, String insurance,
               String inspection, String tax, String fire, String medical, String rate) {
        this.ID = ID;
        this.licence = licence;
        this.brand = brand;
        this.usage = usage;
        this.insurance = insurance;
        this.inspection = inspection;
        this.tax = tax;
        this.fire = fire;
        this.medical = medical;
        this.rate = rate;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getInspection() {
        return inspection;
    }

    public void setInspection(String inspection) {
        this.inspection = inspection;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getFire() {
        return fire;
    }

    public void setFire(String fire) {
        this.fire = fire;
    }

    public String getMedical() {
        return medical;
    }

    public void setMedical(String medical) {
        this.medical = medical;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Car{" +
                "ID=" + ID +
                ", licence='" + licence + '\'' +
                ", brand='" + brand + '\'' +
                ", usage='" + usage + '\'' +
                ", insurance='" + insurance + '\'' +
                ", inspection='" + inspection + '\'' +
                ", tax='" + tax + '\'' +
                ", fire='" + fire + '\'' +
                ", medical='" + medical + '\'' +
                ", rate='" + rate + '\'' +
                '}';
    }
}

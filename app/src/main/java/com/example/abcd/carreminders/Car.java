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
    private String oil;
    private String parking;
    private String eairfilter;
    private String cairfilter;
    private String battery;
    private String antifreeze;
    private String tire;
    private String wiper;

    public Car(){

    }

    public Car(String licence, String brand, String usage, String insurance, String inspection,
               String tax, String fire, String medical, String rate, String oil, String parking,
               String eairfilter, String cairfilter, String battery, String antifreeze, String tire, String wiper) {
        this.licence = licence;
        this.brand = brand;
        this.usage = usage;
        this.insurance = insurance;
        this.inspection = inspection;
        this.tax = tax;
        this.fire = fire;
        this.medical = medical;
        this.rate = rate;
        this.oil = oil;
        this.parking = parking;
        this.eairfilter = eairfilter;
        this.cairfilter = cairfilter;
        this.battery = battery;
        this.antifreeze = antifreeze;
        this.tire = tire;
        this.wiper = wiper;
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

    public String getOil() {
        return oil;
    }

    public void setOil(String oil) {
        this.oil = oil;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public String getEairfilter() {
        return eairfilter;
    }

    public void setEairfilter(String eairfilter) {
        this.eairfilter = eairfilter;
    }

    public String getCairfilter() {
        return cairfilter;
    }

    public void setCairfilter(String cairfilter) {
        this.cairfilter = cairfilter;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getAntifreeze() {
        return antifreeze;
    }

    public void setAntifreeze(String antifreeze) {
        this.antifreeze = antifreeze;
    }

    public String getTire() {
        return tire;
    }

    public void setTire(String tire) {
        this.tire = tire;
    }

    public String getWiper() {
        return wiper;
    }

    public void setWiper(String wiper) {
        this.wiper = wiper;
    }

    /*    @Override
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
    }*/

    //made it this way so we can use it in ViewCarsActivily Listview adapter
    public String toString(){
        return String.valueOf(this.ID);
    }

}

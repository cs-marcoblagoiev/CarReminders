package com.mmproduction.abcd.carreminders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event {
    private String licence;
    private String date;
    private String name;


    public Event(String licence, String date, String name) {
        this.licence = licence;
        this.date = date;
        this.name = name;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Event{" +
                "licence='" + licence + '\'' +
                ", date='" + date + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Date getRealDate(){
        SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
        Date date2=null;
        try {
            date2 = formatter.parse(date);
        }
        catch (ParseException e){

        }
        return date2;
    }
}

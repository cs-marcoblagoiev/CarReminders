package com.example.abcd.carreminders;

import java.util.Comparator;

//implementing a way to sort the events
public class CustomComparator implements Comparator<Event> {
    @Override
    public int compare(Event o1, Event o2) {
        return o1.getRealDate().compareTo(o2.getRealDate());
    }
}

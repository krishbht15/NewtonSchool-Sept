package com.company.oops.practo;

import java.util.ArrayList;

public class Patient {

    public String name;
    public int age;
    public ArrayList<Slot> bookedSlots;

    public Patient(String name, int age) {
        this.name = name;
        this.age = age;
        this.bookedSlots = new ArrayList<>();
    }
}

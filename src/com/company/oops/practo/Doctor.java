package com.company.oops.practo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Doctor {

    public String name;
    public Specialization specialization;
    public ArrayList<Slot> slots;

    public Doctor(String name, Specialization specialization) {
        this.name = name;
        this.specialization = specialization;
        this.slots = new ArrayList<>();
    }

    public Slot createSlot(int hours, int mins) {
        Date newStart = new Date(2022, 2, 8, hours, mins);
        Date newEnd = new Date(2022, 2, 8, hours + ((mins + 30) / 60), (mins + 30) % 60);
        for (Slot oldSlot : slots) {
            if (!((oldSlot.start.after(newStart) && oldSlot.start.after(newEnd)) ||
                    (oldSlot.start.before(newStart) && oldSlot.end.before(newStart)))) {
                System.out.println("The given slot " + hours + ":" + mins + " is overlapping an existing slot");
                return null;
            }
        }
        Slot slot = new Slot(newStart, newEnd, this);
        this.slots.add(slot);
        System.out.println(slot + " has been successfully added");
        return slot;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return name.equals(doctor.name) && specialization == doctor.specialization;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, specialization);
    }
}

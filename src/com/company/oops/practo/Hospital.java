package com.company.oops.practo;

import javax.print.Doc;
import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Hospital {

    public static ArrayList<Doctor> doctors;

    public static void main(String[] args) {
        doctors = new ArrayList<>();
        Doctor krish = registerDoctor("Krish", Specialization.CARDIOLOGIST);
        Doctor rajesh = registerDoctor("Rajesh", Specialization.CARDIOLOGIST);
        Doctor notRegistered = registerDoctor("Krish", Specialization.CARDIOLOGIST);
        krish.createSlot(5, 30);
        rajesh.createSlot(5, 10);
        krish.createSlot(6, 45);
        rajesh.createSlot(6, 10);
        krish.createSlot(7, 45);
        rajesh.createSlot(7, 10);
        krish.createSlot(8, 45);
        rajesh.createSlot(8, 10);
//        for (Slot s : getSlotsBySpeciality(Specialization.CARDIOLOGIST)) {
//            System.out.println(s);
//        }
        Patient p = registerPatient("patient", 45);
        Patient p2 = registerPatient("patient2", 45);
        System.out.println(bookAnAppointment(p, krish, 8, 45));
        System.out.println(bookAnAppointment(p2, krish, 8, 45));
        cancelBooking(p, 8, 45);
    }

    public static Doctor registerDoctor(String name, Specialization specialization) {
        Doctor newDoctor = new Doctor(name, specialization);
        for (Doctor doctor : doctors) {
            if (doctor.equals(newDoctor)) {
                System.out.println(doctor + " has already registered.");
                return null;
            }
        }
        doctors.add(newDoctor);
        System.out.println(newDoctor + " has registered successfully");
        return newDoctor;
    }

    public static Patient registerPatient(String name, int age) {
        return new Patient(name, age);
    }

    public static List<Slot> getSlotsBySpeciality(Specialization specialization) {
        List<Slot> ans = new ArrayList<>();
        for (Doctor doctor : doctors) {
            if (doctor.specialization == specialization)
                ans.addAll(doctor.slots);
        }
        Collections.sort(ans);
        return ans;
    }

    public static Slot bookAnAppointment(Patient p, Doctor doctor, int hour, int min) {
        Date date = new Date(2022, 2, 8, hour, min);
        for (Slot s : p.bookedSlots) {
            if (s.start.equals(date)) {
                System.out.println("Patient has already booked a slot for the given time");
                return null;
            }
        }
        Slot bookingSlot = null;
        for (Slot s : doctor.slots) {
            if (s.start.equals(date)) {
                bookingSlot = s;
                break;
            }
        }
        if (bookingSlot == null) {
            System.out.println("Slot doesn't exist for the given doctor.");
        } else if (bookingSlot.bookedBy != null) {
            System.out.println("Slot is already booked, adding you to the waitlist.");
            bookingSlot.addPatientToWaitlist(p);
        } else {
            bookingSlot.bookedBy = p;
            p.bookedSlots.add(bookingSlot);
            System.out.println(p.name + ", You've successfully booked a slot.");
        }
        return bookingSlot;
    }

    public static Slot bookAppointment(Patient p, Slot slot) {
        Date date = slot.start;
        for (Slot s : p.bookedSlots) {
            if (s.start.equals(date)) {
                return null;
            }
        }
        slot.bookedBy = p;
        p.bookedSlots.add(slot);
        System.out.println(p.name + " ,You've successfully booked a slot.");
        return slot;
    }

    public static Slot cancelBooking(Patient p, int hour, int min) {
        Date date = new Date(2022, 2, 8, hour, min);
        Slot bookedSlot = null;
        for (Slot s : p.bookedSlots) {
            if (s.start.equals(date)) {
                bookedSlot = s;
                break;
            }
        }
        if (bookedSlot == null) {
            System.out.println("No slot was found for the given time and patient");
        } else {
            p.bookedSlots.remove(bookedSlot);
            bookedSlot.bookedBy = null;
            System.out.println("Booking has been cancelled.");
            while (!bookedSlot.waitlist.isEmpty()) {
                Patient patient = bookedSlot.waitlist.remove();
                if (bookAppointment(patient, bookedSlot) != null) break;
            }
        }
        return bookedSlot;
    }

}

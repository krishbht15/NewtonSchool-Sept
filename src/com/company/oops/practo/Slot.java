package com.company.oops.practo;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Queue;

public class Slot implements Comparable<Slot> {

    public Date start;
    public Date end;
    public Doctor doctor;
    public Patient bookedBy;
    public Queue<Patient> waitlist;

    public Slot(Date start, Date end, Doctor doctor) {
        this.start = start;
        this.end = end;
        this.doctor = doctor;
        this.bookedBy = null;
        this.waitlist = null;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "start=" + start +
                ", end=" + end +
                ", doctor=" + doctor +
                ", bookedBy=" + bookedBy +
                '}';
    }

    @Override
    public int compareTo(Slot o) {
        if (this.start.before(o.start)) return -1;
        return 1;
    }

    public void addPatientToWaitlist(Patient p) {
        if (this.waitlist == null) this.waitlist = new ArrayDeque();
        this.waitlist.add(p);
    }
}

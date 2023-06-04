package com.example.clinic;
import java.sql.Timestamp;

public abstract class Ticket {
    private Patient patient;
    private Doctor doctor;
    private Timestamp timestamp;
    private long ID;
    private long fee;
    private long randomID;
    public long getID() {
        return ID;
    }
    public void setID(long iD) {
        ID = iD;
    }

    public Ticket(Patient patient, Doctor doctor, Timestamp timestamp,long fee) {
        this.patient = patient;
        this.doctor = doctor;
        this.timestamp = timestamp;
        this.fee = fee;
    }
    public Ticket(long ID,long randomID,Patient patient, Doctor doctor, Timestamp timestamp,long fee) {
        this.patient = patient;
        this.doctor = doctor;
        this.timestamp = timestamp;
        this.ID=ID;
    }
    public Patient getPatient() {
        return patient;
    }
    public Doctor getDoctor() {
        return doctor;
    }
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public long getFee() {
        return fee;
    }

    public long getRandomID() {
        return randomID;
    }

    public void setFee(long fee) {
        this.fee = fee;
    }

    public void setRandomID(long privateID) {
        this.randomID = privateID;
    }
}

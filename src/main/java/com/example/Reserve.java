package com.example;

import java.sql.Timestamp;
public class Reserve extends Ticket {
    private String placeholder;
    private static long IDCreator=1;
    public Reserve(Patient patient, Doctor doctor, Timestamp timestamp, String placeholder) {
        super(patient, doctor, timestamp,100);
        this.placeholder = placeholder;
        this.setID(IDCreator);
        IDCreator++;
    }
}

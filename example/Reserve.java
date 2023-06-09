package com.example;

import java.sql.Timestamp;
import java.util.Random;
public class Reserve extends Ticket {
    private String placeholder;
    private static long IDCreator=1;
    public Reserve(Patient patient, Doctor doctor, Timestamp timestamp) {
        super(patient, doctor, timestamp,100);
        this.setID(IDCreator);
        IDCreator++;
    }
    public Reserve(long ID, long randomID, Patient patient, Doctor doctor, Timestamp timestamp) {
        super(ID, randomID, patient, doctor, timestamp, 100);
    }
    public void randomGenerator(Hospital hospital) {
        boolean checker = true;
        while(checker){
            this.setRandomID(new Random().nextLong());
            checker = false;
            for(Reserve i: hospital.getReserves()){
                if(i==this){
                    continue;
                }
                if(i.getRandomID()==this.getRandomID()|| this.getRandomID()<0){
                    checker = true;
                    break;
                }
            }
        }
    }
}

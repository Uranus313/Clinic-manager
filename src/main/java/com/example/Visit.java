package com.example;
import java.sql.Timestamp;
import java.util.Random;

public class Visit extends Ticket {
    private String drugPrescription;
    private String orders;
    private String diagnose;
    private static long IDCreator=1;
    public Visit(Patient patient, Doctor doctor, Timestamp timestamp, String drugPrescription, String orders,long fee,String diagnose) {
        super(patient, doctor, timestamp , fee);
        this.drugPrescription = drugPrescription;
        this.orders = orders;
        this.diagnose = diagnose;
        this.setID(IDCreator);
        IDCreator++;
    }

    public Visit(long ID, Patient patient, Doctor doctor, Timestamp timestamp, String drugPrescription, String orders,long fee,String diagnose) {
        super(ID, patient, doctor, timestamp, fee);
        this.drugPrescription = drugPrescription;
        this.orders=orders;
        this.diagnose= diagnose;
    }

    public String getDrugPrescription() {
        return drugPrescription;
    }
    public String getOrders() {
        return orders;
    }
    public void randomGenerator(Hospital hospital) {
        boolean checker = true;
        while(checker){
            this.setRandomID(new Random().nextLong());
            checker = false;
            for(Visit i: hospital.getVisits()){
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

    public String getDiagnose() {
        return diagnose;
    }
}

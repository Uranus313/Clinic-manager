package com.example;
import java.sql.PreparedStatement;
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

    public Visit(long ID,long randomID, Patient patient, Doctor doctor, Timestamp timestamp, String drugPrescription, String orders,long fee,String diagnose) {
        super(ID,randomID, patient, doctor, timestamp, fee);
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

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    @Override
    public Doctor getDoctor() {
        return super.getDoctor();
    }

    @Override
    public long getFee() {
        return super.getFee();
    }

    @Override
    public long getID() {
        return super.getID();
    }

    @Override
    public long getRandomID() {
        return super.getRandomID();
    }

    @Override
    public Patient getPatient() {
        return super.getPatient();
    }

    @Override
    public Timestamp getTimestamp() {
        return super.getTimestamp();
    }

    public static long getIDCreator() {
        return IDCreator;
    }

    @Override
    public void setFee(long fee) {
        super.setFee(fee);
    }

    @Override
    public void setID(long iD) {
        super.setID(iD);
    }

    @Override
    public void setRandomID(long privateID) {
        super.setRandomID(privateID);
    }

    public void setDrugPrescription(String drugPrescription) {
        this.drugPrescription = drugPrescription;
    }

    public static void setIDCreator(long IDCreator) {
        Visit.IDCreator = IDCreator;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }
}

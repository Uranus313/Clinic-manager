package com.example.clinic;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Random;

import static com.example.clinic.DataCenter.connection;

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

    public static void saveVisit(Hospital hospital,Visit visit){

        try{
            PreparedStatement ps = connection.prepareStatement("insert into doctor (ID, randomID, patientID, doctorID, timestamp, drugprescription,orders,fee,diagnose"+
                      "values (?,?,?,?,?,?,?,?,?,?,?,?)");

            ps.setLong(1, visit.getID());
            ps.setLong(2, visit.getRandomID());
            ps.setLong(3, visit.getPatient().getID());
            ps.setLong(4, visit.getDoctor().getID());
            ps.setTimestamp(5, visit.getTimestamp());
            ps.setString(6, visit.getDrugPrescription());
            ps.setString(7, visit.getOrders());
            ps.setLong(8, visit.getFee());
            ps.setString(9, visit.getDiagnose());
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
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

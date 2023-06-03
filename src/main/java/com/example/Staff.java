package com.example;
import java.util.Random;

import javafx.stage.Stage;

public class Staff extends ClinicMember {
    String placeholder;
    private static long IDCreator=1;
    public Staff(String firstName, String lastName, String address, String birthDate, long personalID, long salary,
            boolean nightShift, boolean organce, String password) {
        super(firstName, lastName, address, birthDate, personalID, salary, nightShift, organce, password);
        this.setID(IDCreator);
        IDCreator++;
    }

    public Staff(long ID, long randomID, String firstName, String lastName, String address, String birthDate, long personalID, long salary, boolean nightShift, boolean organce, String password) {
        super(ID, randomID, firstName, lastName, address, birthDate, personalID, salary, nightShift, organce, password);
    }

    public void randomGenerator(Hospital hospital) {
        boolean checker = true;
       while(checker){
        this.setRandomID(new Random().nextLong());
        checker = false;
        for(Staff i: hospital.getStaff()){
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
    public static long getIDCreator() {
        return IDCreator;
    }
    public static void setIDCreator(long iDCreator) {
        IDCreator = iDCreator;
    }
    @Override
    public void menu(Hospital hospital, Stage window) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'menu'");
    }

    @Override
    public void changeInfo(Hospital hospital, Stage window) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'changeInfo'");
    }        
}

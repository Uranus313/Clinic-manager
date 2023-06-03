package com.example;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;



import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Patient extends Person {
    private static long IDCreator=1;
    public Patient(String firstName, String lastName, String address, String birthDate, long personalID,String password) {
        super(firstName, lastName, address, birthDate, personalID, password);
        this.setID(IDCreator);
        IDCreator++;
    }

    public Patient(long id,long randomid,String firstName, String lastName, String address, String birthDate, long personalID,String password) {
        super(id,randomid,firstName, lastName, address, birthDate, personalID, password);
    }
    public ArrayList<Reserve> getReserves(Hospital hospital) {
        ArrayList<Reserve> reserves= new ArrayList<>();
        for(Reserve i : hospital.getReserves()){
            if(i.getPatient()==this){
                reserves.add(i);
            }
        }
        return reserves;
    }
    public ArrayList<Visit> getVisits(Hospital hospital) {
        ArrayList<Visit> visits= new ArrayList<>();
        for(Visit i : hospital.getVisits()){
            if(i.getPatient()==this){
                visits.add(i);
            }
        }
        return visits;
    }
    public void randomGenerator(Hospital hospital) {
        boolean checker = true;
       while(checker){
        this.setRandomID(new Random().nextLong());
        checker = false;
        for(Patient i: hospital.getPatients()){
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

    public static void signUp(Hospital hospital){
        String firstName;
        String lastName;
        String address;
        String birthDate;
        long personalID=0;
        String password;
        Scanner longReader = new Scanner(System.in);
        Scanner stringReader = new Scanner(System.in);
        System.out.println("pls enter your first name");
        firstName = stringReader.nextLine();
        System.out.println("pls enter your last name");
        lastName = stringReader.nextLine();
        System.out.println("pls enter your address");
        address = stringReader.nextLine();
        System.out.println("pls enter your birthDate");
        birthDate = stringReader.nextLine();
        Boolean checker = true;
        while(checker){
            checker = false;
            System.out.println("pls enter your personal ID number");
            personalID = longReader.nextLong();
            for(Patient i: hospital.getPatients()){
                if(i.getRandomID()==personalID || personalID<0){
                    System.out.println("this personal ID is already in the system");
                    checker = true;
                    break;
                }
            }
        }
        System.out.println("pls enter your password");
        password = stringReader.nextLine();
        Patient patient = new Patient(firstName, lastName, address, birthDate, personalID, password);
        patient.randomGenerator(hospital);
        hospital.getPatients().add(patient);
        DataCenter.savePatient(hospital, patient);
        stringReader.close();
        longReader.close();
    }
    public void reserve(){
    }
    public Timestamp checkReserve(int year,int month,int day,long doctorID,Hospital hospital){
        try {
            ArrayList<Reserve> reserves=hospital.searchDoctor(doctorID).getReserves(hospital);
            Timestamp lastReserve = Timestamp.valueOf(year+"-"+month+"-"+day+"0:0:0.0");
            for(Reserve reserve : reserves){
                if(reserve.getTimestamp().getDay()== day-1 && reserve.getTimestamp().getMonth()== month-1 && reserve.getTimestamp().getYear()== year-1) {
                    if(reserve.getTimestamp().compareTo(lastReserve)==1){
                        lastReserve = reserve.getTimestamp();
                    }
                }
            }
            if(lastReserve.getHours()==14){
                return null;
            }
            if(lastReserve.getHours()==0){
                return Timestamp.valueOf(year+"-"+month+"-"+day+"8:0:0.0");
            }
            if(lastReserve.getMinutes()==45){
                lastReserve.setHours(lastReserve.getHours()+1);
                lastReserve.setMinutes(0);
            }else{
                lastReserve.setMinutes(lastReserve.getMinutes()+15);
            }
            return lastReserve;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    public void changeInfo(Hospital hospital, Stage window){
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10)); 
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setAlignment(Pos.CENTER);
        Label welcomeMsg= new Label("what do you want to change?");
        Label firstNameLabel = new Label("first name : ");
        Label lastNameLabel = new Label("last name : ");
        Label personalIDLabel = new Label("personal ID : ");
        Label birthDateLabel = new Label("birth date : ");
        Label addressLabel = new Label("address : ");
        Label password = new Label("password : ");
        TextField firstNameField = new TextField();
        firstNameField.setPromptText(this.getFirstName());
        TextField lastNameField = new TextField();
        lastNameField.setPromptText(this.getLastName());
        TextField personalIDField = new TextField();
        personalIDField.setPromptText(this.getPersonalID()+ "");
        TextField birthDateField = new TextField();
        birthDateField.setPromptText(this.getBirthDate());
        TextField addressField = new TextField();
        TextField oldPasswordField = new TextField();
        oldPasswordField.setPromptText("old password");
        TextField newPasswordField = new TextField();
        newPasswordField.setPromptText("new password");
        firstNameField.setOnAction(e ->{
            if(firstNameField.getText()!= this.getFirstName()){
                if(!checker(firstNameField.getText(), 100, 1, true)){
                    AlertBox.display("error", "entry should be between 1 and 100 letters ");
                }else{
                    this.setFirstName(firstNameField.getText());
                    DataCenter.updateInfo("patient", "firstName", this.getID(), firstNameField.getText());
                    AlertBox.display("change", "your name has successsfully changed");
                }
            }
        });
        lastNameField.setOnAction(e ->{
            if(lastNameField.getText()!= this.getLastName()){
                if(!checker(lastNameField.getText(), 100, 1, true)){
                    AlertBox.display("error", "entry should be between 1 and 100 letters ");
                }else{
                    this.setFirstName(lastNameField.getText());
                    DataCenter.updateInfo("patient", "lastName", this.getID(), lastNameField.getText());
                    AlertBox.display("change", "your name has successsfully changed");
                }
            }
        });
        personalIDField.setOnAction(e ->{
            try{
            if(Long.parseLong(personalIDField.getText()) != this.getPersonalID()){
                if(checker(personalIDField.getText(), 10, 9, false)||Long.parseLong(personalIDField.getText())<0 ){
                    throw new Exception();
                }else{
                     for(Patient i: hospital.getPatients()){
                         if(i==this){
                             continue;
                         }
                         if(i.getPersonalID()== Long.parseLong(personalIDField.getText())){
                            throw new IndexOutOfBoundsException();
                         }
                        }
                 }
                 this.setPersonalID(Long.parseLong(personalIDField.getText()));
                    DataCenter.updateInfo("patient", "personalID", this.getID(), Long.parseLong(personalIDField.getText()));
                    AlertBox.display("change", "your name has successsfully changed");
            }
        }catch(IndexOutOfBoundsException c){
            AlertBox.display("error", "thers's already a person with this ID in our system ");
        }catch(Exception c){
            AlertBox.display("error", "entry should be a positive number either 9 or 10 charachters long  ");
        }
        });
        birthDateField.setOnAction(e ->{
            if(birthDateField.getText()!= this.getBirthDate()){
                if(!checker(birthDateField.getText(), 100, 1, false)){
                    AlertBox.display("error", "entry should be between 1 and 45 letters ");
                }else{
                    this.setBirthDate(birthDateField.getText());
                    DataCenter.updateInfo("patient", "birthDate", this.getID(), birthDateField.getText());
                    AlertBox.display("change", "your birthDate has successsfully changed");
                }
            }
        });
        addressField.setOnAction(e ->{
            if(addressField.getText()!= this.getAddress()){
                if(!checker(addressField.getText(), 200, 1, false)){
                    AlertBox.display("error", "entry should be between 1 and 200 letters ");
                }else{
                    this.setAddress(addressField.getText());
                    DataCenter.updateInfo("patient", "address", this.getID(), addressField.getText());
                    AlertBox.display("change", "your address has successsfully changed");
                }
            }
        });
        newPasswordField.setOnAction(e ->{
            try{
            if(oldPasswordField.getText() != this.getPassword()){
                if(checker(personalIDField.getText(), 40, 8, false)){
                    throw new Exception();
                }else{
                    if(!oldPasswordField.getText().equals(this.getPassword())){
                        throw new IndexOutOfBoundsException();
                    }
                 }
                 this.setPassword(newPasswordField.getText());;
                    DataCenter.updateInfo("patient", "password", this.getID(), newPasswordField.getText());
                    AlertBox.display("change", "your password has successsfully changed");
            }
        }catch(IndexOutOfBoundsException c){
            AlertBox.display("error", "old password is wrong");
        }catch(Exception c){
            AlertBox.display("error", "entry should be between 8 to 40 charachters long  ");
        }
        });
        layout.add(welcomeMsg, 0, 0);
        layout.add(firstNameLabel, 0, 1);
        layout.add(lastNameLabel, 0, 2);
        layout.add(personalIDLabel, 0, 3);
        layout.add(birthDateLabel, 0, 4);
        layout.add(addressLabel, 0, 5);
        layout.add(password, 0, 6);
        layout.add(firstNameField, 1, 1);
        layout.add(lastNameField, 1, 2);
        layout.add(personalIDField, 1, 3);
        layout.add(birthDateField, 1, 4);
        layout.add(addressField, 1, 5);
        layout.add(oldPasswordField, 1, 6);
        layout.add(newPasswordField, 1, 7);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        

    }

    @Override
    public void menu(Hospital hospital, Stage window) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'menu'");
    }
}

package com.example;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Scanner;

public class Hospital {
    private String name;
    private long phoneNumber;
    private ArrayList<Patient> patients;
    private ArrayList<Doctor> doctors;
    private ArrayList<Reserve> reserves;
    private ArrayList<Visit>visits;
    private ArrayList<Nurse>nurses;
    private ArrayList<Staff>staff;
    private ArrayList<Admin>admins;


    public Hospital(String name, long phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        patients= new ArrayList<>();
        doctors= new ArrayList<>();
        reserves= new ArrayList<>();
        visits= new ArrayList<>();
        nurses=new ArrayList<>();
        staff=new ArrayList<>();
        admins=new ArrayList<>();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public ArrayList<Patient> getPatients() {
        return patients;
    }
    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }
    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }
    public void setDoctors(ArrayList<Doctor> doctors) {
        this.doctors = doctors;
    }
    public ArrayList<Reserve> getReserves() {
        return reserves;
    }
    public void setReserves(ArrayList<Reserve> reserves) {
        this.reserves = reserves;
    }
    public ArrayList<Visit> getVisits() {
        return visits;
    }
    public void setVisits(ArrayList<Visit> visits) {
        this.visits = visits;
    }
    public ArrayList<Nurse> getNurses() {
        return nurses;
    }
    public void setNurses(ArrayList<Nurse> nurses) {
        this.nurses = nurses;
    }
    public ArrayList<Staff> getStaff() {
        return staff;
    }
    public void setStaff(ArrayList<Staff> staff) {
        this.staff = staff;
    }

    public ArrayList<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(ArrayList<Admin> admins) {
        this.admins = admins;
    }
    public void  signUpmenu(Stage window){
        GridPane layout2 = new GridPane();
        Label welcomeMsg= new Label("who do you want to sign up as?");
        Button patientSignUpButton = new Button("sign up as patient");
        patientSignUpButton.setOnAction(e -> Patient.signUp(this, window));
        Button adminSignUpButton = new Button("sign up as admin");
        adminSignUpButton.setOnAction(e-> Admin.signUp(this,window));
        layout2.setPadding(new Insets(10, 10, 10, 10)); 
        layout2.setVgap(5);
        layout2.setHgap(5);
        layout2.setAlignment(Pos.CENTER);
        layout2.add(welcomeMsg, 0,0);
        layout2.add(patientSignUpButton, 1,1);
        layout2.add(adminSignUpButton, 1,2);
        Scene scene = new Scene(layout2);
        window.setScene(scene);
        window.show();
    }
    public void  menu(Stage window){
        GridPane layout2 = new GridPane();
        Label welcomeMsg= new Label("welcome to our clinic");
        Button signUpButton = new Button("sign up as patient");
        signUpButton.setOnAction(e -> Patient.signUp(this, window));
        Button signInButton = new Button("sign in");
        signInButton.setOnAction(e-> signInMenu(this,window));
        Button searchDoctorButton = new Button("search for doctor");
        searchDoctorButton.setOnAction(e -> Patient.searchDoctor(this, window));
        layout2.setPadding(new Insets(10, 10, 10, 10)); 
        layout2.setVgap(5);
        layout2.setHgap(5);
        layout2.setAlignment(Pos.CENTER);
        layout2.add(welcomeMsg, 0,0);
        layout2.add(signInButton, 1,1);
        layout2.add(signUpButton, 1,2);
        layout2.add(searchDoctorButton, 1,3);
        Scene scene = new Scene(layout2);
        window.setScene(scene);
        window.show();
    }
    public void signInMenu(Hospital hospital, Stage window){
        GridPane layout2 = new GridPane();
        layout2.setAlignment(Pos.CENTER);
        Label welcomeLabel = new Label("sign IN");
        Label nameIDLabel = new Label("name/random ID : ");
        Label passLabel = new Label("password : ");
        Label choiceLabel = new Label("what do you want to sign in with? ");
        TextField nameIDField = new TextField();
        TextField passField = new TextField();
        Button adminSignIn = new Button("admin sign in");
        Button patientSignIn = new Button("patient sign in");
        Button doctorSignIn = new Button("doctor sign in");
        Button nurseSignIn = new Button("nurse sign in");
        Button staffSignIn = new Button("staff sign in");
        ToggleGroup tg = new ToggleGroup();
        RadioButton IDSignIn = new RadioButton("random ID");
        IDSignIn.setSelected(true);
        RadioButton nameSignIn = new RadioButton("Last name"); 
        adminSignIn.setOnAction(e ->{
            try{
            if(nameSignIn.isSelected()){
                for(Admin admin : this.getAdmins()){
                    if(admin.checkSignIn(nameIDField.getText(), passField.getText())){
                        admin.menu(hospital, window);
                        break;
                    }
                }
            }else{
                for(Admin admin : this.getAdmins()){
                    if(admin.checkSignIn(Long.parseLong(nameIDField.getText()), passField.getText())){
                        admin.menu(hospital, window);
                        break;
                    }
                }
            }
        }catch(Exception c){
            System.out.println(c);
        }
        });
        patientSignIn.setOnAction(e ->{
            try{
            if(nameSignIn.isSelected()){
                for(Patient patient : this.getPatients()){
                    if(patient.checkSignIn(nameIDField.getText(), passField.getText())){
                        patient.menu(hospital, window);
                        break;
                    }
                }
            }else{
                for(Patient patient : this.getPatients()){
                    if(patient.checkSignIn(Long.parseLong(nameIDField.getText()), passField.getText())){
                        patient.menu(hospital, window);
                        break;
                    }
                }
            }
        }catch(Exception c){
            System.out.println(c);
        }
        });
        doctorSignIn.setOnAction(e ->{
            try{
            if(nameSignIn.isSelected()){
                for(Doctor doctor : this.getDoctors()){
                    if(doctor.checkSignIn(nameIDField.getText(), passField.getText())){
                        doctor.menu(hospital, window);
                        break;
                    }
                }
            }else{
                for(Doctor doctor : this.getDoctors()){
                    if(doctor.checkSignIn(Long.parseLong(nameIDField.getText()), passField.getText())){
                        doctor.menu(hospital, window);
                        break;
                    }
                }
            }
        }catch(Exception c){
            System.out.println(c);
        }
        });
        nurseSignIn.setOnAction(e ->{
            try{
            if(nameSignIn.isSelected()){
                for(Nurse nurse : this.getNurses()){
                    if(nurse.checkSignIn(nameIDField.getText(), passField.getText())){
                        nurse.menu(hospital, window);
                        break;
                    }
                }
            }else{
                for(Nurse nurse : this.getNurses()){
                    if(nurse.checkSignIn(Long.parseLong(nameIDField.getText()), passField.getText())){
                        nurse.menu(hospital, window);
                        break;
                    }
                }
            }
        }catch(Exception c){
            System.out.println(c);
        }
        });
        staffSignIn.setOnAction(e ->{
            try{
            if(nameSignIn.isSelected()){
                for(Staff staff : this.getStaff()){
                    if(staff.checkSignIn(nameIDField.getText(), passField.getText())){
                        staff.menu(hospital, window);
                        break;
                    }
                }
            }else{
                for(Staff staff : this.getStaff()){
                    if(staff.checkSignIn(Long.parseLong(nameIDField.getText()), passField.getText())){
                        staff.menu(hospital, window);
                        break;
                    }
                }
            }
        }catch(Exception c){
            System.out.println(c);
        }
        });
        IDSignIn.setToggleGroup(tg);
        nameSignIn.setToggleGroup(tg);
        layout2.add(welcomeLabel, 0, 0);
        layout2.add(nameIDLabel, 0, 1);
        layout2.add(nameIDField, 1, 1);
        layout2.add(passLabel, 0, 2);
        layout2.add(passField, 1, 2);
        layout2.add(choiceLabel, 0, 3);
        layout2.add(IDSignIn, 1, 3);
        layout2.add(nameSignIn, 2, 3);
        layout2.add(patientSignIn,0,4);
        layout2.add(doctorSignIn,1,4);
        layout2.add(nurseSignIn,2,4);
        layout2.add(staffSignIn,0,5);
        layout2.add(adminSignIn,1,5);
        window.setScene(new Scene(layout2));
    }
    public Doctor searchDoctor(long search){
        for(Doctor doctor: this.doctors){
            if (doctor.getID()== search){
                System.out.println(doctor.getFirstName()+doctor.getLastName()+" : "+doctor.getProfession());
                return doctor;
            }
        }
        return null;
    }
    public ObservableList<Doctor> searchDoctorBYname(String search){
        ObservableList<Doctor> doctors= FXCollections.observableArrayList();
        for(Doctor doctor: this.doctors){
            if ((doctor.getFirstName()+ " " + doctor.getLastName()).contains(search)){
               doctors.add(doctor);
            }
        }
        return doctors;
    }

    public ObservableList<Doctor> searchDoctorByProfession(String search){
        ObservableList<Doctor> doctors= FXCollections.observableArrayList();
        for(Doctor doctor: this.doctors){
            if (doctor.getProfession().contains( search)){
                doctors.add(doctor);

            }
        }
        return doctors;
    }
    public Patient searchPatient(long search){
        for(Patient patient: this.patients){
            if (patient.getID()== search){
                System.out.println(patient.getFirstName()+patient.getLastName());
                return patient;
            }
        }
        return null;
    }
    
    
}

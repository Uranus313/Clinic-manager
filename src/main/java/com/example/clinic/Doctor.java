package com.example.clinic;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;


public class Doctor extends ClinicMember {
    private String profession;
    public String getProfession() {
        return profession;
    }
    public void setProfession(String profession) {
        this.profession = profession;
    }
    private static long IDCreator=1;
    public Doctor(String firstName, String lastName, String address, String birthDate, long personalID, long salary,
            boolean nightShift, boolean organce, String profession,String password) {
        super(firstName, lastName, address, birthDate, personalID, salary, nightShift, organce, password);
        this.profession = profession;
        this.setID(IDCreator);
        IDCreator++;
    }
    

    public Doctor(long ID, long randomID, String firstName, String lastName, String address, String birthDate,
            long personalID, long salary, boolean nightShift, boolean organce, String password, String profession) {
        super(ID, randomID, firstName, lastName, address, birthDate, personalID, salary, nightShift, organce, password);
        this.profession = profession;
    }
    
    public ObservableList<Reserve> getReserves(Hospital hospital) {
        ObservableList<Reserve> reserves= FXCollections.observableArrayList();
        for(Reserve i : hospital.getReserves()){
            if(i.getDoctor()==this){
                reserves.add(i);
            }
        }
        return reserves;
    }
    public ArrayList<Reserve> getReservesArray(Hospital hospital) {
        ArrayList<Reserve> reserves= new ArrayList<>();
        for(Reserve i : hospital.getReserves()){
            if(i.getDoctor()==this){
                reserves.add(i);
            }
        }
        return reserves;
    }
    public ObservableList<Visit> getVisits(Hospital hospital) {
        ObservableList<Visit> visits= FXCollections.observableArrayList();
        for(Visit i : hospital.getVisits()){
            if(i.getDoctor()==this){
                visits.add(i);
            }
        }
        return visits;
    }      
    public ObservableList<Patient> getPatients(Hospital hospital) {
        ObservableList<Patient> patients= FXCollections.observableArrayList();
        for(Visit i : hospital.getVisits()){
            boolean checker = false;
            if(i.getDoctor()==this){
                for(Patient j : patients){
                    if(i.getPatient()==j){
                        checker = true;
                        break;
                    }
                    if(checker){
                        continue;
                    }
                    patients.add(i.getPatient());
                }
            }
        }
        return patients;
    }
    public void randomGenerator(Hospital hospital) {
        boolean checker = true;
       while(checker){
        this.setRandomID(new Random().nextLong());
        checker = false;
        for(Doctor i: hospital.getDoctors()){
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
    @Override
    public void menu(Hospital hospital, Stage window) {
        GridPane layout = new GridPane();
        Label welcomeMsg= new Label("welcome"+ this.getFirstName());
        Button getInfo = new Button("get your info");
        getInfo.setOnAction(e -> {
            System.out.println("check");
            this.getInfo(hospital,window);});
        Button changeInfo = new Button("change your info");
        changeInfo.setOnAction(e -> {
            System.out.println("check2");
            this.changeInfo(hospital,window);});
        Button getPatientList = new Button("get your patient list");
        // getInfo.setOnAction(e -> this.getPatientList());
        Button getVisitList = new Button("get your visit list");
        // getInfo.setOnAction(e -> this.getVisitList());
        Button getReserveList = new Button("get your reserve list");
        // getInfo.setOnAction(e -> this.getReserveList());
        Button visit = new Button("make a visit");
        visit.setOnAction(e -> {
            System.out.println("check4");
            this.visit(hospital,window);});
        layout.setPadding(new Insets(10, 10, 10, 10)); 
        layout.setVgap(5);
        layout.setHgap(5);
        layout.setAlignment(Pos.CENTER);
        layout.add(welcomeMsg, 0,0);
        layout.add(getInfo, 1,1);
        layout.add(changeInfo, 1,2);
        layout.add(getPatientList, 1,3);
        layout.add(getVisitList, 1,4);
        layout.add(getReserveList, 1,5);
        layout.add(visit, 1,6);
        Scene scene = new Scene(layout);
        window.setScene(scene);
    }
    public void visit(Hospital hospital, Stage window){
        GridPane layout = new GridPane();
        Patient  patient;
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setPadding(new Insets(10));

        Label welcomeLabel= new Label("make a visit");
        Label patientLabel = new Label("patient ID :");
        GridPane.setConstraints(welcomeLabel,0,0);
        TextField patientField = new TextField();
        GridPane.setConstraints(patientLabel,0,1);
        patientField.setPromptText("patient ID");
        GridPane.setConstraints(patientField,1,1);
        Button search = new Button("check the ID");
        GridPane.setConstraints(search,0,2);
        search.setOnAction(e -> {
            try {
                 if(hospital.searchPatient(Long.parseLong(patientField.getText()))== null){
                    throw new Exception();
                 }else{
                   AlertBox.display("found", "patient "+hospital.searchPatient(Long.parseLong(patientField.getText())).getFirstName()+hospital.searchPatient(Long.parseLong(patientField.getText())).getLastName()+" has been found" );
                 }
            }catch(Exception c){
                AlertBox.display("404", "no patients with this ID has been found");
                patientField.clear();
            }
        });
        Label diagnoseLabel = new Label("diagnose :");
        GridPane.setConstraints(diagnoseLabel,0,3);
        TextField diagnoseField = new TextField();
        GridPane.setConstraints(diagnoseField,1,3);
        diagnoseField.setPromptText("diagnose");
        Label prescribeLabel = new Label("prescribe :");
        GridPane.setConstraints(prescribeLabel,0,4);
        TextField prescribeField = new TextField();
        GridPane.setConstraints(prescribeField,1,4);
        prescribeField.setPromptText("prescribe");
        Label orderLabel = new Label("orders :");
        GridPane.setConstraints(orderLabel,0,5);
        TextField orderField = new TextField();
        GridPane.setConstraints(orderField,1,5);
        orderField.setPromptText("orders");
        Label feeLabel = new Label("fee :");
        GridPane.setConstraints(feeLabel,0,6);
        TextField feeField = new TextField();
        GridPane.setConstraints(feeField,1,6);
        feeField.setPromptText("fee");
        Button submitButton = new Button("submit");
        GridPane.setConstraints(submitButton,0,7);
        submitButton.setOnAction(e ->{
            try{
                if(hospital.searchPatient(Long.parseLong(patientField.getText()))== null || diagnoseField.getText()== null ||
                prescribeField.getText()==null || orderField.getText()==null || feeField.getText()== null ){
                    throw new IndexOutOfBoundsException();
                }
                Visit visit = new Visit(hospital.searchPatient(Long.parseLong(patientField.getText())), this, new Timestamp(System.currentTimeMillis()), prescribeField.getText(), orderField.getText(),Long.parseLong(feeField.getText()), diagnoseField.getText());
//                DataCenter.saveVisit(visit);
            }catch(IndexOutOfBoundsException c){
                AlertBox.display("error", "you should'nt leave any of the fields empty");
            }catch(Exception c){
                AlertBox.display("error", "enter a valid patient ID");
            }
        });
        layout.getChildren().addAll(welcomeLabel,patientLabel,patientField,search,diagnoseLabel,diagnoseField,orderLabel,orderField,prescribeField,prescribeLabel,feeField,feeLabel,submitButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);

    }
    public static long getIDCreator() {
        return IDCreator;
    }
    public static void setIDCreator(long iDCreator) {
        IDCreator = iDCreator;
    }
    public void getPatientList(Hospital hospital){
        Stage window = new Stage();
        TableView patientTable = new TableView<>();
        window.setTitle("Patient List");
        TableColumn<Patient,Long > IDColumn = new TableColumn<>("ID");
        IDColumn.setMinWidth(100);
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID") );
        TableColumn<Patient,String > firstNameColumn = new TableColumn<>("first name");
        firstNameColumn.setMinWidth(100);
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName") );
        TableColumn<Patient,String > lastNameColumn = new TableColumn<>("last name");
        lastNameColumn.setMinWidth(100);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName") );
        TableColumn<Patient,Long > personalIDColumn = new TableColumn<>("personal ID");
        personalIDColumn.setMinWidth(100);
        personalIDColumn.setCellValueFactory(new PropertyValueFactory<>("personalID") );
        TableColumn<Patient,String > addressColumn = new TableColumn<>("address");
        addressColumn.setMinWidth(100);
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address") );
        TableColumn<Patient,String > birthDateColumn = new TableColumn<>("birth date");
        birthDateColumn.setMinWidth(100);
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate") );
        patientTable.setItems(this.getPatients(hospital));
        patientTable.getColumns().addAll(IDColumn,firstNameColumn,lastNameColumn,personalIDColumn,addressColumn,birthDateColumn);
        VBox layout = new VBox(patientTable);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }
	@Override

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
        Label professionLabel = new Label("profession : ");
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
        TextField professionField = new TextField();
        professionField.setPromptText(this.getProfession());
        oldPasswordField.setPromptText("old password");
        TextField newPasswordField = new TextField();
        newPasswordField.setPromptText("new password");
        firstNameField.setOnAction(e ->{
            if(firstNameField.getText()!= this.getFirstName()){
                if(!checker(firstNameField.getText(), 100, 1, true)){
                    AlertBox.display("error", "entry should be between 1 and 100 letters ");
                }else{
                    this.setFirstName(firstNameField.getText());
                    DataCenter.updateInfo("doctor", "firstName", this.getID(), firstNameField.getText());
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
                    DataCenter.updateInfo("doctor", "lastName", this.getID(), lastNameField.getText());
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
                        for(Doctor i: hospital.getDoctors()){
                            if(i==this){
                                continue;
                            }
                            if(i.getPersonalID()== Long.parseLong(personalIDField.getText())){
                                throw new IndexOutOfBoundsException();
                            }
                        }
                    }
                    this.setPersonalID(Long.parseLong(personalIDField.getText()));
                    DataCenter.updateInfo("doctor", "personalID", this.getID(), Long.parseLong(personalIDField.getText()));
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
                    DataCenter.updateInfo("doctor", "birthDate", this.getID(), birthDateField.getText());
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
                    DataCenter.updateInfo("doctor", "address", this.getID(), addressField.getText());
                    AlertBox.display("change", "your address has successsfully changed");
                }
            }
        });
        professionField.setOnAction(e ->{
            if(professionField.getText()!= this.getAddress()){
                if(!checker(professionField.getText(), 500, 1, false)){
                    AlertBox.display("error", "entry should be between 1 and 500 letters ");
                }else{
                    this.setAddress(professionField.getText());
                    DataCenter.updateInfo("doctor", "profession", this.getID(), professionField.getText());
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
                    DataCenter.updateInfo("doctor", "password", this.getID(), newPasswordField.getText());
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
}

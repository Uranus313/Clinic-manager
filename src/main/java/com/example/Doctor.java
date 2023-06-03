package com.example;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;



import javafx.beans.Observable;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



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
        getInfo.setOnAction(e -> this.getInfo(hospital,window));
        Button changeInfo = new Button("change your info");
        // getInfo.setOnAction(e -> this.changeInfo());
        Button getPatientList = new Button("get your patient list");
        // getInfo.setOnAction(e -> this.getPatientList());
        Button getVisitList = new Button("get your visit list");
        // getInfo.setOnAction(e -> this.getVisitList());
        Button getReserveList = new Button("get your reserve list");
        // getInfo.setOnAction(e -> this.getReserveList());
        Button visit = new Button("make a visit");
        // visit.setOnAction(e -> this.makeVisit());
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
    public void visit(Hospital hospital){
        GridPane layout = new GridPane();
        Patient  patient;
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);
        Label patientLabel = new Label("patient ID :");
        TextField patientField = new TextField();
        patientField.setPromptText("patient ID");
        Button search = new Button("check the ID");
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
        TextField diagnoseField = new TextField();
        diagnoseField.setPromptText("diagnose");
        Label prescribeLabel = new Label("prescribe :");
        TextField prescribeField = new TextField();
        prescribeField.setPromptText("prescribe");
        Label orderLabel = new Label("orders :");
        TextField orderField = new TextField();
        orderField.setPromptText("orders");
        Label feeLabel = new Label("fee :");
        TextField feeField = new TextField();
        feeField.setPromptText("fee");
        Button submitButton = new Button("submit");
        submitButton.setOnAction(e ->{
            try{
                if(hospital.searchPatient(Long.parseLong(patientField.getText()))== null || diagnoseField.getText()== null ||
                prescribeField.getText()==null || orderField.getText()==null || feeField.getText()== null ){
                    throw new IndexOutOfBoundsException();
                }
                Visit visit = new Visit(hospital.searchPatient(Long.parseLong(patientField.getText())), this, new Timestamp(System.currentTimeMillis()), prescribeField.getText(), orderField.getText(),Long.parseLong(feeField.getText()), diagnoseField.getText());
                DataCenter.saveVisit(visit);
            }catch(IndexOutOfBoundsException c){
                AlertBox.display("error", "you should'nt leave any of the fields empty");
            }catch(Exception c){
                AlertBox.display("error", "fee should be number");
            }
        });
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
	public void changeInfo(Hospital hospital, Stage window) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'changeInfo'");
	}        
    
}

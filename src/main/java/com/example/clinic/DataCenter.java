package com.example.clinic;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataCenter {
    static Connection connection;
    static Statement statement;
    static ResultSet resultSet;
    DataCenter(){
        try{
        String url = "jdbc:mysql://localhost:3306/hospital";
        connection = DriverManager.getConnection(url, "root", "@Mm12345678");
        statement = connection.createStatement();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public static void loadAll(Hospital hospital){
        try {
            resultSet = statement.executeQuery("select * from doctor");
            System.out.println(resultSet);
            // Doctor(long ID, long randomID, String firstName, String lastName, String address, String birthDate,
            // long personalID, long salary, boolean nightShift, boolean organce, String password, String profession)
            while(resultSet.next()){
                System.out.println("check");
                hospital.getDoctors().add(new Doctor(resultSet.getLong(1),resultSet.getLong(2),resultSet.getString(3),
                resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getLong(7),
                resultSet.getLong(8),resultSet.getBoolean(9),resultSet.getBoolean(10),
                resultSet.getString(11),resultSet.getString(12)));
            }
            Doctor.setIDCreator(1+hospital.getDoctors().get(hospital.getDoctors().size()-1).getID());

        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            resultSet = statement.executeQuery("select * from patient");
            System.out.println(resultSet);
            // Doctor(long ID, long randomID, String firstName, String lastName, String address, String birthDate,
            //int id,int randomid,String firstName, String lastName, String address, String birthDate, long personalID,String password
            // long personalID, long salary, boolean nightShift, boolean organce, String password, String profession)
            while(resultSet.next()){
                System.out.println("check");
                hospital.getPatients().add(new Patient(resultSet.getLong(1),resultSet.getLong(2),resultSet.getString(3),
                resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getLong(7),
                resultSet.getString(8)));
            }
            Patient.setIDCreator(1+hospital.getPatients().get(hospital.getPatients().size()-1).getID());

        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            resultSet = statement.executeQuery("select * from Nurse");
            System.out.println(resultSet);
            // Doctor(long ID, long randomID, String firstName, String lastName, String address, String birthDate,
            //int id,int randomid,String firstName, String lastName, String address, String birthDate, long personalID,String password
            // long personalID, long salary, boolean nightShift, boolean organce, String password, String profession)
            while(resultSet.next()){
                System.out.println("check");
                hospital.getNurses().add(new Nurse(resultSet.getLong(1),resultSet.getLong(2),resultSet.getString(3),
                        resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getLong(7),
                        resultSet.getLong(8),resultSet.getBoolean(9),resultSet.getBoolean(10),
                        resultSet.getString(11)));
            }
            Nurse.setIDCreator(1+hospital.getPatients().get(hospital.getPatients().size()-1).getID());

        } catch (Exception e) {
            System.out.println(e);
        }


    try {
        resultSet = statement.executeQuery("select * from Nurse");
        System.out.println(resultSet);
        // Doctor(long ID, long randomID, String firstName, String lastName, String address, String birthDate,
        //int id,int randomid,String firstName, String lastName, String address, String birthDate, long personalID,String password
        // long personalID, long salary, boolean nightShift, boolean organce, String password, String profession)
        while(resultSet.next()){
            System.out.println("check");
            hospital.getStaff().add(new Staff(resultSet.getLong(1),resultSet.getLong(2),resultSet.getString(3),
                    resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getLong(7),
                    resultSet.getLong(8),resultSet.getBoolean(9),resultSet.getBoolean(10),
                    resultSet.getString(11)));
        }
        Staff.setIDCreator(1+hospital.getPatients().get(hospital.getPatients().size()-1).getID());

    } catch (Exception e) {
        System.out.println(e);
    }
}




    public static void saveDoctor(Hospital hospital){
        try{
        PreparedStatement ps = connection.prepareStatement("insert into doctor (ID, randomID, firstName, lastName, address, birthDate,"+
        "personalID, salary, nightShift, organce, password, profession)" + "values (?,?,?,?,?,?,?,?,?,?,?,?)");
        for(Doctor i : hospital.getDoctors()){
            ps.setLong(1, i.getID());
            ps.setLong(2, i.getRandomID());
            ps.setString(3, i.getFirstName());
            ps.setString(4, i.getLastName());
            ps.setString(5, i.getAddress());
            ps.setString(6, i.getBirthDate());
            ps.setLong(7, i.getPersonalID());
            ps.setLong(8, i.getSalary());
            ps.setBoolean(9, i.isNightShift());
            ps.setBoolean(10, i.isOrgance());
            ps.setString(11, i.getPassword());
            ps.setString(12, i.getProfession());
            ps.executeUpdate();
        }
    }catch(Exception e){
        System.out.println(e);
    }
    }
    public static void saveDoctor(Hospital hospital,Doctor doctor){
        try{
        PreparedStatement ps = connection.prepareStatement("insert into doctor (ID, randomID, firstName, lastName, address, birthDate,"+
        "personalID, salary, nightShift, organce, password, profession)" + "values (?,?,?,?,?,?,?,?,?,?,?,?)");
            hospital.getDoctors().add(doctor);
            doctor.randomGenerator(hospital);
            ps.setLong(1, doctor.getID());

            ps.setLong(2, doctor.getRandomID());
            ps.setString(3, doctor.getFirstName());
            ps.setString(4, doctor.getLastName());
            ps.setString(5, doctor.getAddress());
            ps.setString(6, doctor.getBirthDate());
            ps.setLong(7, doctor.getPersonalID());
            ps.setLong(8, doctor.getSalary());
            ps.setBoolean(9, doctor.isNightShift());
            ps.setBoolean(10, doctor.isOrgance());
            ps.setString(11, doctor.getPassword());
            ps.setString(12, doctor.getProfession());
            ps.executeUpdate();
    }catch(Exception e){
        System.out.println(e);
    }
    }
    public static void savePatient(Hospital hospital){
        try{
            PreparedStatement ps = connection.prepareStatement("insert into patient (ID, randomID, firstName, lastName, address, birthDate,"+
            " personalID, password)" + "values (?,?,?,?,?,?,?,?)");
            for(Patient i : hospital.getPatients()){
                ps.setLong(1, i.getID());
                ps.setLong(2, i.getRandomID());
                ps.setString(3, i.getFirstName());
                ps.setString(4, i.getLastName());
                ps.setString(5, i.getAddress());
                ps.setString(6, i.getBirthDate());
                ps.setLong(7, i.getPersonalID());
                ps.setString(8, i.getPassword());
                ps.executeUpdate();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public static void savePatient(Hospital hospital,Patient patient){
        try{
            PreparedStatement ps = connection.prepareStatement("insert into patient (ID, randomID, firstName, lastName, address, birthDate,"+
            " personalID, password)" + "values (?,?,?,?,?,?,?,?)");
                ps.setLong(1, patient.getID());
                ps.setLong(2, patient.getRandomID());
                ps.setString(3, patient.getFirstName());
                ps.setString(4, patient.getLastName());
                ps.setString(5, patient.getAddress());
                ps.setString(6, patient.getBirthDate());
                ps.setLong(7, patient.getPersonalID());
                ps.setString(8, patient.getPassword());
                ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public static void updateInfo(String changeClass, String element , long ID, String newValue){
        try {
            PreparedStatement ps = connection.prepareStatement("update "+changeClass+" set "+element+" = ? where ID = ?");
            ps.setString(1, newValue);
            ps.setLong(2, ID);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void updateInfo(String changeClass, String element , long ID, Boolean newValue){
        try {
            PreparedStatement ps = connection.prepareStatement("update "+changeClass+" set "+element+" = ? where ID = ?");
            ps.setBoolean(1, newValue);
            ps.setLong(2, ID);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void updateInfo(String changeClass, String element , long ID, long newValue){
        try {
            PreparedStatement ps = connection.prepareStatement("update "+changeClass+" set "+element+" = ? where ID = ?");
            ps.setLong(1, newValue);
            ps.setLong(2, ID);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void remove(String removeClass, long ID){
        try {
            PreparedStatement ps = connection.prepareStatement("delete from "+removeClass+" where ID = ?");  
            ps.setLong(1, ID);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

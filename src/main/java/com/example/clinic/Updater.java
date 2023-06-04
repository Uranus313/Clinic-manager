package com.example.clinic;
import java.sql.*;

public class Updater {
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    public Updater(){
        try{
            String url = "jdbc:mysql://localhost:3306/hospital";
            connection = DriverManager.getConnection(url, "root", "Mehrbod13577531");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from test");
        }catch(Exception e){
         
        }
    }
    

}

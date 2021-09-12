package com.oopii.wellness_centre4;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    /*
    * Get the connection from this class by Connection conn = DatabaseConnection.connectDB();
    *
    * Use the if statement below to test the connection It worked on my(Ryan Koech) side
    *
      if(DatabaseConnection.connectDB() == null){
        System.out.println("Unsuccessfull Connection");
      }else {
        System.out.println("Success Connecting");
      }
    * */
    static final String DB_URL = "jdbc:mysql://localhost:3306/wellness_centre";
    static final String USER = "root";
    static final String PASS = "";
    public static Connection connectDB(){
        Connection conn = null;
        try{

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            return conn;
        }catch(Exception ex){

            System.out.print("There was an error connecting to the database!");
            return null;
        }
    }
}

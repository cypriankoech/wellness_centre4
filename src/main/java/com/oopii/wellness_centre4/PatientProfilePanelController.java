package com.oopii.wellness_centre4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

public class PatientProfilePanelController {
    public static void printThis(String smt){
        System.out.println(smt);
    }

    //Fetches and returns data from the database
    public static Hashtable getProfileData(){
        Hashtable<String, String> profileData= new Hashtable<String, String>();
        Connection connection = DatabaseConnection.connectDB();
        if(connection != null){
            try{
                PreparedStatement st = (PreparedStatement)
                        connection.prepareStatement ("SELECT * FROM `patients` WHERE PatientId = 1 ");
                ResultSet res = st.executeQuery();

                if (res.next()){
                    String firstname = res.getString("fname");
                    String lastname = res.getString("lname");
                    String gender = res.getString("gender");
                    String dob = res.getString("DOB");
                    String phoneNumber = res.getString("phone");
                    String email = res.getString("email");
                    String patientId = res.getString("PatientId");

                    profileData.put("firstname", firstname);
                    profileData.put("lastname", lastname);
                    profileData.put("gender", gender);
                    profileData.put("dob", dob);
                    profileData.put("phoneNumber", phoneNumber);
                    profileData.put("email", email);
                    profileData.put("patientId", patientId);
                    System.out.println(profileData);
                }
            }catch (SQLException ex){
                System.out.println(ex);
            }
        }
        return profileData;
    }
}

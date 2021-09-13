package com.oopii.wellness_centre4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class RecordsPanelController {

    public static ObservableList<Record> getRecordsList(){
        ObservableList<Record> recordsList = FXCollections.observableArrayList();
        Connection connection = DatabaseConnection.connectDB();

        if(connection != null){
            String query = "SELECT appointments.AppointmentId, doctors.fname, doctors.lname, appointments.category, appointments.DateDue, appointments.startTime, appointments.endTime, appointments.status FROM `appointments` LEFT JOIN `doctors` ON appointments.DoctorId = doctors.DoctorId ";
            Statement st;
            ResultSet rs;
            try {
                st = connection.createStatement();
                rs =st.executeQuery(query);
                Record record;

                while(rs.next()){
                    String startTime = rs.getString("startTime");
                    String endTime = rs.getString("endTime");
                    String time;

                    startTime = startTime.substring(0, startTime.length() - 3);
                    if(endTime != null){
                        endTime = endTime.substring(0, endTime.length() - 3);
                        time = startTime + ":" + endTime;
                    }else {
                        time = startTime;
                    }

                    record = new Record(rs.getInt("AppointmentId"), rs.getString("fname") +" " +rs.getString("lname") , rs.getString("category") , rs.getString("DateDue"), time  , rs.getString("status"));
                    recordsList.add(record);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }else {
            System.out.println("DatabaseConnection.connectDB() was null");
        }

        return recordsList;
    }
}

package com.oopii.wellness_centre4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

public class BookNowPanelController {
    static ObservableList  choiceBoxList = FXCollections.observableArrayList();

    public static void uploadBookingInfo(Hashtable<String, String> bookingInfo){
        Connection connection = DatabaseConnection.connectDB();
        if(connection != null){

            PreparedStatement st = null;
            try {
                st = (PreparedStatement)
                        connection.prepareStatement ("INSERT INTO `appointments` (`PatientId`, `DoctorId`, `category`, `DateDue`, `startTime`) VALUES ('2', ?, ?, ?, ?);");
                st.setString(1, getDoctorId(bookingInfo.get("doctor")));
                st.setString(2, bookingInfo.get("specialist"));
                st.setString(3, bookingInfo.get("year") + "-" + getMonthNumber(bookingInfo.get("month")) + "-" + bookingInfo.get("date"));
                st.setString(4, bookingInfo.get("hour") + ":" + bookingInfo.get("minute"));

                st.executeUpdate();
            } catch (SQLException | ParseException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("DatabaseConnection.connectDB() has returned null");
        }
    }

    public static List<String> getSpecialistList(){
        choiceBoxList.removeAll(choiceBoxList);
        choiceBoxList.addAll("General", "Ophthalmologist", "Phsychiatrist", "Dermatologist", "Gynaecologist", "Pulmonologist", "Physiatrists" );
        return choiceBoxList;
    }

    public static List<String> getDoctorsList(){
        choiceBoxList.removeAll(choiceBoxList);
        Connection connection = DatabaseConnection.connectDB();

        if(connection != null){
            try{
                PreparedStatement st = (PreparedStatement)
                        connection.prepareStatement ("SELECT `lname`, `fname` , `DoctorId` FROM `doctors`");
                ResultSet res = st.executeQuery();

                while (res.next()){
                    String listItem = res.getString("lname") + " " + res.getString("fname") + ": " + res.getString("DoctorId");
                    choiceBoxList.add(listItem);
                }
            }catch (SQLException ex){
                System.out.println(ex);
            }
        }

        return choiceBoxList;
    }
     public static List<String> getDateList(){
        choiceBoxList.removeAll(choiceBoxList);

        for(int i = 1; i <= 31; i++){
            Integer count = i;
            choiceBoxList.add(count.toString());
        }

        return choiceBoxList;
     }

     public static  List<String> getMonthList(){
        choiceBoxList.removeAll(choiceBoxList);

         for(int i= 1; i <= 12; i++) {
             choiceBoxList.addAll(Month.of(i)+"");
         }
         //Resorted to using a longer way since the shorter way brought unexplainable errors
//         choiceBoxList.addAll("JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY",
//                 "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER");
        return choiceBoxList;
     }

     public static List<String> getYearList(){
        choiceBoxList.removeAll(choiceBoxList);
        int year = Calendar.getInstance().get(Calendar.YEAR);

        for(int i = 0; i <= 6; i++){
            Integer count = year + i;
            choiceBoxList.add(count.toString());
        }
        return choiceBoxList;
    }

    public static List<String> getMinutesList(){
        choiceBoxList.removeAll(choiceBoxList);

        for(int i = 0; i < 60; i++){
            Integer count = i;
            choiceBoxList.add(count.toString());
        }

        return choiceBoxList;
    }

    public static List<String> getHoursList(){
        choiceBoxList.removeAll(choiceBoxList);

        for(int i = 0; i < 24; i++){
            Integer count = i;
            choiceBoxList.add(count.toString());
        }

        return choiceBoxList;
    }

    public static String getDoctorId(String doctorChoice){
        String[] arrOfStr = doctorChoice.split(" ", 0);
        return  arrOfStr[arrOfStr.length-1];
    }

    public static String getMonthNumber(String monthName) throws ParseException {
        Date date = new SimpleDateFormat("MMMM").parse(monthName);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        System.out.println(cal.get(Calendar.MONTH)+1);
        return "5";
    }
}

package com.oopii.wellness_centre4;

import com.oopii.wellness_centre4.DatabaseConnection;

import java.sql.*;
public class LoginModel {

        Connection connection;
        public LoginModel() {
            Connection connection = DatabaseConnection.connectDB();
            if(connection == null) {
                System.out.println("Path is not connected");
                System.exit(1);
            }
        }

        public boolean isDbConnected() {
            try {
                return !connection.isClosed();
            }catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        public boolean isLogin(String userType, String user, String pass) throws SQLException {
            PreparedStatement preparedStatment = null;
            ResultSet resultSet= null;
            String query = "select * from ? where username = ? and password = ?";
            try {

                preparedStatment = connection.prepareStatement(query);
                preparedStatment.setString(1, userType);
                preparedStatment.setString(2, user);
                preparedStatment.setString(3, pass);
                resultSet = preparedStatment.executeQuery();
                if(resultSet.next()) {
                    return true;
                }
                else {
                    return false;
                }
            } catch (Exception e) {
                return false;
            } finally {
                preparedStatment.close();
                resultSet.close();
            }
        }
    }


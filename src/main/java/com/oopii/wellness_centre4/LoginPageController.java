package com.oopii.wellness_centre4;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.security.auth.spi.LoginModule;

public class LoginPageController {
    public LoginModel loginModel = new LoginModel();
    private String userType;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button loginBtn;

    @FXML
    void logIn(ActionEvent event) throws IOException {


        if (WelcomePage.current_page == "docLogin") {
            userType = "doctors";
        } else if (WelcomePage.current_page == "patientLogin") {
            userType = "patients";
        }

        try {
            if(loginModel.isLogin(userType,usernameField.getText(), passwordField.getText())) {
                if (userType == "doctors") {
                    WelcomePage.stage.setScene(WelcomePage.doctor_page);

                } else if (userType == "patients") {
                    WelcomePage.stage.setScene(WelcomePage.patient_page);

                }

            }else {


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {

    }
}

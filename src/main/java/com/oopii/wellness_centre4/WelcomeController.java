package com.oopii.wellness_centre4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.Objects;

public class WelcomeController {

    @FXML
    private StackPane doctorPane;

    @FXML
    private Label doctorText;

    @FXML
    private Rectangle doctorCard;

    @FXML
    private StackPane patientPane;

    @FXML
    private Label patientText;

    @FXML
    private Rectangle patientCard;

    @FXML
    private Label welcomeText;

    @FXML
    private Button todocLogInBtn,topatientLogInBtn;

    @FXML
    void toLogIn(ActionEvent event) throws IOException {
        if (event.getSource() == todocLogInBtn) {
            WelcomePage.stage.setScene(WelcomePage.login_page);
            WelcomePage.current_page = "docLogin";

        } else if (event.getSource() == topatientLogInBtn) {
            WelcomePage.stage.setScene(WelcomePage.login_page);
            WelcomePage.current_page = "patientLogin";

        }
    }

    @FXML
    void todocLogIn() throws IOException {
        WelcomePage.stage.setScene(WelcomePage.doctor_page);
    }

    @FXML
    void topatientLogIn() throws IOException {
        WelcomePage.stage.setScene(WelcomePage.patient_page);
    }

    @FXML
    void initialize() throws IOException {

    }


}

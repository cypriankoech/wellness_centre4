package com.oopii.wellness_centre4;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class DoctorPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane appointmentsPane, diagnosisPane, prescrPane, usersPane;

    @FXML
    private Button appointmentsBtn, diagnosisBtn, prescriptionsBtn, usersBtn;

    @FXML
    private void docTabSwitcher(ActionEvent event) {
        if (event.getSource() == appointmentsBtn) {
            appointmentsPane.toFront();

        } else if (event.getSource() == diagnosisBtn) {
            diagnosisPane.toFront();

        } else if (event.getSource() == prescriptionsBtn) {
            prescrPane.toFront();

        } else if (event.getSource() == usersBtn) {
            usersPane.toFront();

        }

    }

    @FXML
    void initialize() {
    }
}

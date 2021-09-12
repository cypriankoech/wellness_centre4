package com.oopii.wellness_centre4;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;

public class DoctorPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane appointmentsPane, diagnosisPane, prescrPane, usersPane, editUsersPane;

    @FXML
    private ToggleButton appointmentsBtn, diagnosisBtn, prescriptionsBtn, usersBtn,editUsersBtn;

    @FXML
    private void docTabSwitcher(ActionEvent event) {
        ToggleButton[] sideBarButtons = {appointmentsBtn, diagnosisBtn, prescriptionsBtn, usersBtn, editUsersBtn};
        if (event.getSource() == appointmentsBtn) {
            appointmentsPane.toFront();

        } else if (event.getSource() == diagnosisBtn) {
            diagnosisPane.toFront();

        } else if (event.getSource() == prescriptionsBtn) {
            prescrPane.toFront();

        } else if (event.getSource() == usersBtn) {
            usersPane.toFront();

        } else if (event.getSource() == editUsersBtn) {
            editUsersPane.toFront();

        }

            for (ToggleButton sideBarButton: sideBarButtons) {
            if (sideBarButton != event.getSource()) {
                sideBarButton.setSelected(false);
            }
        }

    }

    @FXML
    void initialize() {
        // start with appointments tab selected
        appointmentsBtn.setSelected(true);
    }
}


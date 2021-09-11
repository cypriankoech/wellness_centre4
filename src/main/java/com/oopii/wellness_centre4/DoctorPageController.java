package com.oopii.wellness_centre4;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class DoctorPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane appointmentsPane;

    @FXML
    private Pane diagnosisPane;

    @FXML
    private Pane prescrPane;

    @FXML
    private Pane usersPane;

    @FXML
    private Button appointmentsBtn;

    @FXML
    private Button diagnosisBtn;

    @FXML
    private Button prescriptionsBtn;

    @FXML
    private Button usersBtn;

    @FXML
    void initialize() {
    }
}

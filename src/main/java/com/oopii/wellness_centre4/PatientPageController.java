package com.oopii.wellness_centre4;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.SVGPath;

import java.net.URL;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.ResourceBundle;

public class PatientPageController implements Initializable {

    //Side Menu Components
    @FXML
    private Pane recordsPane;

    @FXML
    private Pane profilePane;

    @FXML
    private Pane bookNowPane;

    @FXML
    private Button profilesBtn;

    @FXML
    private Button bookNowBtn;

    @FXML
    private Button recordsBtn;

    @FXML
    private Region profileSVGRegion;

    @FXML
    private Region bookNowSVGRegion;

    @FXML
    private Region recordsSVGRegion;

    @FXML
    private Region heartIconSVGRegion;

    //Profiles Panel Components
    @FXML
    private SVGPath sgvIcon;

    @FXML
    private Region genderSVGRegion;

    @FXML
    private Region dobSVGRegion;

    @FXML
    private Region patientIdSVGRegion;

    @FXML
    private Region phoneNumberSVGRegion;

    @FXML
    private Region emailAddressSVGRegion;

    @FXML
    private Region addressSVGRegion;

    @FXML
    private Label nameLabel;

    @FXML
    private Label patientIdLabel;

    @FXML
    private Label phoneNumberLabel;

    @FXML
    private Label emailAddressLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label dobLabel;

    @FXML
    private Label genderLabel;

    //BookNow Panel Components
    @FXML
    private ChoiceBox<String> doctorChoiceBox;

    @FXML
    private ChoiceBox<String> specialistChoiceBox;

    @FXML
    private ChoiceBox<String> dayChoiceBox;

    @FXML
    private ChoiceBox<String> yearChoiceBox;

    @FXML
    private ChoiceBox<String> monthChoiceBox;

    @FXML
    private ChoiceBox<String> hourChoiceBox;

    @FXML
    private ChoiceBox<String> minuteChoiceBox;

    @FXML
    private Button bookBtn;

    //Records Panel
    @FXML
    private TableView<Record> recordsTable;

    @FXML
    private TableColumn<Record, Integer> appointmentIdColumn;

    @FXML
    private TableColumn<Record, String> doctorColumn;

    @FXML
    private TableColumn<Record, String> categoryColumn;

    @FXML
    private TableColumn<Record, String> dateColumn;

    @FXML
    private TableColumn<Record, String> timeColumn;

    @FXML
    private TableColumn<Record, String> statusColumn;



    @Override
    public void initialize(URL url, ResourceBundle rb) {

        loadSVGIcons();
        loadProfileData();
        setSpecialistChoiceBox();
        setDoctorChoiceBox();
        setDayChoiceBox();
        setMonthChoiceBox();
        setYearChoiceBox();
        setMinuteChoiceBox();
        setHourChoiceBox();
        showRecords();
    }

    @FXML
    //To change the various panels for the various actions that can be performed
    private void handleMenuButtonActions(ActionEvent event) {
        if(event.getSource() == profilesBtn){
            profilePane.toFront();
        } else if(event.getSource() == bookNowBtn){
            bookNowPane.toFront();
        } else if(event.getSource() == recordsBtn){
            recordsPane.toFront();
        }
    }

    @FXML
    //Passes the information entered into BookNowPanelController.upload() to upload to the database
    private void handleBookButtonAction(ActionEvent event) {
        Hashtable<String, String> bookingInfo = new Hashtable<String, String>();
        bookingInfo.put("specialist", specialistChoiceBox.getValue());
        bookingInfo.put("doctor", doctorChoiceBox.getValue());
        bookingInfo.put("year", yearChoiceBox.getValue());
        bookingInfo.put("month", monthChoiceBox.getValue());
        bookingInfo.put("date", dayChoiceBox.getValue());
        bookingInfo.put("hour", hourChoiceBox.getValue());
        bookingInfo.put("minute", minuteChoiceBox.getValue());

        BookNowPanelController.uploadBookingInfo(bookingInfo);
    }

    //Gets profile information from a function in PatientProfilePanelController class
    private void loadProfileData(){
        Hashtable profileData;
        profileData = PatientProfilePanelController.getProfileData();

        nameLabel.setText((String) profileData.get("firstname") + " " + profileData.get("lastname"));
        genderLabel.setText((String) profileData.get("gender"));
        dobLabel.setText((String) profileData.get("dob"));
        patientIdLabel.setText((String) profileData.get("patientId"));
        phoneNumberLabel.setText((String) profileData.get("phoneNumber"));
        emailAddressLabel.setText((String) profileData.get("email"));
    }

    //Sets the children for the specialist choiceBox
    private void setSpecialistChoiceBox(){
        specialistChoiceBox.getItems().addAll(BookNowPanelController.getSpecialistList());
        specialistChoiceBox.setValue("General");
    }

    //Sets the children for the doctors choiceBox
    private void setDoctorChoiceBox(){
        doctorChoiceBox.getItems().addAll(BookNowPanelController.getDoctorsList());
    }

    //Sets the children for the day choice Box
    private  void setDayChoiceBox(){
        dayChoiceBox.getItems().addAll(BookNowPanelController.getDateList());
        dayChoiceBox.setValue("1");
    }

    //Sets the children for the month choice Box
    private  void setMonthChoiceBox(){
        monthChoiceBox.getItems().addAll(BookNowPanelController.getMonthList());
        monthChoiceBox.setValue("JANUARY");
    }

    //Sets the children for the year choice Box
    private  void setYearChoiceBox(){
        yearChoiceBox.getItems().addAll(BookNowPanelController.getYearList());
        yearChoiceBox.setValue(""+Calendar.getInstance().get(Calendar.YEAR)+"");
    }

    //Sets the children for the year choice Box
    private  void setMinuteChoiceBox(){
        minuteChoiceBox.getItems().addAll(BookNowPanelController.getMinutesList());
        minuteChoiceBox.setValue("0");
    }

    //Sets the children for the year choice Box
    private  void setHourChoiceBox(){
        hourChoiceBox.getItems().addAll(BookNowPanelController.getHoursList());
        hourChoiceBox.setValue("0");
    }

    private void showRecords() {
        ObservableList<Record> list = RecordsPanelController.getRecordsList();

        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<Record, Integer>("appointmentId"));
        doctorColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("doctorName"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("specialist"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("dateDue"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("times"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("status"));

        recordsTable.setItems(list);
    }

    private void loadSVGIcons(){
        //First intialize the SVGs
        //User Icon
        SVGPath userIcon = new SVGPath();
        userIcon.setContent("M55,27.5C55,12.337,42.663,0,27.5,0S0,12.337,0,27.5c0,8.009,3.444,15.228,8.926,20.258l-0.026,0.023l0.892,0.752c0.058,0.049,0.121,0.089,0.179,0.137c0.474,0.393,0.965,0.766,1.465,1.127c0.162,0.117,0.324,0.234,0.489,0.348c0.534,0.368,1.082,0.717,1.642,1.048c0.122,0.072,0.245,0.142,0.368,0.212c0.613,0.349,1.239,0.678,1.88,0.98c0.047,0.022,0.095,0.042,0.142,0.064c2.089,0.971,4.319,1.684,6.651,2.105c0.061,0.011,0.122,0.022,0.184,0.033c0.724,0.125,1.456,0.225,2.197,0.292c0.09,0.008,0.18,0.013,0.271,0.021C25.998,54.961,26.744,55,27.5,55c0.749,0,1.488-0.039,2.222-0.098c0.093-0.008,0.186-0.013,0.279-0.021c0.735-0.067,1.461-0.164,2.178-0.287c0.062-0.011,0.125-0.022,0.187-0.034c2.297-0.412,4.495-1.109,6.557-2.055c0.076-0.035,0.153-0.068,0.229-0.104c0.617-0.29,1.22-0.603,1.811-0.936c0.147-0.083,0.293-0.167,0.439-0.253c0.538-0.317,1.067-0.648,1.581-1c0.185-0.126,0.366-0.259,0.549-0.391c0.439-0.316,0.87-0.642,1.289-0.983c0.093-0.075,0.193-0.14,0.284-0.217l0.915-0.764l-0.027-0.023C51.523,42.802,55,35.55,55,27.5z M2,27.5C2,13.439,13.439,2,27.5,2S53,13.439,53,27.5c0,7.577-3.325,14.389-8.589,19.063c-0.294-0.203-0.59-0.385-0.893-0.537l-8.467-4.233c-0.76-0.38-1.232-1.144-1.232-1.993v-2.957c0.196-0.242,0.403-0.516,0.617-0.817c1.096-1.548,1.975-3.27,2.616-5.123c1.267-0.602,2.085-1.864,2.085-3.289v-3.545c0-0.867-0.318-1.708-0.887-2.369v-4.667c0.052-0.519,0.236-3.448-1.883-5.864C34.524,9.065,31.541,8,27.5,8s-7.024,1.065-8.867,3.168c-2.119,2.416-1.935,5.345-1.883,5.864v4.667c-0.568,0.661-0.887,1.502-0.887,2.369v3.545c0,1.101,0.494,2.128,1.34,2.821c0.81,3.173,2.477,5.575,3.093,6.389v2.894c0,0.816-0.445,1.566-1.162,1.958l-7.907,4.313c-0.252,0.137-0.502,0.297-0.752,0.476C5.276,41.792,2,35.022,2,27.5z M42.459,48.132c-0.35,0.254-0.706,0.5-1.067,0.735c-0.166,0.108-0.331,0.216-0.5,0.321c-0.472,0.292-0.952,0.57-1.442,0.83c-0.108,0.057-0.217,0.111-0.326,0.167c-1.126,0.577-2.291,1.073-3.488,1.476c-0.042,0.014-0.084,0.029-0.127,0.043c-0.627,0.208-1.262,0.393-1.904,0.552c-0.002,0-0.004,0.001-0.006,0.001c-0.648,0.16-1.304,0.293-1.964,0.402c-0.018,0.003-0.036,0.007-0.054,0.01c-0.621,0.101-1.247,0.174-1.875,0.229c-0.111,0.01-0.222,0.017-0.334,0.025C28.751,52.97,28.127,53,27.5,53c-0.634,0-1.266-0.031-1.895-0.078c-0.109-0.008-0.218-0.015-0.326-0.025c-0.634-0.056-1.265-0.131-1.89-0.233c-0.028-0.005-0.056-0.01-0.084-0.015c-1.322-0.221-2.623-0.546-3.89-0.971c-0.039-0.013-0.079-0.027-0.118-0.04c-0.629-0.214-1.251-0.451-1.862-0.713c-0.004-0.002-0.009-0.004-0.013-0.006c-0.578-0.249-1.145-0.525-1.705-0.816c-0.073-0.038-0.147-0.074-0.219-0.113c-0.511-0.273-1.011-0.568-1.504-0.876c-0.146-0.092-0.291-0.185-0.435-0.279c-0.454-0.297-0.902-0.606-1.338-0.933c-0.045-0.034-0.088-0.07-0.133-0.104c0.032-0.018,0.064-0.036,0.096-0.054l7.907-4.313c1.36-0.742,2.205-2.165,2.205-3.714l-0.001-3.602l-0.23-0.278c-0.022-0.025-2.184-2.655-3.001-6.216l-0.091-0.396l-0.341-0.221c-0.481-0.311-0.769-0.831-0.769-1.392v-3.545c0-0.465,0.197-0.898,0.557-1.223l0.33-0.298v-5.57l-0.009-0.131c-0.003-0.024-0.298-2.429,1.396-4.36C21.583,10.837,24.061,10,27.5,10c3.426,0,5.896,0.83,7.346,2.466c1.692,1.911,1.415,4.361,1.413,4.381l-0.009,5.701l0.33,0.298c0.359,0.324,0.557,0.758,0.557,1.223v3.545c0,0.713-0.485,1.36-1.181,1.575l-0.497,0.153l-0.16,0.495c-0.59,1.833-1.43,3.526-2.496,5.032c-0.262,0.37-0.517,0.698-0.736,0.949l-0.248,0.283V39.8c0,1.612,0.896,3.062,2.338,3.782l8.467,4.233c0.054,0.027,0.107,0.055,0.16,0.083C42.677,47.979,42.567,48.054,42.459,48.132z");

        //BookNow Icon
        SVGPath bookNowIcon = new SVGPath();
        bookNowIcon.setContent("m435.710938 117.226562-6.925782-4 12-20.796874 6.925782 4 12-20.800782c3.1875-5.515625 4.050781-12.074218 2.398437-18.226562-1.648437-6.152344-5.679687-11.394532-11.199219-14.574219l-13.863281-8c-5.515625-3.1875-12.070313-4.050781-18.222656-2.398437-6.152344 1.652343-11.394531 5.679687-14.578125 11.199218l-12 20.800782 6.929687 4-12 20.796874-6.929687-4-12.246094 21.28125v-106.296874h-320c-26.5.027343-47.9726562 21.503906-48 48v352c.0273438 26.5 21.5 47.972656 48 48h320v-213.703126l59.710938-103.421874 13.855468 8-24 41.597656 13.859375 8 32-55.421875zm-6.640626-68.503906 13.859376 8c1.835937 1.0625 3.179687 2.8125 3.730468 4.863282.546875 2.050781.257813 4.234374-.804687 6.074218l-4 6.929688-27.710938-16 4-6.929688c2.207031-3.828125 7.097657-5.144531 10.925781-2.9375zm-14.613281 28.519532 12.472657 7.203124-12 20.796876-13.859376-8 12-20.796876zm-366.457031-61.03125h304v24h-312v16h312v24h-304c-17.671875 0-32-14.324219-32-32 0-17.671876 14.328125-32 32-32zm304 416h-296v-296h-16v294.863281c-14.085938-3.636719-23.945312-16.316407-24-30.863281v-316.320313c6.738281 6.0625 15.0625 10.085937 24 11.601563v24.71875h16v-24h296v38.007812l-116.65625 202.058594-4.289062 71.429687 59.714843-39.429687 61.230469-106.058594zm-101.039062-89.09375 26.078124 15.054687-28.09375 18.542969zm34.894531 1.664062-6.925781-4 108-187.058594-13.859376-8-108 187.058594-3.460937-2-3.464844-2 128-221.695312 27.710938 16zm0 0");

        //Records Icon
        SVGPath recordsIcon = new SVGPath();
        recordsIcon.setContent("m72.808594 48h-64.960938c-4.332031.003906-7.84374975 3.515625-7.847656 7.847656v384.304688c.00390625 4.332031 3.515625 7.84375 7.847656 7.847656h288.3125c4.332032-.003906 7.84375-3.515625 7.847656-7.847656v-384.304688c-.003906-4.332031-3.515624-7.84375-7.847656-7.847656h-64.953125c-3.828125 18.613281-20.199219 31.976562-39.199219 32h-80c-19-.023438-35.371093-13.386719-39.199218-32zm215.199218 360h-272v-16h272zm0-32h-272v-16h272zm0-32h-272v-16h272zm0-32h-272v-16h272zm0-32h-272v-16h272zm-183.160156-175.960938c3.742188.394532 6.699219 3.34375 7.113282 7.082032l5.382812 48.4375 11.808594-19.679688c1.640625-2.726562 4.746094-4.222656 7.902344-3.804687s5.761718 2.664062 6.640624 5.726562l8.90625 31.136719 15.8125-47.464844c1.023438-3.074218 3.796876-5.230468 7.027344-5.460937 3.230469-.226563 6.28125 1.511719 7.726563 4.414062l13.785156 27.574219h91.054687v16h-96c-3.046874.015625-5.839843-1.699219-7.199218-4.425781l-7.359375-14.726563-17.890625 53.679688c-1.136719 3.304687-4.261719 5.511718-7.757813 5.472656-3.507812-.074219-6.558593-2.425781-7.519531-5.800781l-10.9375-38.269531-14.519531 24.191406c-1.769531 2.941406-5.214844 4.425781-8.566407 3.691406-3.351562-.730469-5.863281-3.523438-6.242187-6.933594l-4-35.789062-20.480469 61.4375c-1.050781 3.152344-3.9375 5.324218-7.253906 5.464844h-.273438c-3.199218 0-6.089843-1.90625-7.351562-4.847657l-21.921875-51.144531h-26.726563v-16h32c3.199219 0 6.089844 1.90625 7.351563 4.847656l15.710937 36.65625 25.34375-76c1.195313-3.5625 4.695313-5.832031 8.433594-5.464844zm0 0");

        //Heart Icon
        SVGPath heartIcon = new SVGPath();
        heartIcon.setContent("M413.6,204H378c14.8-33.6,13.2-60.8,7.6-79.2c-9.6-32.8-42-68.4-90-68.4c-12.8,0-25.6,2.4-38.8,7.2\n" +
                "\t\t\tC234,72,218.8,81.2,212,86c-6.8-4.8-21.6-14-44.8-22.4c-13.2-4.8-26.4-7.2-38.8-7.2c-48,0-80.4,35.6-90,68.4\n" +
                "\t\t\tc-5.6,19.6-7.6,49.2,11.2,86.4H6.4c-3.6,0-6.4,2.8-6.4,6.4c0,3.6,2.8,6.4,6.4,6.4h50.8c2,3.6,4.4,7.2,7.2,10.8\n" +
                "\t\t\tc41.6,60,132,125.2,148.4,129.2v0.4c0,0,0.4,0,0.8-0.4c0.4,0,0.8,0,1.2,0v-0.4c14-6.8,100.8-62.8,146.4-129.2c4.4-6,8-12,11.2-18\n" +
                "\t\t\th42c3.6,0,6.4-2.8,6.4-6.4C420.8,206.4,417.2,204,413.6,204z M349.6,227.6c-42.4,62-120.4,112-137.2,122.8\n" +
                "\t\t\tc-17.2-10.8-94.8-61.2-137.6-122.8c-0.8-1.2-1.6-2.4-2.4-3.6h58.8c2.4,0,4.8-1.2,6-3.6l26.8-52.8l42,107.6c0.8,2.4,3.6,4,6,4\n" +
                "\t\t\tc0,0,0,0,0.4,0c2.8,0,5.2-2,6-4.8l36.8-126l19.6,63.6c0.8,2.4,2.8,4,5.2,4.4c2.4,0.4,4.8-0.8,6.4-2.8l15.6-22.8l13.2,22.4\n" +
                "\t\t\tc1.2,2,3.2,3.2,5.6,3.2h36C354.4,220.4,352,224,349.6,227.6z M364,204h-39.2l-16.4-28c-1.2-2-3.2-3.2-5.6-3.2\n" +
                "\t\t\tc-2.4,0-4.4,0.8-5.6,2.8l-13.6,20l-22-71.6c-0.8-2.8-3.6-4.4-6.4-4.8c-2.8,0-5.6,2-6.4,4.8l-37.2,128.4l-40.4-103.2\n" +
                "\t\t\tc-0.8-2.4-3.2-4-5.6-4c-2.8,0-4.8,1.2-6,3.6l-31.6,62H64.4c-16-29.2-20.4-57.2-13.2-82.4c8.4-28.4,36-58.8,77.6-58.8\n" +
                "\t\t\tc11.2,0,22.8,2.4,34.4,6.4c29.2,10.8,44.8,23.2,45.2,23.2c2.4,2,5.6,2,8,0c0,0,15.6-12.4,45.2-23.2c11.6-4.4,23.2-6.4,34.4-6.4\n" +
                "\t\t\tc41.2,0,69.2,30.4,77.6,58.8C380.4,151.6,376.8,177.2,364,204z");

        //Gender Icon
        SVGPath genderIcon = new SVGPath();
        genderIcon.setContent("M403.921,0v31.347h35.36l-68.982,65.409c-24.421-24.99-58.474-40.53-96.092-40.53c-50.603,0-94.759,28.112-117.687,69.535\n" +
                "\t\t\tc-1.964-0.086-3.938-0.138-5.924-0.138c-74.118,0-134.417,60.299-134.417,134.418c0,68.816,51.984,125.71,118.743,133.498v41.657\n" +
                "\t\t\tH87.995v31.347h46.929V512h31.347v-45.458h48.977v-31.347h-48.977v-41.657c43.948-5.127,81.488-31.533,102.013-68.616\n" +
                "\t\t\tc1.964,0.086,3.937,0.138,5.922,0.138c74.119,0,134.418-60.299,134.418-134.417c0-25.187-6.969-48.774-19.071-68.944\n" +
                "\t\t\tl74.919-71.038v38.933h31.347V0H403.921z M150.598,363.11c-56.833,0-103.07-46.237-103.07-103.071\n" +
                "\t\t\tc0-54.619,42.705-99.442,96.477-102.853c-2.751,10.7-4.215,21.91-4.215,33.457c0,60.464,40.132,111.726,95.157,128.562\n" +
                "\t\t\tC216.281,345.738,185.432,363.11,150.598,363.11z M249.044,290.6c-44.709-11.26-77.906-51.802-77.906-99.957\n" +
                "\t\t\tc0-10.636,1.62-20.901,4.625-30.561c44.709,11.26,77.906,51.803,77.906,99.958C253.669,270.676,252.048,280.94,249.044,290.6z\n" +
                "\t\t\t M280.801,293.495c2.751-10.7,4.215-21.909,4.215-33.456c0-60.464-40.132-111.726-95.156-128.563\n" +
                "\t\t\tc18.666-26.532,49.516-43.905,84.349-43.905c56.834,0,103.071,46.237,103.071,103.071\n" +
                "\t\t\tC377.278,245.261,334.573,290.085,280.801,293.495z");

        //DOB Icon
        SVGPath dobIcon = new SVGPath();
        dobIcon.setContent("M467,30h-15V15c0-8.284-6.716-15-15-15s-15,6.716-15,15v15h-61V15c0-8.284-6.716-15-15-15s-15,6.716-15,15v15h-60V15\n" +
                "\t\t\tc0-8.284-6.716-15-15-15s-15,6.716-15,15v15h-60V15c0-8.284-6.716-15-15-15s-15,6.716-15,15v15H90V15c0-8.284-6.716-15-15-15\n" +
                "\t\t\tS60,6.716,60,15v15H45C20.187,30,0,50.187,0,75c0,18.376,0,371.361,0,392c0,24.813,20.187,45,45,45h422c24.813,0,45-20.187,45-45\n" +
                "\t\t\tc0-20.645,0-373.633,0-392C512,50.187,491.813,30,467,30z M482,467c0,8.271-6.729,15-15,15H45c-8.271,0-15-6.729-15-15V150h452\n" +
                "\t\t\tV467z M482,120H30V75c0-8.271,6.729-15,15-15h15v15c0,8.284,6.716,15,15,15s15-6.716,15-15V60h61v15c0,8.284,6.716,15,15,15\n" +
                "\t\t\ts15-6.716,15-15V60h60v15c0,8.284,6.716,15,15,15s15-6.716,15-15V60h60v15c0,8.284,6.716,15,15,15s15-6.716,15-15V60h61v15\n" +
                "\t\t\tc0,8.284,6.716,15,15,15s15-6.716,15-15V60h15c8.271,0,15,6.729,15,15V120z M376,422h-15v-45c0-24.813-20.187-45-45-45h-45v-33.58c17.459-6.192,30-22.865,30-42.42c0-7.622-2.176-15.39-4.412-19.297\n" +
                "\t\t\tl-27.57-48.156C266.345,183.879,261.378,181,256,181s-10.345,2.879-13.018,7.547l-27.57,48.156\n" +
                "\t\t\tC213.213,240.546,211,248.275,211,256c0,19.555,12.541,36.228,30,42.42V332h-45c-24.813,0-45,20.187-45,45v45h-15\n" +
                "\t\t\tc-8.284,0-15,6.716-15,15s6.716,15,15,15c7.996,0,227.523,0,240,0c8.284,0,15-6.716,15-15S384.284,422,376,422z M241.944,250.739\n" +
                "\t\t\tL256,226.189l14.056,24.55c0.627,1.672,0.944,3.438,0.944,5.261c0,8.271-6.729,15-15,15s-15-6.729-15-15\n" +
                "\t\t\tC241,254.178,241.317,252.412,241.944,250.739z M331,422H181v-45c0-8.271,6.729-15,15-15h120c8.271,0,15,6.729,15,15V422z");

        //Patient ID Icon
        SVGPath patientIcon = new SVGPath();
        patientIcon.setContent("m21.25 3h-18.5c-1.517 0-2.75 1.233-2.75 2.75v12.5c0 1.517 1.233 2.75 2.75 2.75h18.5c1.517 0 2.75-1.233 2.75-2.75v-12.5c0-1.517-1.233-2.75-2.75-2.75zm-13.75 4c1.378 0 2.5 1.122 2.5 2.5s-1.122 2.5-2.5 2.5-2.5-1.122-2.5-2.5 1.122-2.5 2.5-2.5zm4.5 9.25c0 .414-.336.75-.75.75h-7.5c-.414 0-.75-.336-.75-.75v-.5c0-1.517 1.233-2.75 2.75-2.75h3.5c1.517 0 2.75 1.233 2.75 2.75zm8.25.75h-5.5c-.414 0-.75-.336-.75-.75s.336-.75.75-.75h5.5c.414 0 .75.336.75.75s-.336.75-.75.75zm0-4h-5.5c-.414 0-.75-.336-.75-.75s.336-.75.75-.75h5.5c.414 0 .75.336.75.75s-.336.75-.75.75zm0-4h-5.5c-.414 0-.75-.336-.75-.75s.336-.75.75-.75h5.5c.414 0 .75.336.75.75s-.336.75-.75.75z");
        //Phone number Icon
        SVGPath phoneNumberIcon = new SVGPath();
        phoneNumberIcon.setContent("M98.339,320.8c47.6,56.9,104.9,101.7,170.3,133.4c24.9,11.8,58.2,25.8,95.3,28.2c2.3,0.1,4.5,0.2,6.8,0.2\n" +
                "\t\tc24.9,0,44.9-8.6,61.2-26.3c0.1-0.1,0.3-0.3,0.4-0.5c5.8-7,12.4-13.3,19.3-20c4.7-4.5,9.5-9.2,14.1-14\n" +
                "\t\tc21.3-22.2,21.3-50.4-0.2-71.9l-60.1-60.1c-10.2-10.6-22.4-16.2-35.2-16.2c-12.8,0-25.1,5.6-35.6,16.1l-35.8,35.8\n" +
                "\t\tc-3.3-1.9-6.7-3.6-9.9-5.2c-4-2-7.7-3.9-11-6c-32.6-20.7-62.2-47.7-90.5-82.4c-14.3-18.1-23.9-33.3-30.6-48.8\n" +
                "\t\tc9.4-8.5,18.2-17.4,26.7-26.1c3-3.1,6.1-6.2,9.2-9.3c10.8-10.8,16.6-23.3,16.6-36s-5.7-25.2-16.6-36l-29.8-29.8\n" +
                "\t\tc-3.5-3.5-6.8-6.9-10.2-10.4c-6.6-6.8-13.5-13.8-20.3-20.1c-10.3-10.1-22.4-15.4-35.2-15.4c-12.7,0-24.9,5.3-35.6,15.5l-37.4,37.4\n" +
                "\t\tc-13.6,13.6-21.3,30.1-22.9,49.2c-1.9,23.9,2.5,49.3,13.9,80C32.739,229.6,59.139,273.7,98.339,320.8z M25.739,104.2\n" +
                "\t\tc1.2-13.3,6.3-24.4,15.9-34l37.2-37.2c5.8-5.6,12.2-8.5,18.4-8.5c6.1,0,12.3,2.9,18,8.7c6.7,6.2,13,12.7,19.8,19.6\n" +
                "\t\tc3.4,3.5,6.9,7,10.4,10.6l29.8,29.8c6.2,6.2,9.4,12.5,9.4,18.7s-3.2,12.5-9.4,18.7c-3.1,3.1-6.2,6.3-9.3,9.4\n" +
                "\t\tc-9.3,9.4-18,18.3-27.6,26.8c-0.2,0.2-0.3,0.3-0.5,0.5c-8.3,8.3-7,16.2-5,22.2c0.1,0.3,0.2,0.5,0.3,0.8\n" +
                "\t\tc7.7,18.5,18.4,36.1,35.1,57.1c30,37,61.6,65.7,96.4,87.8c4.3,2.8,8.9,5,13.2,7.2c4,2,7.7,3.9,11,6c0.4,0.2,0.7,0.4,1.1,0.6\n" +
                "\t\tc3.3,1.7,6.5,2.5,9.7,2.5c8,0,13.2-5.1,14.9-6.8l37.4-37.4c5.8-5.8,12.1-8.9,18.3-8.9c7.6,0,13.8,4.7,17.7,8.9l60.3,60.2\n" +
                "\t\tc12,12,11.9,25-0.3,37.7c-4.2,4.5-8.6,8.8-13.3,13.3c-7,6.8-14.3,13.8-20.9,21.7c-11.5,12.4-25.2,18.2-42.9,18.2\n" +
                "\t\tc-1.7,0-3.5-0.1-5.2-0.2c-32.8-2.1-63.3-14.9-86.2-25.8c-62.2-30.1-116.8-72.8-162.1-127c-37.3-44.9-62.4-86.7-79-131.5\n" +
                "\t\tC28.039,146.4,24.139,124.3,25.739,104.2z");

        //Email Address Icon
        SVGPath emailAddressIcon = new SVGPath();
        emailAddressIcon.setContent("M405.333,213.874V106.667c0-23.531-19.135-42.667-42.667-42.667h-320C19.135,64,0,83.135,0,106.667V320\n" +
                "\t\t\tc0,23.531,19.135,42.667,42.667,42.667h239.215C295.858,411.84,341.073,448,394.667,448c20.625,0,40.906-5.427,58.677-15.708\n" +
                "\t\t\tc5.094-2.948,6.844-9.469,3.885-14.573c-2.948-5.104-9.479-6.865-14.573-3.885c-14.521,8.396-31.115,12.833-47.99,12.833\n" +
                "\t\t\tc-52.938,0-96-43.063-96-96s43.063-96,96-96s96,43.063,96,96v10.667c0,11.76-9.573,21.333-21.333,21.333\n" +
                "\t\t\tc-11.76,0-21.333-9.573-21.333-21.333v-42.667c0-5.896-4.771-10.667-10.667-10.667c-2.869,0-5.447,1.161-7.362,3\n" +
                "\t\t\tc-9.428-8.401-21.714-13.667-35.305-13.667c-29.406,0-53.333,23.927-53.333,53.333S365.26,384,394.667,384\n" +
                "\t\t\tc15.896,0,30.03-7.131,39.81-18.202c7.727,10.977,20.44,18.202,34.857,18.202C492.865,384,512,364.865,512,341.333v-10.667\n" +
                "\t\t\tC512,269.569,465.044,219.288,405.333,213.874z M42.667,85.333h320c0.444,0,0.816,0.227,1.254,0.254L211.438,210.75\n" +
                "\t\t\tc-5.427,3.417-13.292,2.708-16.823,0.542L41.426,85.585C41.859,85.559,42.227,85.333,42.667,85.333z M384,213.874\n" +
                "\t\t\tc-59.711,5.414-106.667,55.695-106.667,116.793c0,3.6,0.221,7.148,0.54,10.667H42.667c-11.76,0-21.333-9.573-21.333-21.333\n" +
                "\t\t\tV106.667c0-3.021,0.667-5.874,1.805-8.48l158.883,130.293c6.208,4.052,13.344,6.188,20.646,6.188\n" +
                "\t\t\tc7.021,0,13.885-1.979,19.927-5.729c0.604-0.323,1.177-0.708,1.719-1.156l157.88-129.598c1.139,2.608,1.807,5.461,1.807,8.483\n" +
                "\t\t\tV213.874z M394.667,362.667c-17.646,0-32-14.354-32-32c0-17.646,14.354-32,32-32s32,14.354,32,32\n" +
                "\t\t\tC426.667,348.313,412.313,362.667,394.667,362.667z");

        //Address Icon
        SVGPath addressIcon = new SVGPath();
        addressIcon.setContent("M256,0C161.896,0,85.333,76.563,85.333,170.667c0,28.25,7.063,56.26,20.49,81.104L246.667,506.5\n" +
                "\t\t\tc1.875,3.396,5.448,5.5,9.333,5.5s7.458-2.104,9.333-5.5l140.896-254.813c13.375-24.76,20.438-52.771,20.438-81.021\n" +
                "\t\t\tC426.667,76.563,350.104,0,256,0z M256,256c-47.052,0-85.333-38.281-85.333-85.333c0-47.052,38.281-85.333,85.333-85.333\n" +
                "\t\t\ts85.333,38.281,85.333,85.333C341.333,217.719,303.052,256,256,256z");

        //Set Region Shapes
        profileSVGRegion.setShape(userIcon);
        bookNowSVGRegion.setShape(bookNowIcon);
        recordsSVGRegion.setShape(recordsIcon);
        heartIconSVGRegion.setShape(heartIcon);
        genderSVGRegion.setShape(genderIcon);
        dobSVGRegion.setShape(dobIcon);
        patientIdSVGRegion.setShape(patientIcon);
        phoneNumberSVGRegion.setShape(phoneNumberIcon);
        emailAddressSVGRegion.setShape(emailAddressIcon);
        addressSVGRegion.setShape(addressIcon);

        //Set Colors
        profileSVGRegion.setStyle("-fx-background-color: black;");
        bookNowSVGRegion.setStyle("-fx-background-color: black;");
        recordsSVGRegion.setStyle("-fx-background-color: black;");
        heartIconSVGRegion.setStyle("-fx-background-color: #ff1457;");
        genderSVGRegion.setStyle("-fx-background-color: black;");
        dobSVGRegion.setStyle("-fx-background-color: black;");
        phoneNumberSVGRegion.setStyle("-fx-background-color: black;");
        patientIdSVGRegion.setStyle("-fx-background-color: black;");
        emailAddressSVGRegion.setStyle("-fx-background-color: black;");
        addressSVGRegion.setStyle("-fx-background-color: black;");



    }

    private  static Region createRegionIcon(String svgData, String color){
        Region svgRegion = new Region();
        SVGPath icon = new SVGPath();

        icon.setContent(svgData);
        svgRegion.setShape(icon);

        svgRegion.setMinSize(20.0, 20.0);
        svgRegion.setPrefSize(25.0, 25.0);
        svgRegion.setMaxSize(30.0, 30.0);
        svgRegion.setStyle("-fx-background-color: " + color);
        System.out.println("svgRegion" + svgRegion);

        return svgRegion;
    }

}

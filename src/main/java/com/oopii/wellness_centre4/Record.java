package com.oopii.wellness_centre4;

public class Record {
    private int appointmentId;
    private String doctorName;
    private String specialist;
    private String dateDue;
    private String times;
    private String status;

    public Record(int appointmentId, String doctorName, String specialist, String dateDue, String times, String status) {
        this.appointmentId = appointmentId;
        this.doctorName = doctorName;
        this.specialist = specialist;
        this.dateDue = dateDue;
        this.times = times;
        this.status = status;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getSpecialist() {
        return specialist;
    }

    public String getDateDue() {
        return dateDue;
    }

    public String getTimes() {
        return times;
    }

    public String getStatus() {
        return status;
    }
}


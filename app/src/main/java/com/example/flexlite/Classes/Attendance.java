package com.example.flexlite.Classes;

public class Attendance {
    private int lectureNo;
    private String Status;
    private Registration RegID;
    private int id;
    private String date;

    public Attendance(int lectureNo, String status, Registration regID, int id, String date) {
        this.lectureNo = lectureNo;
        Status = status;
        RegID = regID;
        this.id = id;
        this.date = date;
    }

    public int getLectureNo() {
        return lectureNo;
    }

    public void setLectureNo(int lectureNo) {
        this.lectureNo = lectureNo;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Registration getRegID() {
        return RegID;
    }

    public void setRegID(Registration regID) {
        RegID = regID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

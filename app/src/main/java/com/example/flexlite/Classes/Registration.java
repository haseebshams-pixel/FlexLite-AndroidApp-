package com.example.flexlite.Classes;

import java.util.ArrayList;

public class Registration {
    private int RegId;
    private String Status;
    private Student Stud;
    private Section Sec;
    private ArrayList<Assessment> AssessmentList;
    private ArrayList<Attendance> AttendanceList;

    public Registration(int regId, String status, Student stud, Section sec) {
        RegId = regId;
        Status = status;
        Stud = stud;
        Sec = sec;
        this.AssessmentList = new ArrayList<Assessment>();
        this.AttendanceList = new ArrayList<Attendance>();
    }

    public int getRegId() {
        return RegId;
    }

    public void setRegId(int regId) {
        RegId = regId;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Student getStud() {
        return Stud;
    }

    public void setStud(Student stud) {
        Stud = stud;
    }

    public Section getSec() {
        return Sec;
    }

    public void setSec(Section sec) {
        Sec = sec;
    }

    public ArrayList<Assessment> getAssessmentList() {
        return AssessmentList;
    }

    public void setAssessmentList(ArrayList<Assessment> assessmentList) {
        AssessmentList = assessmentList;
    }

    public ArrayList<Attendance> getAttendanceList() {
        return AttendanceList;
    }

    public void setAttendanceList(ArrayList<Attendance> attendanceList) {
        AttendanceList = attendanceList;
    }
}

package com.example.flexlite.Classes;

import android.util.Log;

import com.example.flexlite.Firebase.IFlexLiteDAO;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;

public class Registration {
    private String id;
    private String status;
    private String StudId;
    private String sectionId;
    private IFlexLiteDAO dao;
    private ArrayList<Assessment> AssessmentList;
    private ArrayList<Attendance> AttendanceList;

    public Registration(String status, String stud, String sec) {
        this.id = UUID.randomUUID().toString();
        this.status = status;
        StudId = stud;
        sectionId = sec;
        this.AssessmentList = new ArrayList<Assessment>();
        this.AttendanceList = new ArrayList<Attendance>();
    }

    public void setDao(IFlexLiteDAO dao) {
        this.dao = dao;
    }

    public Registration(IFlexLiteDAO dao) {
       this.dao = dao;
    }

    public String getid() {
        return id;
    }

    public void setid(String id) {
        id = id;
    }

    public String getstatus() {
        return status;
    }

    public void setstatus(String status) {
        this.status = status;
    }

    public String getStud() {
        return StudId;
    }

    public void setStud(String stud) {
        StudId = stud;
    }

    public String getSec() {
        return sectionId;
    }

    public void setSec(String sec) {
        sectionId = sec;
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

    public void save() {
        if (dao != null) {
            Log.d("Data", "Data is Stored");
            Hashtable<String, String> data = new Hashtable<String, String>();
            data.put("id", this.id);
            data.put("sectionId",this.sectionId);
            data.put("status",this.status);
            data.put("studId",this.StudId);
            dao.save(data);
        }
    }

    public void load(Hashtable<String, String> data) {
        this.id = data.get("id");
        this.status = data.get("status");
        this.StudId= data.get("studId");
        this.sectionId = data.get("sectionId");
    }

    public static ArrayList <Registration> load(IFlexLiteDAO dao,String id) {
        ArrayList<Registration> notes = new ArrayList<Registration>();
        if (dao != null) {
            ArrayList<Hashtable<String, String>> objects = dao.load();
            for (Hashtable<String, String> obj : objects) {
                Registration donor = new Registration(dao);
                donor.load(obj);
                if(donor.getStud().equals(id)){
                    notes.add(donor);
                }
            }
        }
        return  notes;
    }
}

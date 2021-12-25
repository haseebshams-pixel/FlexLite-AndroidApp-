package com.example.flexlite.Classes;

import android.util.Log;

import com.example.flexlite.Firebase.IFlexLiteDAO;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;

public class Attendance {
    private String lectureNo;
    private String Status;
    private String RegID;
    private String id;
    private String date;
    private IFlexLiteDAO dao;

    public Attendance(String lectureNo, String status, String regID, String date) {
        this.lectureNo = lectureNo;
        this.Status = status;
        this.RegID = regID;
        this.id = UUID.randomUUID().toString();
        this.date = date;
    }

    public Attendance(IFlexLiteDAO dao){
        this.dao = dao;
        this.id=UUID.randomUUID().toString();
    }

    public IFlexLiteDAO getDao() {
        return dao;
    }

    public void setDao(IFlexLiteDAO dao) {
        this.dao = dao;
    }

    public String getLectureNo() {
        return lectureNo;
    }

    public void setLectureNo(String lectureNo) {
        this.lectureNo = lectureNo;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getRegID() {
        return RegID;
    }

    public void setRegID(String regID) {
        RegID = regID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public void save() {
        if (dao != null) {
            Log.d("Data", "Data is Stored");
            Hashtable<String, String> data = new Hashtable<String, String>();
            data.put("id", this.id);
            data.put("date", this.date);
            data.put("lecNo", this.lectureNo);
            data.put("regId", this.RegID);
            data.put("status", this.Status);
            dao.save(data);
        }
    }

    public void load(Hashtable<String, String> data) {
        this.id = data.get("id");
        this.date = data.get("date");
        this.lectureNo = data.get("lecNo");
        this.RegID = data.get("regId");
        this.Status = data.get("status");

    }

    public static ArrayList<Attendance> load(IFlexLiteDAO dao) {
        ArrayList<Attendance> notes = new ArrayList<Attendance>();
        if (dao != null) {
            ArrayList<Hashtable<String, String>> objects = dao.load();
            for (Hashtable<String, String> obj : objects) {
                Attendance donor = new Attendance(dao);
                donor.load(obj);
                notes.add(donor);
            }
        }
        return notes;
    }
}

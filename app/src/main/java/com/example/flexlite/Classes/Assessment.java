package com.example.flexlite.Classes;

import android.util.Log;

import com.example.flexlite.Firebase.IFlexLiteDAO;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;

public class Assessment {
    private String id;
    private String totalMarks;
    private String ObtainedMarks;
    private String weightage;
    private String RegID;
    private String type;
    private IFlexLiteDAO dao;

    public Assessment(String id, String totalMarks, String obtainedMarks, String weightage, String regID, String type) {
        this.id = UUID.randomUUID().toString();
        this.totalMarks = totalMarks;
        this.ObtainedMarks = obtainedMarks;
        this.weightage = weightage;
        this.RegID = regID;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Assessment(IFlexLiteDAO dao) {
        this.dao = dao;
        this.id = UUID.randomUUID().toString();
    }

    public IFlexLiteDAO getDao() {
        return dao;
    }

    public void setDao(IFlexLiteDAO dao) {
        this.dao = dao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(String totalMarks) {
        this.totalMarks = totalMarks;
    }

    public String getObtainedMarks() {
        return ObtainedMarks;
    }

    public void setObtainedMarks(String obtainedMarks) {
        ObtainedMarks = obtainedMarks;
    }

    public String getWeightage() {
        return weightage;
    }

    public void setWeightage(String weightage) {
        this.weightage = weightage;
    }

    public String getRegID() {
        return RegID;
    }

    public void setRegID(String regID) {
        RegID = regID;
    }

    public void save() {
        if (dao != null) {
            Log.d("Data", "Data is Stored");
            Hashtable<String, String> data = new Hashtable<String, String>();
            data.put("id", this.id);
            data.put("obtainedMarks", this.ObtainedMarks);
            data.put("regId", this.RegID);
            data.put("totalMarks", this.totalMarks);
            data.put("type", this.type);
            data.put("weightage", this.weightage);
            dao.save(data);
        }
    }

    public void load(Hashtable<String, String> data) {
        this.id = data.get("id");
        this.totalMarks = data.get("totalMarks");
        this.ObtainedMarks = data.get("obtainedMarks");
        this.weightage = data.get("weightage");
        this.RegID = data.get("regId");
        this.type = data.get("type");

    }

    public static ArrayList<Assessment> load(IFlexLiteDAO dao) {
        ArrayList<Assessment> notes = new ArrayList<Assessment>();
        if (dao != null) {
            ArrayList<Hashtable<String, String>> objects = dao.load();
            for (Hashtable<String, String> obj : objects) {
                Assessment donor = new Assessment(dao);
                donor.load(obj);
                notes.add(donor);
            }
        }
        return notes;
    }
    public static ArrayList<Assessment> load(IFlexLiteDAO dao,String type) {
        ArrayList<Assessment> notes = new ArrayList<Assessment>();
        if (dao != null) {
            ArrayList<Hashtable<String, String>> objects = dao.load();
            for (Hashtable<String, String> obj : objects) {
                Assessment donor = new Assessment(dao);
                donor.load(obj);
                if(donor.getType().equals(type)){
                    notes.add(donor);
                }
            }
        }
        return notes;
    }
}

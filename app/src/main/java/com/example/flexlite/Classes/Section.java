package com.example.flexlite.Classes;

import android.util.Log;

import com.example.flexlite.Firebase.IFlexLiteDAO;

import java.util.ArrayList;
import java.util.Hashtable;

public class Section {
    private String id;
    private String name;
    private String teacherId;
    private String courseId;
    private IFlexLiteDAO dao;

    public Section(String id, String name, String teacher,String courseId) {
        this.id = id;
        this.name = name;
        this.teacherId = teacher;
        this.courseId =courseId;
    }
    public Section(IFlexLiteDAO dao){
        this.dao =dao;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getTeacher() {
        return teacherId;
    }

    public void setTeacher(String teacher) {
        this.teacherId = teacher;
    }

    public void save() {
        if (dao != null) {
            Log.d("Data", "Data is Stored");
            Hashtable<String, String> data = new Hashtable<String, String>();
            data.put("id", this.id);
            data.put("name",this.name);
            data.put("courseId",this.courseId);
            data.put("teacherId",teacherId);
            dao.save(data);
        }
    }

    public void load(Hashtable<String, String> data) {
        this.id = data.get("id");
        this.name = data.get("name");
        this.teacherId =  data.get("teacherId");
        this.courseId = data.get("courseId");
    }

    public static ArrayList<Section> load(IFlexLiteDAO dao, String id) {
        ArrayList<Section> notes = new ArrayList<Section>();
        if (dao != null) {
            ArrayList<Hashtable<String, String>> objects = dao.load();
            for (Hashtable<String, String> obj : objects) {
                Section donor = new Section(dao);
                donor.load(obj);
                if(donor.getCourseId().equals(id)){
                    notes.add(donor);
                }
            }
        }
        return  notes;
    }

    public static Section load(IFlexLiteDAO dao, String id,String secName) {
        ArrayList<Section> notes = new ArrayList<Section>();
        if (dao != null) {
            ArrayList<Hashtable<String, String>> objects = dao.load();
            for (Hashtable<String, String> obj : objects) {
                Section donor = new Section(dao);
                donor.load(obj);
                if(donor.getCourseId().equals(id)){
                    notes.add(donor);
                }
            }
            for(int i=0;i<notes.size();i++){
                if(notes.get(i).getName().equals(secName)){
                    return notes.get(i);
                }
            }
        }
        return  null;
    }

    public static ArrayList<Section> load(IFlexLiteDAO dao) {
        ArrayList<Section> notes = new ArrayList<Section>();
        if (dao != null) {
            ArrayList<Hashtable<String, String>> objects = dao.load();
            for (Hashtable<String, String> obj : objects) {
                Section donor = new Section(dao);
                donor.load(obj);
                    notes.add(donor);
            }
        }
        return  notes;
    }


}

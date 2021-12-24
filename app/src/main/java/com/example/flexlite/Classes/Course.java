package com.example.flexlite.Classes;

import com.example.flexlite.Firebase.IFlexLiteDAO;

import java.util.ArrayList;
import java.util.Hashtable;

public class Course {
    private String id;
    private String CourseCode;
    private String name;
    private String DepId;
    private String semesterNo;
    private String CreditHours;
    private IFlexLiteDAO dao;
    private ArrayList<Section> SectionList;

    public Course(String id, String courseCode, String name, String dep, String semesterNo, String creditHours) {
        this.id = id;
        this.CourseCode = courseCode;
        this.name = name;
        this.DepId = dep;
        this.semesterNo = semesterNo;
        this.CreditHours = creditHours;
        this.SectionList = new ArrayList<Section>();
    }

    public Course(IFlexLiteDAO dao) {
        this.dao = dao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseCode() {
        return CourseCode;
    }

    public void setCourseCode(String courseCode) {
        CourseCode = courseCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDep() {
        return DepId;
    }

    public void setDep(String dep) {
        DepId = dep;
    }

    public String getSemesterNo() {
        return semesterNo;
    }

    public void setSemesterNo(String semesterNo) {
        this.semesterNo = semesterNo;
    }

    public String getCreditHours() {
        return CreditHours;
    }

    public void setCreditHours(String creditHours) {
        CreditHours = creditHours;
    }

    public ArrayList<Section> getSectionList() {
        return SectionList;
    }

    public void setSectionList(ArrayList<Section> sectionList) {
        SectionList = sectionList;
    }

    public void load(Hashtable<String, String> data) {
        this.id = data.get("id");
        this.CourseCode = data.get("courseCode");
        this.name = data.get("name");
        this.DepId = data.get("depId");
        this.semesterNo = data.get("semesterNo");
        this.CreditHours = data.get("creditHours");
    }

    public static ArrayList<Course> load(IFlexLiteDAO dao, String semNo) {
        ArrayList<Course> notes = new ArrayList<Course>();
        if (dao != null) {
            ArrayList<Hashtable<String, String>> objects = dao.load();
            for (Hashtable<String, String> obj : objects) {
                Course donor = new Course(dao);
                donor.load(obj);
                if (donor.getSemesterNo().equals(semNo)) {
                    notes.add(donor);
                }
            }
        }
        return notes;
    }

    public static ArrayList<Course> load(IFlexLiteDAO dao) {
        ArrayList<Course> notes = new ArrayList<Course>();
        if (dao != null) {
            ArrayList<Hashtable<String, String>> objects = dao.load();
            for (Hashtable<String, String> obj : objects) {
                Course donor = new Course(dao);
                donor.load(obj);
                notes.add(donor);

            }
        }
        return notes;
    }

}

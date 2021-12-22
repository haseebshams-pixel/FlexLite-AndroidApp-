package com.example.flexlite.Classes;

import java.util.ArrayList;

public class Course {
    private int id;
    private String CourseCode;
    private String name;
    private Department Dep;
    private int semesterNo;
    private int CreditHours;
    private ArrayList<Section> SectionList;

    public Course(int id, String courseCode, String name, Department dep, int semesterNo, int creditHours) {
        this.id = id;
        CourseCode = courseCode;
        this.name = name;
        Dep = dep;
        this.semesterNo = semesterNo;
        CreditHours = creditHours;
        this.SectionList = new ArrayList<Section>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Department getDep() {
        return Dep;
    }

    public void setDep(Department dep) {
        Dep = dep;
    }

    public int getSemesterNo() {
        return semesterNo;
    }

    public void setSemesterNo(int semesterNo) {
        this.semesterNo = semesterNo;
    }

    public int getCreditHours() {
        return CreditHours;
    }

    public void setCreditHours(int creditHours) {
        CreditHours = creditHours;
    }

    public ArrayList<Section> getSectionList() {
        return SectionList;
    }

    public void setSectionList(ArrayList<Section> sectionList) {
        SectionList = sectionList;
    }
}

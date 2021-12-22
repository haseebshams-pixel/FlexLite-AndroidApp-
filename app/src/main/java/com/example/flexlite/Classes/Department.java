package com.example.flexlite.Classes;

import java.util.ArrayList;

public class Department {
    private int id;
    private String name;
    private boolean isRegOpen;
    private boolean isWithdrawOpen;
    private Admin admin;
    private ArrayList<Student> StudentList;
    private ArrayList<Teacher> TeacherList;
    private ArrayList<Course> CourseList;

    public Department(int id, String name, boolean isRegOpen, boolean isWithdrawOpen, Admin admin) {
        this.id = id;
        this.name = name;
        this.isRegOpen = isRegOpen;
        this.isWithdrawOpen = isWithdrawOpen;
        this.admin = admin;
        this.StudentList = new ArrayList<Student>();
        this.TeacherList = new ArrayList<Teacher>();
        this.CourseList = new ArrayList<Course>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRegOpen() {
        return isRegOpen;
    }

    public void setRegOpen(boolean regOpen) {
        isRegOpen = regOpen;
    }

    public boolean isWithdrawOpen() {
        return isWithdrawOpen;
    }

    public void setWithdrawOpen(boolean withdrawOpen) {
        isWithdrawOpen = withdrawOpen;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public ArrayList<Student> getStudentList() {
        return StudentList;
    }

    public void setStudentList(ArrayList<Student> studentList) {
        StudentList = studentList;
    }

    public ArrayList<Teacher> getTeacherList() {
        return TeacherList;
    }

    public void setTeacherList(ArrayList<Teacher> teacherList) {
        TeacherList = teacherList;
    }

    public ArrayList<Course> getCourseList() {
        return CourseList;
    }

    public void setCourseList(ArrayList<Course> courseList) {
        CourseList = courseList;
    }
}

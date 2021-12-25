package com.example.flexlite.Classes;

import com.example.flexlite.Firebase.IFlexLiteDAO;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Hashtable;

public class Student {
    private String name;
    private String rollno;
    private String batch;
    private String email;
    private String gender;
    private String dob;
    private String address;
    private String cnic;
    private String id;
    private String mobileno;
    private String degree;
    private String semesterNo;
    private String deptId;
    private IFlexLiteDAO dao;

    public Student(String name, String rollno, String batch, String email, String gender, String dob, String address, String cnic, String id, String mobileno, String degree,String semesterNo,String deptId) {
        this.name = name;
        this.rollno = rollno;
        this.batch = batch;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.cnic = cnic;
        this.id = id;
        this.mobileno = mobileno;
        this.degree = degree;
        this.deptId = deptId;
        this.semesterNo = semesterNo;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public Student(IFlexLiteDAO dao){
        this.dao = dao;
    }

    public void setDao(IFlexLiteDAO dao) {
        this.dao = dao;
    }

    public String getSemesterNo() {
        return semesterNo;
    }

    public void setSemesterNo(String semesterNo) {
        this.semesterNo = semesterNo;
    }

    public String getName() {
        return name;
    }

    public String getRollno() {
        return rollno;
    }

    public String getBatch() {
        return batch;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getdob() {
        return dob;
    }

    public String getAddress() {
        return address;
    }

    public String getcnic() {
        return cnic;
    }

    public String getId() {
        return id;
    }

    public String getMobileno() {
        return mobileno;
    }

    public String getDegree() {
        return degree;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setdob(String dob) {
        this.dob = dob;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setcnic(String cnic) {
        this.cnic = cnic;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void load(Hashtable<String, String> data) {
        this.name = data.get("name");
        this.rollno = data.get("rollno");
        this.batch = data.get("batch");
        this.email = data.get("email");
        this.gender = data.get("gender");
        this.dob = data.get("dob");
        this.address = data.get("address");
        this.cnic = data.get("cnic");
        this.id = data.get("id");
        this.mobileno = data.get("mobileno");
        this.degree = data.get("degree");
        this.semesterNo = data.get("semesterNo");
        this.deptId=data.get("deptId");
    }

    public static Student load(IFlexLiteDAO dao,String id) {
        ArrayList<Student> notes = new ArrayList<Student>();
        if (dao != null) {
            ArrayList<Hashtable<String, String>> objects = dao.load();
            for (Hashtable<String, String> obj : objects) {
                Student donor = new Student(dao);
                donor.load(obj);
                notes.add(donor);
            }
        }
        for(int i=0;i<notes.size();i++){
            if(notes.get(i).id.equals(id)){
                return notes.get(i);
            }
        }
        return null;
    }
    public static ArrayList <Student> load(IFlexLiteDAO dao) {
        ArrayList<Student> notes = new ArrayList<Student>();
        if (dao != null) {
            ArrayList<Hashtable<String, String>> objects = dao.load();
            for (Hashtable<String, String> obj : objects) {
                Student donor = new Student(dao);
                donor.load(obj);
                notes.add(donor);
            }
        }
        return notes;
    }

}

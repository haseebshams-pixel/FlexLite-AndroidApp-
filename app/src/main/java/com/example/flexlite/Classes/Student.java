package com.example.flexlite.Classes;

public class Student {
    String name;
    String rollno;
    String batch;
    String email;
    String gender;
    String DOB;
    String address;
    String CNIC;
    int id;
    String mobileno;
    String degree;

    public Student(String name, String rollno, String batch, String email, String gender, String DOB, String address, String CNIC, int id, String mobileno, String degree) {
        this.name = name;
        this.rollno = rollno;
        this.batch = batch;
        this.email = email;
        this.gender = gender;
        this.DOB = DOB;
        this.address = address;
        this.CNIC = CNIC;
        this.id = id;
        this.mobileno = mobileno;
        this.degree = degree;
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

    public String getDOB() {
        return DOB;
    }

    public String getAddress() {
        return address;
    }

    public String getCNIC() {
        return CNIC;
    }

    public int getId() {
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

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}

package com.example.flexlite.Classes;

public class Teacher {
    private String name;
    private int salary;
    private String batch;
    private String email;
    private String gender;
    private String DOB;
    private String address;
    private String CNIC;
    private int id;
    private String mobileno;
    private String degree;

    public Teacher(String name, int salary, String batch, String email, String gender, String DOB, String address, String CNIC, int id, String mobileno, String degree) {
        this.name = name;
        this.salary = salary;
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

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}

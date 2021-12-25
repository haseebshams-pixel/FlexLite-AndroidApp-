package com.example.flexlite.Classes;

import com.example.flexlite.Firebase.IFlexLiteDAO;

import java.util.ArrayList;
import java.util.Hashtable;

public class Teacher {
    private String name;
    private String salary;
    private String email;
    private String gender;
    private String DOB;
    private String address;
    private String CNIC;
    private String id;
    private String mobileno;
    private String degree;
    private String deptId;
    private IFlexLiteDAO dao;

    public Teacher(String name, String salary, String email, String gender, String DOB, String address, String CNIC, String id, String mobileno, String degree,String deptId) {
        this.name = name;
        this.salary = salary;
        this.email = email;
        this.gender = gender;
        this.DOB = DOB;
        this.address = address;
        this.CNIC = CNIC;
        this.id = id;
        this.mobileno = mobileno;
        this.degree = degree;
        this.deptId = deptId;
    }

    public Teacher(IFlexLiteDAO dao){
        this.dao=dao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public IFlexLiteDAO getDao() {
        return dao;
    }

    public void setDao(IFlexLiteDAO dao) {
        this.dao = dao;
    }

    public void load(Hashtable<String, String> data) {
        this.name = data.get("name");
        this.email = data.get("email");
        this.gender = data.get("gender");
        this.DOB = data.get("dob");
        this.address = data.get("address");
        this.CNIC = data.get("cnic");
        this.id = data.get("id");
        this.mobileno = data.get("mobileno");
        this.degree = data.get("degree");
        this.salary = data.get("salary");
        this.deptId=data.get("deptId");
    }

    public static Teacher load(IFlexLiteDAO dao,String id) {
        ArrayList<Teacher> notes = new ArrayList<Teacher>();
        if (dao != null) {
            ArrayList<Hashtable<String, String>> objects = dao.load();
            for (Hashtable<String, String> obj : objects) {
                Teacher donor = new Teacher(dao);
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

}

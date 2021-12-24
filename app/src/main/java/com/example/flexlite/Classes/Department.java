package com.example.flexlite.Classes;

import com.example.flexlite.Firebase.IFlexLiteDAO;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;

public class Department {
    private String id;
    private String name;
    private String isRegOpen;
    private String isWithdrawOpen;
    private String admin;
    private IFlexLiteDAO dao;

    public Department(String name, String isRegOpen, String isWithdrawOpen, String admin) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.isRegOpen = isRegOpen;
        this.isWithdrawOpen = isWithdrawOpen;
        this.admin = admin;
    }

    public Department(IFlexLiteDAO dao) {
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

    public String getIsRegOpen() {
        return isRegOpen;
    }

    public void setIsRegOpen(String isRegOpen) {
        this.isRegOpen = isRegOpen;
    }

    public String getIsWithdrawOpen() {
        return isWithdrawOpen;
    }

    public void setIsWithdrawOpen(String isWithdrawOpen) {
        this.isWithdrawOpen = isWithdrawOpen;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public IFlexLiteDAO getDao() {
        return dao;
    }

    public void setDao(IFlexLiteDAO dao) {
        this.dao = dao;
    }

    public void load(Hashtable<String, String> data) {
        this.id = data.get("id");
        this.admin = data.get("adminid");
        this.isRegOpen = data.get("isRegOpen");
        this.isWithdrawOpen = data.get("isWithdrawOpen");
        this.name = data.get("name");
    }

    public static ArrayList<Department> load(IFlexLiteDAO dao) {
        ArrayList<Department> notes = new ArrayList<Department>();
        if (dao != null) {
            ArrayList<Hashtable<String, String>> objects = dao.load();
            for (Hashtable<String, String> obj : objects) {
                Department donor = new Department(dao);
                donor.load(obj);
                notes.add(donor);
            }
        }
        return notes;
    }
}

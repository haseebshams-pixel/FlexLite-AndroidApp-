package com.example.flexlite.Classes;

public class Section {
    private int id;
    private String name;
    private int stdLimit;
    private int stdReg;
    private Teacher teacher;

    public Section(int id, String name, int stdLimit, int stdReg, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.stdLimit = stdLimit;
        this.stdReg = stdReg;
        this.teacher = teacher;
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

    public int getStdLimit() {
        return stdLimit;
    }

    public void setStdLimit(int stdLimit) {
        this.stdLimit = stdLimit;
    }

    public int getStdReg() {
        return stdReg;
    }

    public void setStdReg(int stdReg) {
        this.stdReg = stdReg;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}

package com.example.flexlite.Classes;

public class Assessment {
    private int id;
    private int totalMarks;
    private float ObtainedMarks;
    private float weightage;
    private Registration RegID;

    public Assessment(int id, int totalMarks, float obtainedMarks, float weightage, Registration regID) {
        this.id = id;
        this.totalMarks = totalMarks;
        ObtainedMarks = obtainedMarks;
        this.weightage = weightage;
        RegID = regID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public float getObtainedMarks() {
        return ObtainedMarks;
    }

    public void setObtainedMarks(float obtainedMarks) {
        ObtainedMarks = obtainedMarks;
    }

    public float getWeightage() {
        return weightage;
    }

    public void setWeightage(float weightage) {
        this.weightage = weightage;
    }

    public Registration getRegID() {
        return RegID;
    }

    public void setRegID(Registration regID) {
        RegID = regID;
    }
}

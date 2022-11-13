package com.example.iiitl_elective_selector_app.AdminPortal;

import android.widget.EditText;

import java.io.Serializable;
import java.util.ArrayList;

public class Elective implements Serializable{
    public ArrayList<String> subjectArrayList;
    public String numberOfSeats;
    public String status;
    public ArrayList<String> facultyArrayList;
    public Elective(){
        subjectArrayList = new ArrayList<>();
        facultyArrayList = new ArrayList<>();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<String> getSubjectArrayList() {
        return subjectArrayList;
    }

    public String getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setSubjectArrayList(ArrayList<String> subjectArrayList) {
        this.subjectArrayList = subjectArrayList;
    }

    public void setNumberOfSeats(String numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public ArrayList<String> getFacultyArrayList() {
        return facultyArrayList;
    }

    public void setFacultyArrayList(ArrayList<String> facultyArrayList) {
        this.facultyArrayList = facultyArrayList;
    }
}

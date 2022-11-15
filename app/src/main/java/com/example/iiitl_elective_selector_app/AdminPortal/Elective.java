package com.example.iiitl_elective_selector_app.AdminPortal;

import android.widget.EditText;

import java.io.Serializable;
import java.util.ArrayList;

public class Elective implements Serializable{
    public ArrayList<String> subjectArrayList;
    public ArrayList<String> seatCountArrayList;
    public String numberOfSeats;
    public String status;
    public ArrayList<String> facultyArrayList;
    public Elective(){
        subjectArrayList = new ArrayList<>();
        facultyArrayList = new ArrayList<>();
        seatCountArrayList = new ArrayList<>();
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<String> getSeatCountArrayList() {
        return seatCountArrayList;
    }

    public void setSeatCountArrayList(ArrayList<String> seatCountArrayList) {
        this.seatCountArrayList = seatCountArrayList;
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

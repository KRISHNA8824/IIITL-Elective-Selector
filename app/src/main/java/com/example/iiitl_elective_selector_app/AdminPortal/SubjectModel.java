package com.example.iiitl_elective_selector_app.AdminPortal;

import java.util.ArrayList;

public class SubjectModel {
    public String subjectName;
    public String facultyName;
    public  String countofSeat;

    public SubjectModel(){

    }

    public SubjectModel(String subjectName, String facultyName, String countofSeat) {
        this.subjectName = subjectName;
        this.facultyName = facultyName;
        this.countofSeat = countofSeat;
    }

    public String getCountofSeat() {
        return countofSeat;
    }

    public void setCountofSeat(String countofSeat) {
        this.countofSeat = countofSeat;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }
}

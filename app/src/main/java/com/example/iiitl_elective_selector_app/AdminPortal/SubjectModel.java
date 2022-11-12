package com.example.iiitl_elective_selector_app.AdminPortal;

import java.util.ArrayList;

public class SubjectModel {
    public String subjectName;
    public String facultyName;

    public SubjectModel(){

    }

    public SubjectModel(String subjectName, String facultyName) {
        this.subjectName = subjectName;
        this.facultyName = facultyName;
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

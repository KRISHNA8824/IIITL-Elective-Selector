package com.example.iiitl_elective_selector_app;

public class StudentModel {
    String studentName;
    String studentEnrolment;

    public StudentModel(String studentName, String studentEnrolment) {
        this.studentName = studentName;
        this.studentEnrolment = studentEnrolment;
    }

    public StudentModel() {
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEnrolment() {
        return studentEnrolment;
    }

    public void setStudentEnrolment(String studentEnrolment) {
        this.studentEnrolment = studentEnrolment;
    }
}

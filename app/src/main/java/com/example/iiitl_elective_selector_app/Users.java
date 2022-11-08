package com.example.iiitl_elective_selector_app;

public class Users {
    String uid;
    String name;
    String email;
    String enrolment;
    String program;
    String year;
    String branch;
    String imageUri;

    public Users() {
    }

    public Users(String uid, String name, String email, String enrolment, String program, String year, String branch) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.enrolment = enrolment;
        this.imageUri = imageUri;
        this.program = program;
        this.year = year;
        this.branch = branch;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEnrolment() {
        return enrolment;
    }
    public void setEnrolment(String enrolment) {
        this.enrolment = enrolment;
    }

    public String getProgram() {
        return program;
    }
    public void setProgram(String program) {
        this.program = program;
    }

    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }

    public String getBranch() {
        return branch;
    }
    public void setBranch(String branch) {
        this.branch = branch;
    }


}

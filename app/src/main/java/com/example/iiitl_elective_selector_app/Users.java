package com.example.iiitl_elective_selector_app;

public class Users {
    String uid;
    String name;
    String email;
    String enrolment;
    String imageUri;
    String status;

    public Users() {
    }

    public Users(String uid, String name, String email, String enrolment) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.enrolment = enrolment;
        this.imageUri = imageUri;
        this.status = status;
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

}

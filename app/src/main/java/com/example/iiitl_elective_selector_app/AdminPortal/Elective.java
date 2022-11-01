package com.example.iiitl_elective_selector_app.AdminPortal;

import java.io.Serializable;
import java.util.ArrayList;

public class Elective implements Serializable{
    public ArrayList<String> subjectArrayList = new ArrayList<>();
    public Elective(){

    }
    public Elective(ArrayList<String> subjectArrayList) {
        this.subjectArrayList = subjectArrayList;
    }

    public ArrayList<String> getSubjectArrayList() {
        return subjectArrayList;
    }

    public void setSubjectArrayList(ArrayList<String> subjectArrayList) {
        this.subjectArrayList = subjectArrayList;
    }
}

package com.example.iiitl_elective_selector_app.AdminPortal;

import java.io.Serializable;

public class DetailsModel implements Serializable {
    String program;
    String year;
    String branch;
    String new_program;

    public DetailsModel(String program, String year, String branch, String new_program) {
        this.program = program;
        this.year = year;
        this.branch = branch;
        this.new_program = new_program;
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

    public String getNew_program() {
        return new_program;
    }

    public void setNew_program(String new_program) {
        this.new_program = new_program;
    }
}

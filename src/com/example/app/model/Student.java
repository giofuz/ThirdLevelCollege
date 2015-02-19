package com.example.app.model;

public class Student {

    private int studentID;
    private String name;
    private String email;
    private String course;
    private String department;


    public Student(int sd, String nm, String em, String cr, String dp) {
        this.studentID = sd;
        this.name = nm;
        this.email = em;
        this.course = cr;
        this.department = dp;
    }
    
    public Student(String nm, String em, String cr, String dp){
        this(-1, nm, em, cr, dp);
        
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


   
}

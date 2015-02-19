package com.example.app.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Model {

    private static Model instance = null;

    public static synchronized Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }
 
    private List<Student> students;
    StudentTableGateway gateway;
    
    private Model() {

        try {
            Connection conn = DBConnection.getInstance();
            this.gateway = new StudentTableGateway(conn);
            
            this.students = this.gateway.getStudents();
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean addStudent(Student st){
        boolean result = false;
        try {
            int id = this.gateway.insertStudent(st.getName(), st.getEmail(), st.getCourse(), st.getDepartment());
            if (id != -1) {
                st.setStudentID(id);
                this.students.add(st);
                result = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public boolean removeStudent(Student st){
        boolean removed = false;
        
        try {
            removed = this.gateway.deleteStudent(st.getStudentID());
            if (removed){
                removed = this.students.remove(st);
            }
        }
        catch (SQLException ex){
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return removed;
    }
    
    public List<Student> getStudents() {
        return this.students;
    
    }

    Student findStudentByStudentID(int studentID) {
        Student st = null;
        int i = 0;
        boolean found =  false;
        while(i < this.students.size() && !found){
            st = this.students.get(i);
            if(st.getStudentID() == studentID){
                found = true;
            } else {
                i++;
            }
        
        }
        if (!found) {
            st = null;
        }
        return st;
    }
    
    boolean updateStudent(Student st){
        boolean updated = false;
        
        try {
            updated = this.gateway.updateStudent(st);
        }
        catch (SQLException ex){
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return updated;
    }
}

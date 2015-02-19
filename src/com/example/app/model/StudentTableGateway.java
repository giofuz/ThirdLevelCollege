package com.example.app.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentTableGateway {
    
    private Connection mConnection;
    
    private static final String TABLE_NAME = "studentsapp";
    private static final String COLUMN_STUDENTID = "studentID";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_COURSE = "course";
    private static final String COLUMN_DEPARTMENT = "department";
  
    public StudentTableGateway(Connection connection) {
        mConnection = connection;   
        
    }
    
    public int insertStudent(String n, String e, String c, String d) throws SQLException{
        String query = null;
        PreparedStatement stmt;
        int numRowsAffected;
        int studentID = -1;
        
        query = "INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_NAME + "," +
                COLUMN_EMAIL + "," +
                COLUMN_COURSE + "," +
                COLUMN_DEPARTMENT +
                ") VALUES (?,?,?,?)";
        
        stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, n);
        stmt.setString(2, e);
        stmt.setString(3, c);
        stmt.setString(4, d);
        
        numRowsAffected = stmt.executeUpdate();
        if(numRowsAffected == 1){
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();
            
            studentID = keys.getInt(1); 
            
        }
        // return the id assigned to the row in the database
        return studentID;
    }
    
    public boolean deleteStudent(int id) throws SQLException {
        String query;
        PreparedStatement stmt;
        int numRowsAffected;
        
        query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_STUDENTID + " = ?";
        
        stmt = mConnection.prepareStatement(query);
        stmt.setInt(1, id);
        
        numRowsAffected = stmt.executeUpdate();
        
        return (numRowsAffected == 1);
    }

    public List<Student> getStudents() throws SQLException {
        String query;
        Statement stmt;
        ResultSet rs;
        List<Student> students;
        
        int studentID;
        String name, email, course, department;
        
        Student p;
        
        query = "SELECT * FROM " + TABLE_NAME;
        stmt = this.mConnection.createStatement();
        rs = stmt.executeQuery(query);
        
        students = new ArrayList<Student>();
        while (rs.next()) {
            studentID = rs.getInt(COLUMN_STUDENTID);
            name= rs.getString(COLUMN_NAME);
            email = rs.getString(COLUMN_EMAIL);
            course = rs.getString(COLUMN_COURSE);
            department = rs.getString(COLUMN_DEPARTMENT);
            
            p = new Student(studentID, name, email, course, department);
            students.add(p);
        }
        return students;
    }
    
    boolean updateStudent(Student st) throws SQLException{
        String query;
        PreparedStatement stmt;
        int numRowsAffected;
        
        query = "UPDATE " + TABLE_NAME + " SET " +
                COLUMN_NAME         + " = ?, " +
                COLUMN_NAME         + " = ?, " +
                COLUMN_NAME         + " = ?, " +
                COLUMN_NAME         + " = ? " +
                " WHERE " + COLUMN_STUDENTID + " = ?";
        
        stmt = mConnection.prepareStatement(query);
        stmt.setString(1, st.getName());
        stmt.setString(2, st.getEmail());
        stmt.setString(3, st.getCourse());
        stmt.setString(4, st.getDepartment());
        stmt.setInt(5, st.getStudentID());
        
        numRowsAffected = stmt.executeUpdate();
        
        return (numRowsAffected == 1);
    }
    
    }
  


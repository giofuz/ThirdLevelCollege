package com.example.app.model;

import java.util.List;
import java.util.Scanner;

public class DemoApp {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        Model model = Model.getInstance();
        int choice;

        //'do' executes the junk of code below until it hits the while loop when 5 is pressed
        do {
            System.out.println("1.View Students");
            System.out.println("2.Add Students");
            System.out.println("3.Delete Students");
            System.out.println("4.Edit Students");
            System.out.println("5.Exit");
            System.out.println("");

            System.out.print("Please choose an option: ");
            choice = Integer.parseInt(keyboard.nextLine());

            //the switch statements control the choice made by he user
            switch (choice) {
                case 1: {
                    System.out.println("Students: ");
                    viewStudents(model);
                    break;
                }
                case 2: {
                    System.out.println("Student to be added");
                    createStudent(keyboard, model);
                    break;
                }
                case 3: {
                    System.out.print("Enter the number of the student id to delete: ");
                    deleteStudent(keyboard, model);
                    break;
                }
                case 4: {
                    editStudent(keyboard, model);
                    break;
                }

            }

        } //when 5 is pressed the program skips the switch statement and jumps into this while loop and the program and the program finishes.
        while (choice != 5);

        System.out.print("Goodbye");
    }

    private static void createStudent(Scanner keyboard, Model model) {
    Student st = readStudent(keyboard);
        if(model.addStudent(st)){
            System.out.println("Programmer added to database");
        }
        else {
            System.out.println("Programmer not added to database");
        }
        System.out.println("");
    
    }
    
    private static void viewStudents(Model model) {
            List<Student>students = model.getStudents();
                    System.out.println();
                    if (students.isEmpty()){
                        System.out.println("There are no students in the database");
                    }
                    else {
                        System.out.printf("%20s %20s %20s %20s %20s\n","studentID", "Name", "Eamil", "Course", "Department");
                        for(Student st : students){
                            System.out.printf("%20s %20s %20s %20s %20s \n",
                                    st.getStudentID(),
                                    st.getName(),
                                    st.getEmail(),
                                    st.getCourse(),
                                    st.getDepartment());
                        }
                    }
                    System.out.println();
    }

    private static Student readStudent(Scanner keyb){
        int studentID;
        String name, email, course, department;
    
        
        name = getString(keyb, "Enter Name: ");
        email = getString(keyb, "Enter Email: ");
        course = getString(keyb, "Enter Course: ");
        department = getString(keyb, "Enter department: ");
        
        Student st =
                new Student (name, email, course, department);
        
        return st;
    }
    
    private static String getString(Scanner keyboard, String prompt){
        System.out.print(prompt);
        return keyboard.nextLine();
    
    }

    private static void deleteStudent(Scanner keyboard, Model model) {
        int studentID = Integer.parseInt(keyboard.nextLine());
            Student st;

            st = model.findStudentByStudentID(studentID);
            if (st != null) {
                if (model.removeStudent(st)){
                    System.out.println("Student deleted");
                }
                else{
                    System.out.println("Student not deleted");
                }
            }
            else{
                System.out.println("Student not found");
            }
    }

    private static void editStudent(Scanner kb, Model m) {
        System.out.print("Enter the Student ID of the student to edit: ");
        int studentID = Integer.parseInt(kb.nextLine());
        Student st;
        
        st = m.findStudentByStudentID(studentID);
        if(st != null){
            editStudentDetails(kb,st);
            if (m.updateStudent(st)){
                System.out.println("Student updated");
            }
            else{
                System.out.print("Student not updated");
            }
        }
        else{
            System.out.println("Student not found");
        }
    }

    private static void editStudentDetails(Scanner kb, Student st) {
      String name, email, course, department;
      
      name = getString(kb, "Enter name [" + st.getName() + "]: ");
      email = getString(kb, "Enter email [" + st.getEmail() + "]: ");
      course = getString(kb, "Enter course [" + st.getCourse() + "]: ");
      department = getString(kb, "Enter department [" + st.getDepartment() + "]: ");
      
      if(name.length() != 0){
          st.setName(name);
      }
      if(email.length() != 0){
          st.setEmail(email);
      }
      if(course.length() != 0){
          st.setCourse(course);
      }
      if(department.length() != 0){
          st.setDepartment(department);
      }
      
    }
}


//http://avaya/PHPmyadmin/ link to database
//https://vimeo.com/116547367
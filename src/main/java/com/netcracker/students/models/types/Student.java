package com.netcracker.students.models.types;

import java.util.Scanner;

public class Student {
    private String firstname;
    private String lastname;
    private String middlename;
    private int age;
    private String faculty;
    private int course;
    private int student_id;

    public Student(int student_id, String lastname, String firstname, String middlename, int age, String faculty, int course) {
        this.student_id = student_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.age = age;
        this.faculty = faculty;
        this.course = course;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getStudent_id() {
        return student_id;
    }


}

package com.netcracker.students.controllers;

import com.netcracker.students.models.types.StudentList;
import com.netcracker.students.utils.Student_db;

import java.util.Scanner;

public class StudentsListManager {
    private String firstname;
    private String lastname;
    private String middlename;
    private int age;
    private String faculty;
    private int course;
    private int choosenStudent;
    private int option;
    Scanner scanner = new Scanner(System.in);
    Student_db student_db = new Student_db();
    StudentList studentList = new StudentList();
    DataVerify dataVerify = new DataVerify();

    public void choosenOption(StudentList students){
        option=dataVerify.intVerify();
        switch (option){
            case 1:addData();
                break;
            case 2:updateData(students);
                break;
            case 3:printStudentsData();
                break;
            case 4:delStudent(students);
                break;
            case 5:System.exit(0);
                break;
            default:
                System.out.println("Введите цифру из меню!!\n");
                break;
        }
    }

    public void studentData(){
        //scanner.nextLine();
        System.out.println("Enter the lastname: ");
        lastname = dataVerify.stringVerify(scanner);

        System.out.println("Enter the firstname: ");
        firstname = dataVerify.stringVerify(scanner);

        System.out.println("Enter the middlename: ");
        middlename = dataVerify.stringVerify(scanner);

        System.out.println("Enter the faculty: ");
        faculty = dataVerify.stringVerify(scanner);

        System.out.println("Enter the age: ");
        age = dataVerify.ageVerify();

        while (true){
            System.out.println("Enter the course: ");
            course = scanner.nextInt();
            if(course > 0 && course < 9)
                break;
        }
    }
    public void addData(){
        studentData();
        student_db.connectToDB();
        student_db.insertData(lastname,firstname,middlename,age,faculty,course);
    }

    public void updateData(StudentList studentList){
        student_db.connectToDB();
        if (student_db.getDataCount()>0){
            System.out.println("Выберите студента");
            choosenStudent = dataVerify.choosenStudentVerify(student_db);
            studentData();
            //studentList.updateStudent(choosenStudent,lastname,firstname,middlename,age,faculty,course);
            student_db.updateData(choosenStudent,lastname,firstname,middlename,age,faculty,course);
        }else {
            System.out.println("Db is empty");
        }
    }

    public void delStudent(StudentList studentList){
        student_db.connectToDB();
        if (student_db.getDataCount()>0){
            System.out.println("Выберите студента");
            choosenStudent = dataVerify.choosenStudentVerify(student_db);
            //studentList.deleteStudent(choosenStudent);
            student_db.deleteData(choosenStudent);
        }else {
            System.out.println("Db is empty");
        }
    }

    public void printStudentsData(){
        student_db.connectToDB();
        if (student_db.getDataCount()>0){
            student_db.printData();
        }else {
            System.out.println("Db is empty");
        }
    }


}

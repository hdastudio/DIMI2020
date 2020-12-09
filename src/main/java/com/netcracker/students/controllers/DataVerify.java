package com.netcracker.students.controllers;

import com.netcracker.students.utils.Student_db;

import java.util.Scanner;

public class DataVerify {
    private int choosenStudent;
    private int age;
    private int intValueVerify;
    private String strForVerify;
    Scanner scanner = new Scanner(System.in);

    public  String stringVerify(Scanner scanner){

        strForVerify = scanner.nextLine();
        if(strForVerify.isEmpty()){
            System.out.println("Поле пусто");
            stringVerify(scanner);
        }
        if(strForVerify.matches(".*[0-9].*")||strForVerify.matches(".*\\W.*")){
            System.out.println("Недопустимое значение!! (Ожидалась буква)");
            stringVerify(scanner);
        }
        return strForVerify;
    }

    public int intVerify(){

        while (!scanner.hasNextInt()){
            System.out.println("Введите число!");
            scanner.next();
        }
        intValueVerify = scanner.nextInt();
        return intValueVerify;
    }
    public int ageVerify(){
        age = intVerify();
        if(age < 16 || age > 85){
            System.out.println("Недопустимый востраст \n Повторите ввод:");
            ageVerify();
        }
        return age;
    }
    public int choosenStudentVerify(Student_db student_db){
        choosenStudent = intVerify();
        if(!student_db.findID(choosenStudent)){
            System.out.println("Студента с таким ID не существует!!\nПовторите ввод:");
            choosenStudentVerify(student_db);
        }
        return choosenStudent;
    }
}

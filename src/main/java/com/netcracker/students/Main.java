package com.netcracker.students;

import com.netcracker.students.utils.Student_db;
import com.netcracker.students.views.Console;

public class Main {
    public static void main(String[] args){
        /*Student_db student_db = new Student_db();
        student_db.connectToDB();
        student_db.createTable();*/
        Console console = new Console();
        console.menu();
    }
}

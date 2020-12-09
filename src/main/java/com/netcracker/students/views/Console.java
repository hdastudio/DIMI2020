package com.netcracker.students.views;

import com.netcracker.students.controllers.StudentsListManager;
import com.netcracker.students.models.types.StudentList;

public class Console {
    StudentList studentList = new StudentList();
    StudentsListManager studentsListManager = new StudentsListManager();


    public void menu(){
        while(true){
            System.out.println("Меню:");
            System.out.println("1 Добавить студент\n" +
                    "2 Редактировать \n" +
                    "3 Отобразить \n" +
                    "4 Удалить \n" +
                    "5 Выход");
            System.out.println("Выберите опцию");
            studentsListManager.choosenOption(studentList);
        }

    }


}

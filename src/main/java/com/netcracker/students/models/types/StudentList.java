package com.netcracker.students.models.types;


import java.util.ArrayList;

public class StudentList {

    private static int STUDENT_ID = 0;
    public ArrayList<Student> students = new ArrayList<>();

    public void addStudent(String lastname, String firstname, String middlename, int age, String faculty, int course) {
        students.add(new Student(idGenerator(), lastname, firstname, middlename, age, faculty, course));
        System.out.println("Студент добавлен!");
    }

    public void updateStudent(int choosenStudent, String lastname, String firstname, String middlename, int age, String faculty, int course) {
        students.get(choosenStudent).setLastname(lastname);
        students.get(choosenStudent).setFirstname(firstname);
        students.get(choosenStudent).setMiddlename(middlename);
        students.get(choosenStudent).setFaculty(faculty);
        students.get(choosenStudent).setAge(age);
        students.get(choosenStudent).setCourse(course);
        System.out.println("Данные обновлены!");
    }

    public void deleteStudent(int student_id) {
        System.out.println("Студент с ID = " + students.get(student_id).getStudent_id() + " удален");
        students.remove(student_id);
    }

    public static int idGenerator() {
        return STUDENT_ID++;
    }

    public void printList() {
        for (Student student : students) {
            System.out.println(student);
            System.out.println("-----------------------------");
        }
    }

    public boolean isListNotEmpty() {
        if (students.isEmpty()) {
            System.out.println("Список пуст!!!\n");
            return false;
        }
        return true;
    }

}

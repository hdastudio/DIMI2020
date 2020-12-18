package com.company;

import Controller.DishService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        DishService ds = new DishService();
        Scanner scan = new Scanner(System.in);
        int x = -1;

        while (x != 5) {
            System.out.println("1 - Добавить");
            System.out.println("2 - Редактировать");
            System.out.println("3 - Отобразить");
            System.out.println("4 - Удалить");
            System.out.println("5 - Выход");
            x = Integer.parseInt(scan.next());

            switch (x) {
                case 1:
                    ds.addDish();
                    break;
                case 2:
                    ds.editDish();
                    break;
                case 3:
                    ds.displayDish();
                    break;
                case 4:
                    ds.removeDish();
                    break;
                case 5:
                    System.out.println("Sayonara");
                    break;
            }
        }
    }
}

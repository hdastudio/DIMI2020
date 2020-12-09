package com.netcracker.purchases.views;

import com.netcracker.purchases.models.services.PurchaseManager;
import com.netcracker.purchases.models.types.Purchase;
import com.netcracker.purchases.utils.CsvService;
import com.netcracker.purchases.utils.DataVerification;
import com.netcracker.purchases.utils.OracleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private static final String PURCHASE = "покупк";
    private static final Scanner in = new Scanner(System.in);
    private static final List<Purchase> purchases = new ArrayList<>();
    private static final boolean JdbcIsInstalled = OracleService.isJdbcInstalled();

    public static void start() {
        dataLoad();

        while (true) {
            System.out.printf("Меню:\n1. Добавить %sу\n2. Редактировать %sу\n3. Отобразить %sи\n" +
                    "4. Сохрнаить %sи\n5. Удалить %sу\n6. Выход\n\n", PURCHASE, PURCHASE, PURCHASE, PURCHASE, PURCHASE);

            System.out.println("Выберите операцию: ");
            String strNum = in.nextLine();
            System.out.println();

            int num;
            if (DataVerification.isDigit(strNum)) {
                num = Integer.parseInt(strNum);
            } else {
                System.out.println("Ошибка! Введено некоректное значение, повторите ввод.\n");
                continue;
            }

            switch (num) {
                case 1:
                    addPurchase();
                    System.out.printf("Добавление %sи успешно завершено!\n\n", PURCHASE);
                    break;
                case 2:
                    if (purchases.size() == 0) {
                        System.out.printf("Список с %sами пуст!\n\n", PURCHASE);
                        break;
                    }
                    showPurchase();
                    editPurchase();
                    System.out.printf("Редактирование %sи успешно завершено!\n\n", PURCHASE);
                    break;
                case 3:
                    if (purchases.size() == 0) {
                        System.out.printf("Список с %sами пуст!\n\n", PURCHASE);
                        break;
                    }
                    showPurchase();
                    System.out.printf("Список с %sами успешно отображён!\n\n", PURCHASE);
                    break;
                case 4:
                    if (purchases.size() == 0) {
                        System.out.printf("Список с %sами пуст!\n\n", PURCHASE);
                        break;
                    }
                    dataSave();
                    break;
                case 5:
                    if (purchases.size() == 0) {
                        System.out.printf("Список с %sами пуст!\n\n", PURCHASE);
                        break;
                    }
                    delPurchase();
                    System.out.printf("Удаление %sи успешно завершено!\n\n", PURCHASE);
                    break;
                case 6:
                    if (purchases.size() != 0) {
                        closeApp();
                    }
                    System.out.println("Работа программы завершена!");
                    return;
                default:
                    System.out.println("Ошибка! Введено некоректное значение, повторите ввод.\n");
            }
        }
    }

    private static void delPurchase() {
        String format = "%-20s%-50s";
        System.out.printf(format, "Номер", "Название");
        System.out.println();
        for (Purchase purchase : purchases) {
            System.out.printf(format, purchase.getIdLocal(), purchase.getName());
            System.out.println();
        }
        System.out.println();

        while (true) {
            System.out.println("Введите номер удаляемой покупки:");
            String strNum = in.nextLine();

            if (PurchaseManager.del(strNum, purchases)) {
                break;
            } else {
                System.out.println("Ошибка! Введено некоректное значение, повторите ввод.\n");
            }
        }
    }

    private static void showPurchase() {
        String format = "%-15s%-50s%-20s%-25s%-15s";
        System.out.printf(format, "Номер", "Название", "Количество", "Еденицы измерения", "Комментарий");
        System.out.println();
        for (Purchase purchase : purchases) {
            System.out.printf(format, purchase.getIdLocal(), purchase.getName(),
                    purchase.getCount(), purchase.getUnit(), purchase.getComment());
            System.out.println();
        }
        System.out.println();
    }

    private static void addPurchase() {
        StringBuilder[] propPurchase = new StringBuilder[4];
        dataInput(propPurchase);
        PurchaseManager.add(purchases, propPurchase);
    }

    private static void editPurchase() {
        int num;

        while (true) {
            System.out.println("Введите номер редактируемой покупки:");
            String strNum = in.nextLine();

            if (DataVerification.isDigit(strNum) && (Integer.parseInt(strNum) >= 0) && (Integer.parseInt(strNum) < purchases.size())) {
                num = Integer.parseInt(strNum);
                break;
            } else {
                System.out.println("Ошибка! Введено некоректное значение, повторите ввод.\n");
            }
        }

        StringBuilder[] propPurchase = new StringBuilder[4];
        dataInput(propPurchase);
        Purchase purchase = purchases.get(num);
        PurchaseManager.edit(purchase, propPurchase);
    }

    private static void dataInput(StringBuilder[] propPurchase) {
        while (true) {
            System.out.println("Введите название покупки: ");
            propPurchase[0] = new StringBuilder(in.nextLine());
            if (PurchaseManager.checkEmpty(propPurchase[0].toString())) {
                break;
            } else {
                System.out.println("Ошибка! Не введено название покупки, повторите ввод.\n");
            }
        }

        while (true) {
            System.out.println("Введите количество товара: ");
            propPurchase[1] = new StringBuilder(in.nextLine());
            if (PurchaseManager.checkCount(propPurchase[1].toString())) {
                break;
            } else {
                System.out.println("Ошибка! Введено некоректное значение, повторите ввод.\n");
            }
        }

        while (true) {
            System.out.println("Введите еденицы измерения количества товара: ");
            propPurchase[2] = new StringBuilder(in.nextLine());
            if (PurchaseManager.checkEmpty(propPurchase[2].toString())) {
                break;
            } else {
                System.out.println("Ошибка! Не введены еденицы измерения количества товара, повторите ввод.\n");
            }
        }

        System.out.println("Введите комментарий к покупке: ");
        propPurchase[3] = new StringBuilder(in.nextLine());
        PurchaseManager.changeComment(propPurchase[3]);
    }

    private static boolean csvOrDb(String msg) {
        if (!JdbcIsInstalled) {
            return true;
        }

        while (true) {
            System.out.println(msg);
            String ans = in.nextLine();
            if (ans.toLowerCase().equals("ф")) {
                return true;
            } else if (ans.toLowerCase().equals("б")) {
                return false;
            } else {
                System.out.println("Ошибка! Введено некоректное значение, повторите ввод.\n");
            }
        }
    }

    private static void closeApp() {
        while (true) {
            System.out.println("Желаете сохранить данные?(Д/Н)");
            String ans = in.nextLine();
            if (ans.toLowerCase().equals("д")) {
                dataSave();
                break;
            } else if (ans.toLowerCase().equals("н")) {
                break;
            } else {
                System.out.println("Ошибка! Введено некоректное значение, повторите ввод.\n");
            }
        }
    }

    private static void dataLoad() {
        if (!JdbcIsInstalled) {
            System.out.println("Oracle JDBC не установлен! Работа с базой данных не возможна.\n");
        }

        if (csvOrDb("Желаете загрузить данные из файла или из базы данных?(Ф/Б)")) {
            if (CsvService.load(purchases)) {
                System.out.println("Список покупок успешно загружен из файла!\n");
            } else {
                System.out.println("Файл со списком покупок не найден или повреждён!\n");
            }
        } else {
            if (OracleService.load(purchases)) {
                System.out.println("Список покупок успешно загружен из базы данных!\n");
            } else {
                System.out.println("Не удалось установить соединение с базой данных!\n");
            }
        }
    }

    private static void dataSave() {
        if (csvOrDb("Желаете сохранить данные в файл или в базу данных?(Ф/Б)")) {
            if (CsvService.save(purchases)) {
                System.out.printf("Список с %sами успешно сохранён в файл!\n\n", PURCHASE);
            } else {
                System.out.println("Непредвиденная ошибка при записи списка покупок в файл!\n");
            }
        } else {
            if (OracleService.save(purchases)) {
                System.out.printf("Список с %sами успешно сохранён в базу данных!\n\n", PURCHASE);
            } else {
                System.out.println("Непредвиденная ошибка при записи списка покупок в базу данных!\n");
            }
        }
    }
}

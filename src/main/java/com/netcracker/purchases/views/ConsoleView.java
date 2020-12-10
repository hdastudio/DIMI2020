package com.netcracker.purchases.views;

import com.netcracker.purchases.models.services.PurchaseManager;
import com.netcracker.purchases.models.types.Purchase;
import com.netcracker.purchases.utils.CsvService;
import com.netcracker.purchases.utils.DataVerification;
import com.netcracker.purchases.utils.OracleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class ConsoleView {
    private static final String PURCHASE = "покупк";
    private static final Scanner IN = new Scanner(System.in);
    private static final List<Purchase> PURCHASES = new ArrayList<>();
    private static final boolean IS_JDBC_INSTALLED = OracleService.isJdbcInstalled();
    private static final String MSG_INPUT_ERR = "Ошибка! Введено некоректное значение, повторите ввод.\n";

    private ConsoleView() {
    }

    public static void start() {
        dataLoad();

        int num = 0;
        while (num != 6) {
            System.out.printf("Меню:\n1. Добавить %sу\n2. Редактировать %sу\n3. Отобразить %sи\n" +
                    "4. Сохрнаить %sи\n5. Удалить %sу\n6. Выход\n\n", PURCHASE, PURCHASE, PURCHASE, PURCHASE, PURCHASE);

            System.out.println("Выберите операцию: ");
            String strNum = IN.nextLine();
            System.out.println();

            if (DataVerification.isDigit(strNum)) {
                num = Integer.parseInt(strNum);
            } else {
                System.out.println(MSG_INPUT_ERR);
                continue;
            }

            if ((PURCHASES.size() == 0) && (num != 1) && (num != 6)) {
                System.out.printf("Список с %sами пуст!\n\n", PURCHASE);
                continue;
            }

            selectOperation(num);
        }
    }

    private static void selectOperation(int num) {
        switch (num) {
            case 1:
                addPurchase();
                System.out.printf("Добавление %sи успешно завершено!\n\n", PURCHASE);
                break;
            case 2:
                showPurchase();
                editPurchase();
                System.out.printf("Редактирование %sи успешно завершено!\n\n", PURCHASE);
                break;
            case 3:
                showPurchase();
                System.out.printf("Список с %sами успешно отображён!\n\n", PURCHASE);
                break;
            case 4:
                dataSave();
                break;
            case 5:
                showPurchase();
                delPurchase();
                System.out.printf("Удаление %sи успешно завершено!\n\n", PURCHASE);
                break;
            case 6:
                closeApp();
                System.out.println("Работа программы завершена!");
                break;
            default:
                System.out.println(MSG_INPUT_ERR);
                break;
        }
    }

    private static void delPurchase() {
        while (true) {
            System.out.println("Введите номер удаляемой покупки:");
            String strNum = IN.nextLine();

            if (PurchaseManager.isNumberPurchases(strNum, PURCHASES)) {
                PurchaseManager.del(strNum, PURCHASES);
                break;
            } else {
                System.out.println(MSG_INPUT_ERR);
            }
        }
    }

    private static void showPurchase() {
        String format = "%-15s%-15s%-50s%-20s%-25s%-15s";
        System.out.printf(format, "Номер", "ID", "Название", "Количество", "Еденицы измерения", "Комментарий");
        System.out.println();

        int i = 0;
        for (Purchase purchase : PURCHASES) {
            System.out.printf(format, i, purchase.getIdLocal(), purchase.getName(),
                    purchase.getCount(), purchase.getUnit(), purchase.getComment());
            System.out.println();
            i++;
        }
        System.out.println();
    }

    private static void addPurchase() {
        StringBuilder[] propPurchase = new StringBuilder[4];
        dataInput(propPurchase);
        PurchaseManager.add(PURCHASES, propPurchase);
    }

    private static void editPurchase() {
        int num;
        while (true) {
            System.out.println("Введите номер редактируемой покупки:");
            String strNum = IN.nextLine();

            if (PurchaseManager.isNumberPurchases(strNum, PURCHASES)) {
                num = Integer.parseInt(strNum);
                break;
            } else {
                System.out.println(MSG_INPUT_ERR);
            }
        }

        StringBuilder[] propPurchase = new StringBuilder[4];
        dataInput(propPurchase);
        Purchase purchase = PURCHASES.get(num);
        PurchaseManager.edit(purchase, propPurchase);
    }

    private static void dataInput(StringBuilder[] propPurchase) {
        while (true) {
            System.out.println("Введите название покупки: ");
            propPurchase[0] = new StringBuilder(IN.nextLine());
            if (PurchaseManager.checkEmpty(propPurchase[0].toString())) {
                break;
            } else {
                System.out.println(MSG_INPUT_ERR);
            }
        }

        while (true) {
            System.out.println("Введите количество товара: ");
            propPurchase[1] = new StringBuilder(IN.nextLine());
            if (PurchaseManager.checkCount(propPurchase[1].toString())) {
                break;
            } else {
                System.out.println(MSG_INPUT_ERR);
            }
        }

        while (true) {
            System.out.println("Введите еденицы измерения количества товара: ");
            propPurchase[2] = new StringBuilder(IN.nextLine());
            if (PurchaseManager.checkEmpty(propPurchase[2].toString())) {
                break;
            } else {
                System.out.println(MSG_INPUT_ERR);
            }
        }

        System.out.println("Введите комментарий к покупке: ");
        propPurchase[3] = new StringBuilder(IN.nextLine());
        PurchaseManager.changeComment(propPurchase[3]);
    }

    private static boolean csvOrDb(String msg) {
        if (!IS_JDBC_INSTALLED) {
            return true;
        }

        while (true) {
            System.out.println(msg);
            String ans = IN.nextLine().toLowerCase();
            if (ans.equals("ф")) {
                return true;
            } else if (ans.equals("б")) {
                return false;
            } else {
                System.out.println(MSG_INPUT_ERR);
            }
        }
    }

    private static void closeApp() {
        while (true) {
            System.out.println("Желаете сохранить данные?(Д/Н)");
            String ans = IN.nextLine().toLowerCase();
            if (ans.equals("д")) {
                dataSave();
                break;
            } else if (ans.equals("н")) {
                break;
            } else {
                System.out.println(MSG_INPUT_ERR);
            }
        }
    }

    private static void dataLoad() {
        if (!IS_JDBC_INSTALLED) {
            System.out.println("Oracle JDBC не установлен! Работа с базой данных не возможна.\n");
        }

        if (csvOrDb("Желаете загрузить данные из файла или из базы данных?(Ф/Б)")) {
            if (CsvService.load(PURCHASES)) {
                System.out.println("Список покупок успешно загружен из файла!\n");
            } else {
                System.out.println("Файл со списком покупок не найден или повреждён!\n");
            }
        } else {
            if (OracleService.load(PURCHASES)) {
                System.out.println("Список покупок успешно загружен из базы данных!\n");
            } else {
                System.out.println("Не удалось установить соединение с базой данных!\n");
            }
        }
    }

    private static void dataSave() {
        if (csvOrDb("Желаете сохранить данные в файл или в базу данных?(Ф/Б)")) {
            if (CsvService.save(PURCHASES)) {
                System.out.printf("Список с %sами успешно сохранён в файл!\n\n", PURCHASE);
            } else {
                System.out.println("Непредвиденная ошибка при записи списка покупок в файл!\n");
            }
        } else {
            if (OracleService.save(PURCHASES)) {
                System.out.printf("Список с %sами успешно сохранён в базу данных!\n\n", PURCHASE);
            } else {
                System.out.println("Непредвиденная ошибка при записи списка покупок в базу данных!\n");
            }
        }
    }
}

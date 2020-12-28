package com.netcracker.purchases.views;

import com.netcracker.purchases.models.services.PurchaseManager;
import com.netcracker.purchases.models.types.Purchase;
import com.netcracker.purchases.utils.CsvService;
import com.netcracker.purchases.utils.DataVerification;
import com.netcracker.purchases.utils.OracleService;

import java.util.Scanner;

import static com.netcracker.purchases.utils.CommonData.*;

public final class ConsoleView {
    private static final Scanner IN = new Scanner(System.in);

    private ConsoleView() {
    }

    public static void start() {
        dataLoad();

        int num = 0;
        while (num != 6) {
            System.out.println(MSG_MENU);
            System.out.println(MSG_ADD);
            System.out.println(MSG_EDIT);
            System.out.println(MSG_SHOW);
            System.out.println(MSG_SAVE);
            System.out.println(MSG_DEL);
            System.out.println(MSG_EXIT);
            System.out.println("Выберите операцию: ");

            String strNum = IN.nextLine();
            System.out.println();

            if (DataVerification.isDigit(strNum)) {
                num = Integer.parseInt(strNum);
            } else {
                System.out.println(MSG_INPUT_ERR_VALUE);
                continue;
            }

            if ((PURCHASES.size() == 0) && (num != 1) && (num != 6)) {
                System.out.println(MSG_LIST_EMPTY_VALUE);
                continue;
            }

            selectOperation(num);
        }
    }

    private static void selectOperation(int num) {
        switch (num) {
            case 1:
                addPurchase();
                System.out.println(MSG_ADD_SUCCESS);
                break;
            case 2:
                showPurchase();
                editPurchase();
                System.out.println(MSG_EDIT_SUCCESS);
                break;
            case 3:
                showPurchase();
                System.out.println(MSG_SHOW_SUCCESS);
                break;
            case 4:
                dataSave();
                break;
            case 5:
                showPurchase();
                delPurchase();
                System.out.println(MSG_DEL_SUCCESS);
                break;
            case 6:
                closeApp();
                System.out.println(MSG_APP_CLOSE);
                break;
            default:
                System.out.println(MSG_INPUT_ERR_VALUE);
                break;
        }
    }

    private static void delPurchase() {
        while (true) {
            System.out.println(MSG_INPUT_NUM_DEL);
            String strNum = IN.nextLine();

            if (PurchaseManager.isNumberPurchases(strNum, PURCHASES)) {
                PurchaseManager.del(strNum, PURCHASES);
                break;
            } else {
                System.out.println(MSG_INPUT_ERR_VALUE);
            }
        }
    }

    private static void showPurchase() {
        String format = "%-15s%-15s%-50s%-20s%-25s%-15s";
        System.out.printf(format, MSG_NUM, MSG_ID, MSG_NAME, MSG_COUNT, MSG_UNIT, MSG_COMMENT);
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
            System.out.println(MSG_INPUT_NUM_EDIT);
            String strNum = IN.nextLine();

            if (PurchaseManager.isNumberPurchases(strNum, PURCHASES)) {
                num = Integer.parseInt(strNum);
                break;
            } else {
                System.out.println(MSG_INPUT_ERR_VALUE);
            }
        }

        StringBuilder[] propPurchase = new StringBuilder[4];
        dataInput(propPurchase);
        Purchase purchase = PURCHASES.get(num);
        PurchaseManager.edit(purchase, propPurchase);
    }

    private static void dataInput(StringBuilder[] propPurchase) {
        while (true) {
            System.out.println(MSG_INPUT_PURCHASE_NAME);
            propPurchase[0] = new StringBuilder(IN.nextLine());
            if (PurchaseManager.checkEmpty(propPurchase[0].toString())) {
                break;
            } else {
                System.out.println(MSG_INPUT_ERR_VALUE);
            }
        }

        while (true) {
            System.out.println(MSG_INPUT_PURCHASE_COUNT);
            propPurchase[1] = new StringBuilder(IN.nextLine());
            if (PurchaseManager.checkCount(propPurchase[1].toString())) {
                break;
            } else {
                System.out.println(MSG_INPUT_ERR_VALUE);
            }
        }

        while (true) {
            System.out.println(MSG_INPUT_PURCHASE_UNIT);
            propPurchase[2] = new StringBuilder(IN.nextLine());
            if (PurchaseManager.checkEmpty(propPurchase[2].toString())) {
                break;
            } else {
                System.out.println(MSG_INPUT_ERR_VALUE);
            }
        }

        System.out.println(MSG_INPUT_PURCHASE_COMMENT);
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
                System.out.println(MSG_INPUT_ERR_VALUE);
            }
        }
    }

    private static void closeApp() {
        while (true) {
            System.out.println(MSG_DATA_SAVE);
            String ans = IN.nextLine().toLowerCase();
            if (ans.equals("д")) {
                dataSave();
                break;
            } else if (ans.equals("н")) {
                break;
            } else {
                System.out.println(MSG_INPUT_ERR_VALUE);
            }
        }
    }

    private static void dataLoad() {
        if (!IS_JDBC_INSTALLED) {
            System.out.println(MSG_JDBC_NOT_FOUND);
        }

        boolean ans = csvOrDb(MSG_SELECT_DATA_LOAD) ? CsvService.load(PURCHASES) : OracleService.load(PURCHASES);
        if (ans) {
            System.out.println(MSG_LIST_LOAD_SUCCESS);
        } else {
            System.out.println(MSG_LIST_LOAD_ERR);
        }
    }

    private static void dataSave() {
        boolean ans = csvOrDb(MSG_SELECT_DATA_SAVE) ? CsvService.save(PURCHASES) : OracleService.save(PURCHASES);
        if (ans) {
            System.out.println(MSG_LIST_SAVE_SUCCESS);
        } else {
            System.out.println(MSG_LIST_SAVE_ERR);
        }
    }
}

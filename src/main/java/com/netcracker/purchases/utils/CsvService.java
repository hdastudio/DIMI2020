package com.netcracker.purchases.utils;

import com.netcracker.purchases.models.services.PurchaseManager;
import com.netcracker.purchases.models.types.Purchase;

import java.io.*;
import java.util.List;

public final class CsvService {
    private static final String encoding = "windows-1251";
    private static final String FILE_NAME_PURCHASES = "Purchases.csv";

    private CsvService() {
    }

    public static boolean load(List<Purchase> purchases) {
        try (BufferedReader filePurchases = new BufferedReader(new InputStreamReader(
                new FileInputStream(FILE_NAME_PURCHASES), encoding))) {
            String tempStr;
            filePurchases.readLine();
            while ((tempStr = filePurchases.readLine()) != null) {
                String[] tempArr = tempStr.split(";");
                purchases.add(new Purchase(tempArr[1], Integer.parseInt(tempArr[2]), tempArr[3], tempArr[4]));
            }
            return true;
        } catch (Exception ex) {
            PurchaseManager.cleanArray(purchases);
            return false;
        }
    }

    public static boolean save(List<Purchase> purchases) {
        try (Writer filePurchases = new OutputStreamWriter(
                new FileOutputStream(FILE_NAME_PURCHASES), encoding)) {
            filePurchases.write("Номер;Название;Количество;Еденицы измерения;Комментарий\n");
            for (Purchase purchase : purchases) {
                String outputStr = String.format("%s;%s;%s;%s;%s\n", purchase.getIdLocal(), purchase.getName(),
                        purchase.getCount(), purchase.getUnit(), purchase.getComment());
                filePurchases.write(outputStr);
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
package com.netcracker.purchases.models.services;

import com.netcracker.purchases.models.types.Purchase;
import com.netcracker.purchases.utils.DataVerification;

import java.util.List;

public class PurchaseManager {
    private PurchaseManager() {
    }

    public static void add(List<Purchase> purchases, StringBuilder[] propPurchase) {
        purchases.add(new Purchase(propPurchase[0].toString(), Integer.parseInt(propPurchase[1].toString()),
                propPurchase[2].toString(), propPurchase[3].toString()));
    }

    public static void edit(Purchase purchase, StringBuilder[] propPurchase) {
        purchase.setName(propPurchase[0].toString());
        purchase.setCount(Integer.parseInt(propPurchase[1].toString()));
        purchase.setUnit(propPurchase[2].toString());
        purchase.setComment(propPurchase[3].toString());
    }

    public static boolean del(String strNum, List<Purchase> purchases) {
        if (DataVerification.isDigit(strNum) && (Integer.parseInt(strNum) > 0) && (Integer.parseInt(strNum) <= purchases.size())) {
            purchases.remove(Integer.parseInt(strNum));
            return true;
        } else {
            return false;
        }

    }

    public static void cleanArray(List<Purchase> purchases) {
        while (purchases.size() != 0) {
            purchases.remove(0);
        }
    }

    public static boolean checkEmpty(String str) {
        return !str.isEmpty();
    }

    public static boolean checkCount(String count){
        return DataVerification.isDigit(count) && (Integer.parseInt(count) > 0);
    }

    public static void changeComment(StringBuilder comment){
        if (comment.toString().isEmpty()) {
            comment.append(" ");
        }
    }
}

package com.netcracker.purchases.utils;

public final class DataVerification {
    private DataVerification() {
    }

    public static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

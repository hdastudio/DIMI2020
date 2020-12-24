package com.netcracker.controllers.helpfun;

public class CheckNumber {
    private String strNumber;
    private int number  = -1;
    private boolean correct = false;
    public CheckNumber(String strNumber)
    {
        this.strNumber = strNumber;

        if (isNumeric() )
        {
            if (assignIntYear())
                correct = true;
            else correct = false;
        } else  correct = false;
    }

    public boolean getCorrect()
    {
        return correct;
    }
    public int getnumber()
    {
        return number;
    }

    public  boolean isNumeric() {
        if (strNumber.length() > 0) {
            int size = strNumber.length();
            char firstChar = strNumber.charAt(0);

            if (strNumber.length() >= 2 & (firstChar == '+' || firstChar == '-')) {
                for (int i = 1; i < size; ++i)
                    if (!Character.isDigit(strNumber.charAt(i)))
                        return false;
                return true;
            } else if (Character.isDigit(firstChar)) {
                for (int i = 0; i < size; ++i)
                    if (!Character.isDigit(strNumber.charAt(i)))
                        return false;
                return true;
            } else
                return false;
        } else
            return false;
    }

    public boolean assignIntYear() {
        try {
            this.number = Integer.parseInt(strNumber);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}

package com.netcracker.controllers.helpfun;

import com.netcracker.models.services.BookManager;
import com.netcracker.models.types.Book;

public class CheckCorrectBook {
    private final int yearStart = 0;
    private final int yearEnd = 2021;
    private String bookName;
    private String bookAuthor;
    private String bookYear;
    private int intBookYear;
    private boolean correct;
    private String errorString = "";

    public CheckCorrectBook(String bookName, String bookAuthor, String bookYear) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookYear = bookYear;
        this.intBookYear = -1;
        this.correct = false;
    }

    public boolean checkBookName() {
        if (bookName.length() > 0)
            return true;
        else {
            errorString += "неправилльно введено название книги\n";
            return false;
        }
    }

    public boolean checkAuthor() {
        if (bookAuthor.length() > 0)
            return true;
        else {
            errorString += "неправилльно введен автор книги\n";
            return false;
        }
    }

    public boolean assignIntYear() {
        try {
            this.intBookYear = Integer.parseInt(bookYear);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("ошибка формата числа");
            errorString += "неправилльно введено число\n";
            return false;
        }
    }

    public boolean checkYear() {
        if (isNumeric()) {  // если это число
            if (assignIntYear()) {  // если удость пробразовать string в int
                if (intBookYear > yearStart && intBookYear < yearEnd)  // если совпадает диапозон
                    return true;
                else {
                    errorString += "введен год не в пределах от " + yearStart + " - " + yearEnd + "\n";
                    return false;
                }
            } else {
                errorString += "не удалось пробразовать в число\n";
                return false;
            }
        } else {
            errorString += "( " + bookYear + " ) не является числом\n";
            return false;
        }
    }

//  public boolean correctBook()
//  {
//      if(checkBookName() && checkYear())
//      {
//          Book book = new Book(this.bookName, this.bookAuthor, this.intBookYear);
//         if (!(BookManager.getStaticBookManager().contains(book) ))
//             return true;
//         else return false;
//      } else return false;
//  }

    public boolean isNumeric() {
        if (bookYear.length() > 0) {
            int size = bookYear.length();
            char firstChar = bookYear.charAt(0);

            if (bookYear.length() >= 2 & (firstChar == '+' || firstChar == '-')) {
                for (int i = 1; i < size; ++i)
                    if (!Character.isDigit(bookYear.charAt(i)))
                        return false;
                return true;
            } else if (Character.isDigit(firstChar)) {
                for (int i = 0; i < size; ++i)
                    if (!Character.isDigit(bookYear.charAt(i)))
                        return false;
                return true;
            } else
                return false;
        } else
            return false;
    }

    public String getErrorString() {
        return errorString;
    }

    public boolean checkCorrectBook() {
        boolean cor = true;

        if (!checkBookName())
            cor = false;
        if (!checkAuthor())
            cor = false;
        if (!checkYear())
            cor = false;

        correct = cor;
        return correct;
    }

    public Book getBook() {
        if (correct)
            return new Book(bookName, bookAuthor, intBookYear);
        else return null;
    }

}

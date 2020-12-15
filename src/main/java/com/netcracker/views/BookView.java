package com.netcracker.views;


import com.netcracker.models.types.Book;

import java.util.Scanner;

public class BookView {


    public void printElem(int index, Book book)   // вывести элемент
    {
        System.out.println( index + ") " + book);
    }

    public int checkCorrectInteger()
    {
        Scanner in = new Scanner(System.in);
        String index = in.nextLine();

        int value = -1;
        try {
            value = Integer.parseInt(index);
        }
        catch (NumberFormatException e)
        {
            value =  -1;
        }
        return value;
    }


    public   Book  addElem()  // добавить элемент
    {

        String bookName =  getCorrectString("название:");
        String author = getCorrectString("автор:");
        int year = getYear();
        Book book = new Book(bookName, author, year);

        return book;
    }
    public void printAddElemTrue(final Book book)   // вывести надпись
    {
        System.out.println("книга ("+book+") добавлена");
    }

    public void printErrorCloneElem(final Book book)   // вывести надпись о том что книга уже есть
    {
        System.out.println("книга ("+book+") уже есть");
    }

    public void printAddElemFalse(final Book book)  // вывести надпись
    {
        System.out.println("книга ("+book+") не добавлена");
    }
    public void printNotFound(int index)     // вывести надпись
    {
        System.out.println("книга под номером " + index + " не найдена");
    }
    public void printZeroElem()     // вывести надпись
    {
        System.out.println("ноль предметов");
    }


    public String editElemBookName() // редактировать имя
    {
        Scanner in = new Scanner(System.in);
        System.out.print("название: ");
        String bookName = in.nextLine();
        return bookName;
    }
    public String editElemAuthor()  // редактировать автора
    {
        Scanner in = new Scanner(System.in);
        System.out.print("автор: ");
        String author = in.nextLine();
        return author;
    }

    public int editElemYear()  // редактировать год
    {
        int year = getYear();
        return year;
    }

    public int getIndex(String printInfo)  // предложение ввести число, проверить яляется ли это число integer
    {
        System.out.println(printInfo);
        int val = checkCorrectInteger();
        while (val == -1){
            System.out.println("введите корректное число, " + printInfo);
            val = checkCorrectInteger();
        }
        return val;
    }

    public int getYear()
    {

        int year = getIndex("год: ");
        while(year < 0 || year > 2021)
        {
            System.out.println("введите корректный год");
            year = getIndex("год: ");
        }
        return year;
    }

    public String getCorrectString(String printInfo)
    {
        System.out.println(printInfo);

        Scanner in = new Scanner(System.in);
        String string = in.nextLine();

        return string;
    }


    public void printErrorFormatInput()  // вывести надпись
    {
        System.out.println("неверный формат ввода");
    }
    public void printDeleteTrue(int index) // вывести надпись
    {
        System.out.println( "книга под индексом " + index + " удалена");
    }
    public void printDeleteFalse(int index) // вывести надпись
    {
        System.out.println( "книга под индексом " + index + " не удалена");
    }





    public void printRangeIndex(int indexA, int indexB){ // просьба ввести в диапозоне
        System.out.println("введите число от " + indexA + " до " + indexB );
    }

    public int doSmt( int amountOperation) // amountOperation количество операций
    {
        int index = -1;
        while (index <1 || index > amountOperation) {
            System.out.println(" 1 - добавить ");
            System.out.println(" 2 - удалить ");
            System.out.println(" 3 - отобразить ");
            System.out.println(" 4 - редактировать ");
            System.out.println(" 5 - выход ");


            index = checkCorrectInteger();

            if (index < 1 || index > amountOperation) {
                System.out.println("неверный формат");
                printRangeIndex(1, amountOperation); // если непраливльно ввели
            }

        }
        System.out.println("выбрано " + index);

        return index;
    }

}

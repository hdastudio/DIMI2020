package com.example.Model;


public class Book {
    private String bookName;
    private String author;
    private int presentYear;
    private int bookId;

    public Book(String bookName, String author, int presentYear) {
        this.bookName = bookName;
        this.author = author;
        this.presentYear = presentYear;

        this.bookId = getHashBook();
    }

    public Book(int hashId, String bookName, String author, int presentYear) {
        this.bookName = bookName;
        this.author = author;
        this.presentYear = presentYear;
        this.bookId = hashId;
    }

    public Book() {
        this.bookName = "-";
        this.author = "-";
        this.presentYear = 0;
    }

    public int getHashBook() {
        String hash = bookName + author + presentYear;
        hash = hash.replaceAll("\\s", "");
        return hash.hashCode();
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
        this.bookId = getHashBook();
    }

    public String getAuthor() {
        return author;
    }

    public int getBookId() {
        return bookId;
    }

    public void setAuthor(String author) {
        this.author = author;
        this.bookId = getHashBook();
    }

    public int getPresentYear() {
        return presentYear;
    }

    public void setPresentYear(int presentYear) {
        this.presentYear = presentYear;
        this.bookId = getHashBook();
    }

    public void setElements(String bookName, String author, int presentYear) {
        this.bookName = bookName;
        this.author = author;
        this.presentYear = presentYear;
        this.bookId = getHashBook();
    }

    public static int compare(Book p1, Book p2) {
        if (p1.getBookId() == p2.getBookId())
            return 1;
        return -1;
    }

    @Override
    public String toString() {
        return (bookId + ", " + bookName + ", " + author + ", " + presentYear);
    }

    @Override
    public boolean equals(Object v) {
        boolean retVal = false;

        if (v instanceof Book) {
            Book ptr = (Book) v;
            retVal = ptr.getHashBook() == this.bookId;
        }

        return retVal;
    }
}

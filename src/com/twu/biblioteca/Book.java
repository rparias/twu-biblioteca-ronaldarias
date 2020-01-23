package com.twu.biblioteca;

public class Book {

    private String title;
    private String author;
    private int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }


    public String printTitleAuthorAndYearBook() {
        return String.format("%-35s%-25s%-4d", title, author, year);
    }
}

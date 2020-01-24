package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.Book;

public class BookImp implements Book {

    private String title;
    private String author;
    private int year;
    private boolean isAvailable;

    public BookImp(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        isAvailable = true;
    }

    @Override
    public String printTitleAuthorAndYearBook() {
        return String.format("%-35s%-25s%-4d", title, author, year);
    }

    @Override
    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public void setAvailability(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String getTitle() {
        return title;
    }
}

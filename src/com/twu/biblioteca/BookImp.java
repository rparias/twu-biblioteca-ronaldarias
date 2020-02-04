package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.Composition;

public class BookImp implements Composition {

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

    private String printTitleAuthorAndYearBook() {
        return String.format("%-35s%-25s%-4d", title, author, year);
    }

    @Override
    public String printCompositionInfo() {
        return printTitleAuthorAndYearBook();
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

    @Override
    public boolean equals(Object obj) {
        BookImp composition = (BookImp) obj;
        return title.equals(composition.title) &&
                author.equals(composition.author) &&
                year == composition.year &&
                isAvailable == composition.isAvailable;
    }
}

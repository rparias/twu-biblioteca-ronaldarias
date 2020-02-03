package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.Composition;

public class MovieImp implements Composition {

    private String name;
    private int year;
    private String director;
    private int rating;
    private boolean isAvailable;

    public MovieImp(String name, int year, String director, Integer rating) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating == null ? 0 : rating;
        isAvailable = true;
    }

    private String printNameYearDirectorAndRating() {
        return String.format("%-25s%-10d%-25s%-2d", name, year, director, rating);
    }

    @Override
    public String printCompositionInfo() {
        return printNameYearDirectorAndRating();
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
        return name;
    }
}

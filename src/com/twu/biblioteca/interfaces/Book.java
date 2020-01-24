package com.twu.biblioteca.interfaces;

public interface Book {

    String printTitleAuthorAndYearBook();
    boolean isAvailable();
    void setAvailability(boolean isAvailable);
    String getTitle();
}

package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {

    public static void main(String[] args) {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");

        Library library = new Library(books(), System.out);
        library.listBooks();
    }

    private static List<String> books() {
        List<String> books = new ArrayList<String>();
        books.add("Clean Code");
        books.add("Test Driven Development by Example");
        books.add("Clean Architecture");
        return books;
    }
}

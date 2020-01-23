package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {

    public static void main(String[] args) {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");

        System.out.printf("\n%-35s%-25s%-4s\n\n", "TITLE", "AUTHOR", "YEAR");
        Library library = new Library(books(), System.out);
        library.listBooks();
    }

    private static List<Book> books() {
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Clean Code", "Robert Martin", 2010));
        books.add(new Book("TDD by Example", "Kent Beck", 2008));
        books.add(new Book("Clean Architecture", "Robert Martin", 2014));
        return books;
    }
}

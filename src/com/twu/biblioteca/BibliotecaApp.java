package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BibliotecaApp {

    public static void main(String[] args) {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n");

        displayMenu();
    }

    private static void displayMenu() {
        Menu menu = new Menu(options(), System.out, new BufferedReader(new InputStreamReader(System.in)));
        menu.listOptions();

        System.out.println("Enter a number option from menu");
        validateOption(menu.findOptionFromNumber());
    }

    private static void validateOption(Option option) {
        if (option != null) {
            selectOptionFromUser(option);
        } else {
            displayMenu();
        }
    }

    private static void selectOptionFromUser(Option option) {
        switch (option.getNumberOption()) {
            case 1:
                displayListOfBooks();
                break;
            default:
                System.out.println("Wrong option");
                break;
        }
    }

    private static void displayListOfBooks() {
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

    private static Map<String, Option> options() {
        Map<String, Option> options = new HashMap<String, Option>();
        options.put("1", new Option(1, "List of Books"));
        return options;
    }
}

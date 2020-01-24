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

    private static void validateOption(int option) {
        if (option == 0) {
            showErrorMessage();
        } else {
            selectOptionFromUser(option);
        }
    }

    private static void showErrorMessage() {
        System.out.println("Please select a valid option\n");
        displayMenu();
    }

    private static void selectOptionFromUser(int numberOption) {
        switch (numberOption) {
            case 1:
                displayListOfBooks();
                break;
            case 9:
                quitApplication();
                break;
            default:
                showErrorMessage();
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

    private static Map<Integer, String> options() {
        Map<Integer, String> options = new HashMap<Integer, String>();
        options.put(1, "List of Books");
        options.put(9, "Quit");
        return options;
    }

    private static void quitApplication() {
        System.exit(0);
    }
}

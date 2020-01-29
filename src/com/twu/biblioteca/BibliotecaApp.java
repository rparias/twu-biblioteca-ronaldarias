package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.Book;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BibliotecaApp {

    public static void main(String[] args) {
        showMessage("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");

        displayMenu();
    }

    private static void displayMenu() {
        Menu menu = new Menu(options(), System.out, new BufferedReader(new InputStreamReader(System.in)));
        menu.listOptions();

        showMessage("Enter a number option from menu");
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
        showMessage("Please select a valid option");
        displayMenu();
    }

    private static void selectOptionFromUser(int numberOption) {
        switch (numberOption) {
            case 1:
                displayListOfAvailableBooks();
                displayMenu();
                break;
            case 2:
                checkOutBook();
                break;
            case 3:
                returnBook();
                break;
            case 9:
                quitApplication();
                break;
            default:
                showErrorMessage();
                break;
        }
    }

    private static void displayListOfAvailableBooks() {
        showHeaderWithTitleAuthorAndYear();
        LibraryImp library = LibraryImp.getInstanceLibrary(books(), System.out, new BufferedReader(new InputStreamReader(System.in)));
        library.printAvailableBooks();
    }

    private static void displayListOfCheckecOutBooks() {
        showHeaderWithTitleAuthorAndYear();
        LibraryImp library = LibraryImp.getInstanceLibrary(books(), System.out, new BufferedReader(new InputStreamReader(System.in)));
        library.printCheckedOutBooks();
    }

    private static void checkOutBook() {
        displayListOfAvailableBooks();
        LibraryImp library = LibraryImp.getInstanceLibrary(books(), System.out, new BufferedReader(new InputStreamReader(System.in)));
        Book book = library.enterBookName();
        library.checkoutBook(book);
        displayMenu();
    }

    private static void returnBook() {
        displayListOfCheckecOutBooks();
        LibraryImp library = LibraryImp.getInstanceLibrary(books(), System.out, new BufferedReader(new InputStreamReader(System.in)));
        Book book = library.enterBookName();
        library.returnBook(book);
        displayMenu();
    }

    private static List<Book> books() {
        List<Book> books = new ArrayList<Book>();
        books.add(new BookImp("Clean Code", "Robert Martin", 2010));
        books.add(new BookImp("TDD by Example", "Kent Beck", 2008));
        books.add(new BookImp("Clean Architecture", "Robert Martin", 2014));
        return books;
    }

    private static Map<Integer, String> options() {
        Map<Integer, String> options = new HashMap<Integer, String>();
        options.put(1, "List of Books");
        options.put(2, "Checkout a book");
        options.put(3, "Return a book");
        options.put(9, "Quit");
        return options;
    }

    private static void quitApplication() {
        System.exit(0);
    }

    private static void showMessage(String message) {
        System.out.println(message + "\n");
    }

    private static void showHeaderWithTitleAuthorAndYear() {
        System.out.printf("\n%-35s%-25s%-4s\n\n", "TITLE", "AUTHOR", "YEAR");
    }
}

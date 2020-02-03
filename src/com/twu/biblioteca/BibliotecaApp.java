package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.Composition;
import com.twu.biblioteca.interfaces.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BibliotecaApp {

    static PrintStream printStream = System.out;
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static LibraryImp libraryForBooks = new LibraryImp(books(), printStream, bufferedReader);
    static LibraryImp libraryForMovies = new LibraryImp(movies(), printStream, bufferedReader);
    static LoginImp login = new LoginImp(printStream, users(), bufferedReader);
    static Menu menu = new Menu(options(), printStream, bufferedReader);
    static User loggedUser;

    public static void main(String[] args) {
        showWelcomeMessage();
        validateUserAndPassword();
    }

    private static void showWelcomeMessage() {
        printHorizontalLines();
        showMessage("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        printHorizontalLines();
    }

    private static void validateUserAndPassword() {
        loggedUser = login.enterUserNameAndPassword();
        if (loggedUser != null) {
            displayMenu();
        } else {
            validateUserAndPassword();
        }
    }

    private static void displayMenu() {
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
            case 4:
                displayListOfAvailableMovies();
                displayMenu();
                break;
            case 5:
                checkOutMovie();
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
        showHeaderBooksWithTitleAuthorAndYear();
        libraryForBooks.printAvailableCompositions();
    }

    private static void displayListOfCheckedOutBooks() {
        showHeaderBooksWithTitleAuthorAndYear();
        libraryForBooks.printCheckedOutBooks();
    }

    private static void checkOutBook() {
        displayListOfAvailableBooks();
        Composition book = libraryForBooks.enterBookName();
        libraryForBooks.checkoutComposition(book);
        displayMenu();
    }

    private static void returnBook() {
        displayListOfCheckedOutBooks();
        Composition book = libraryForBooks.enterBookName();
        libraryForBooks.returnComposition(book);
        displayMenu();
    }

    private static List<Composition> books() {
        List<Composition> books = new ArrayList<Composition>();
        books.add(new BookImp("Clean Code", "Robert Martin", 2010));
        books.add(new BookImp("TDD by Example", "Kent Beck", 2008));
        books.add(new BookImp("Clean Architecture", "Robert Martin", 2014));
        return books;
    }

    private static List<Composition> movies() {
        List<Composition> movies = new ArrayList<Composition>();
        movies.add(new MovieImp("Clockwork Orange", 1971, "Stanley Kubrick", 10));
        movies.add(new MovieImp("Pulp Fiction", 1994, "Quentin Tarantino", 9));
        movies.add(new MovieImp("Reservoir Dogs", 1992, "Quentin Tarantino", 7));
        return movies;
    }

    private static List<User> users() {
        List<User> users = new ArrayList<>();
        users.add(new Customer("Ronald", "ariasron@hotmail.com", "0987654321", "123-4567"));
        users.add(new Customer("Veronica", "vero@hotmail.com", "0976857633", "234-5678"));
        users.add(new Customer("Bryan", "bryan@hotmail.com", "0998987654", "345-6789"));
        users.add(new Customer("Librarian", "librarian@hotmail.com", "0997656789", "999-9999"));
        return users;
    }

    private static void displayListOfAvailableMovies() {
        showHeaderMoviesWithNameYearDirectorAndRating();
        libraryForMovies.printAvailableCompositions();
    }

    private static void checkOutMovie() {
        displayListOfAvailableMovies();
        Composition movie = libraryForMovies.enterBookName();
        libraryForMovies.checkoutComposition(movie);
        displayMenu();
    }

    private static Map<Integer, String> options() {
        Map<Integer, String> options = new HashMap<Integer, String>();
        options.put(1, "List of Books");
        options.put(2, "Checkout a book");
        options.put(3, "Return a book");
        options.put(4, "List of Movies");
        options.put(5, "Checkout a movie");
        options.put(9, "Quit");
        return options;
    }

    private static void quitApplication() {
        System.exit(0);
    }

    private static void showMessage(String message) {
        printStream.println(message + "\n");
    }

    private static void showHeaderBooksWithTitleAuthorAndYear() {
        printStream.printf("\n%-35s%-25s%-4s\n", "TITLE", "AUTHOR", "YEAR");
    }

    private static void showHeaderMoviesWithNameYearDirectorAndRating() {
        printStream.printf("\n%-25s%-10s%-25s%-2s\n", "NAME", "YEAR", "DIRECTOR", "RATING");
    }

    private static void printHorizontalLines() {
        showMessage("================================================================================");
    }
}

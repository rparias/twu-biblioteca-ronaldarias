package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.Composition;
import com.twu.biblioteca.interfaces.Library;
import com.twu.biblioteca.interfaces.Printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class LibraryImp implements Library, Printer {

    private List<Composition> compositions;
    private PrintStream printStream;
    private BufferedReader bufferedReader;

    // public for juint, problems with singleton in tests
    public LibraryImp(List<Composition> compositions, PrintStream printStream, BufferedReader bufferedReader) {
        this.compositions = compositions;
        this.printStream = printStream;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public List<Composition> listAllCompositions() {
        return compositions;
    }

    @Override
    public List<Composition> listAvailableCompositions() {
        List<Composition> availableBooks = new ArrayList<Composition>();
        for (Composition book: compositions) {
            if (book.isAvailable())
                availableBooks.add(book);
        }
        return availableBooks;
    }

    @Override
    public List<Composition> listCheckedOutCompositions() {
        List<Composition> checkedOutBooks = new ArrayList<Composition>();
        for (Composition book: compositions) {
            if (!book.isAvailable())
                checkedOutBooks.add(book);
        }
        return checkedOutBooks;
    }

    @Override
    public void checkoutComposition(Composition composition) {
        if (composition != null) {
            changeBookToCheckedOut(composition);
        } else {
            showUnsuccessMessageCheckout();
        }
    }

    private void changeBookToCheckedOut(Composition book) {
        if (book.isAvailable()) {
            book.setAvailability(false);
            showSuccessMessageCheckout();
        } else {
            showUnsuccessMessageCheckout();
        }
    }

    @Override
    public void returnComposition(Composition composition) {
        if (composition != null) {
            changeBookToAvailable(composition);
        } else {
            showUnsuccessMessageReturn();
        }
    }

    private void changeBookToAvailable(Composition book) {
        if (!book.isAvailable()) {
            book.setAvailability(true);
            showSuccessMessageOnReturn();
        } else {
            showUnsuccessMessageReturn();
        }
    }

    public void printAvailableCompositions() {
        displayCompositions(listAvailableCompositions());
    }

    public void printCheckedOutBooks() {
        displayCompositions(listCheckedOutCompositions());
    }

    @Override
    public void displayCompositions(List<Composition> compositions) {
        String compositionList = "";
        for (Composition composition: compositions) {
            compositionList += composition.printCompositionInfo() + "\n";
        }
        printStream.println(compositionList);
    }

    public Composition enterBookName() {
        printStream.println("Please enter book name");
        String bookName = readLine();
        return findCompositionByName(bookName);
    }

    @Override
    public Composition findCompositionByName(String name) {
        Composition foundBook = null;
        for (Composition book: listAllCompositions()) {
            if (book.getTitle().equals(name))
                foundBook = book;
        }
        return foundBook;
    }

    private String readLine() {
        String book = null;
        try {
            book = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return book;
    }

    private void showSuccessMessageCheckout() {
        printStream.println("Thank you! Enjoy the book\n");
    }

    private void showUnsuccessMessageCheckout() {
        printStream.println("Sorry, that book is not available\n");
    }

    private void showSuccessMessageOnReturn() {
        printStream.println("Thank you for returning the book\n");
    }

    private void showUnsuccessMessageReturn() {
        printStream.println("That is not a valid book to return\n");
    }
}

package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.Book;
import com.twu.biblioteca.interfaces.Library;
import com.twu.biblioteca.interfaces.Printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class LibraryImp implements Library, Printer {

    private List<Book> books;
    private PrintStream printStream;
    private BufferedReader bufferedReader;
    private static LibraryImp library = null;

    public static LibraryImp getInstanceLibrary(List<Book> books, PrintStream printStream, BufferedReader bufferedReader) {
        if (library == null) {
            library = new LibraryImp(books, printStream, bufferedReader);
        }
        return library;
    }

    // public for juint, problems with singleton in tests
    public LibraryImp(List<Book> books, PrintStream printStream, BufferedReader bufferedReader) {
        this.books = books;
        this.printStream = printStream;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public List<Book> listAllBooks() {
        return books;
    }

    @Override
    public List<Book> listAvailableBooks() {
        List<Book> availableBooks = new ArrayList<Book>();
        for (Book book: books) {
            if (book.isAvailable())
                availableBooks.add(book);
        }
        return availableBooks;
    }

    @Override
    public List<Book> listCheckedOutBooks() {
        List<Book> checkedOutBooks = new ArrayList<Book>();
        for (Book book: books) {
            if (!book.isAvailable())
                checkedOutBooks.add(book);
        }
        return checkedOutBooks;
    }

    @Override
    public void checkoutBook(Book book) {
        if (book != null) {
            changeBookToCheckedOut(book);
        } else {
            showUnsuccessMessageCheckout();
        }
    }

    private void changeBookToCheckedOut(Book book) {
        if (book.isAvailable()) {
            book.setAvailability(false);
            showSuccessMessageCheckout();
        } else {
            showUnsuccessMessageCheckout();
        }
    }

    @Override
    public void returnBook(Book book) {
        if (book != null) {
            changeBookToAvailable(book);
        } else {
            showUnsuccessMessageReturn();
        }
    }

    private void changeBookToAvailable(Book book) {
        if (!book.isAvailable()) {
            book.setAvailability(true);
            showSuccessMessageOnReturn();
        } else {
            showUnsuccessMessageReturn();
        }
    }

    public void printAvailableBooks() {
        displayBooks(listAvailableBooks());
    }

    public void printCheckedOutBooks() {
        displayBooks(listCheckedOutBooks());
    }

    @Override
    public void displayBooks(List<Book> books) {
        String bookList = "";
        for (Book book: books) {
            bookList += book.printTitleAuthorAndYearBook() + "\n";
        }
        printStream.println(bookList);
    }

    public Book enterBookName() {
        printStream.println("Please enter book name");
        String bookName = readLine();
        return findBookByName(bookName);
    }

    @Override
    public Book findBookByName(String name) {
        Book foundBook = null;
        for (Book book: listAllBooks()) {
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

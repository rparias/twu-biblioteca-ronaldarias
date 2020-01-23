package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.List;

public class Library {

    List<String> books;
    PrintStream printStream;

    public Library(List<String> books, PrintStream printStream) {
        this.books = books;
        this.printStream = printStream;
    }

    public void listBooks() {
        String bookList = "";
        for (String book: books) {
            bookList += book + "\n";
        }
        printStream.println(bookList);
    }
}

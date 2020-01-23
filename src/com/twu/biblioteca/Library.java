package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.List;

public class Library {

    List<Book> books;
    PrintStream printStream;

    public Library(List<Book> books, PrintStream printStream) {
        this.books = books;
        this.printStream = printStream;
    }

    public void listBooks() {
        String bookList = "";
        for (Book book: books) {
            bookList += book.printTitleAuthorAndYearBook() + "\n";
        }
        printStream.println(bookList);
    }
}

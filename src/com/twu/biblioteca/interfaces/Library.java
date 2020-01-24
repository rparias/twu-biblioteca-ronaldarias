package com.twu.biblioteca.interfaces;

import java.util.List;

public interface Library {

    List<Book> listAllBooks();
    List<Book> listAvailableBooks();
    List<Book> listCheckedOutBooks();
    void checkoutBook(Book book);
    void returnBook(Book book);
    Book findBookByName(String name);

}

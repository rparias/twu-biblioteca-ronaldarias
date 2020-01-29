package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.Book;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LibraryTest {

    private List<Book> books;
    private LibraryImp library;
    private PrintStream printStream;
    private BufferedReader bufferedReader;

    @Before
    public void setUp() throws Exception {
        books = new ArrayList<Book>();
        printStream = mock(PrintStream.class);
        bufferedReader = mock(BufferedReader.class);
        library = new LibraryImp(books, printStream, bufferedReader);
    }

    @Test
    public void shouldPrintAListOfAllLibraryBooks() {
        // Given - Arrange
        Book book1 = new BookImp("Clean Code", "Robert Martin", 2010);
        this.books.add(book1);

        // When - Act
        List<Book> books = library.listAllBooks();
        library.displayBooks(books);

        // Then - Assert
        verify(printStream).println("Clean Code                         Robert Martin            2010\n");
    }

    @Test
    public void ShouldBeAvailableWhenBookIsCreated() {
        // Given - Arrange
        Book book = new BookImp("Clean Code", "Robert Martin", 2010);
        books.add(book);

        // When - Act
        boolean isAvailable = book.isAvailable();

        // Then - Assert
        assertThat(isAvailable, is(true));
    }

    @Test
    public void ShouldBeNotAvailableWhenBookIsCheckedOut() {
        // Given - Arrange
        Book book = new BookImp("Clean Code", "Robert Martin", 2010);
        books.add(book);

        // When - Act
        library.checkoutBook(book);
        boolean isAvailable = book.isAvailable();

        // Then - Assert
        assertThat(isAvailable, is(false));
    }

    @Test
    public void shouldNotPrintIfBookIsCheckedOut() {
        // Given - Arrange
        Book book1 = new BookImp("Clean Code", "Robert Martin", 2010);
        Book book2 = new BookImp("TDD by Example", "Kent Beck", 2008);
        books.add(book1);
        books.add(book2);

        // When - Act
        library.checkoutBook(book1);
        List<Book> availableBooks = library.listAvailableBooks();

        // Then - Assert
        assertThat(availableBooks, not(hasItems(book1)));
    }

    @Test
    public void shouldReturnBookWhenUserIntroduceNameBook() {
        // Given - Arrange
        Book book1 = new BookImp("Clean Code", "Robert Martin", 2010);
        Book book2 = new BookImp("TDD by Example", "Kent Beck", 2008);
        books.add(book1);
        books.add(book2);
        String nameBook = "Clean Code";

        // When - Act
        Book book = library.findBookByName(nameBook);

        // Then - Assert
        assertThat(book.getTitle(), is("Clean Code"));
    }

    @Test
    public void shouldReturnNullWhenUserIntroduceWrongNameBook() {
        // Given - Arrange
        Book book1 = new BookImp("Clean Code", "Robert Martin", 2010);
        Book book2 = new BookImp("TDD by Example", "Kent Beck", 2008);
        books.add(book1);
        books.add(book2);
        String nameBook = "Clean Ce";

        // When - Act
        Book book = library.findBookByName(nameBook);

        // Then - Assert
        assertThat(book, is(nullValue()));
    }

    @Test
    public void shouldDisplaySuccessMessageOnCheckOutOfABook() {
        // Given - Arrange
        Book book1 = new BookImp("Clean Code", "Robert Martin", 2010);
        Book book2 = new BookImp("TDD by Example", "Kent Beck", 2008);
        books.add(book1);
        books.add(book2);

        // When - Act
        library.checkoutBook(book1);

        // Then - Assert
        verify(printStream).println("Thank you! Enjoy the book\n");
    }

    @Test
    public void shouldDisplayUnsuccessMessageOnCheckOutOfABook() {
        // Given - Arrange
        Book book1 = new BookImp("Clean Code", "Robert Martin", 2010);
        book1.setAvailability(false);
        Book book2 = new BookImp("TDD by Example", "Kent Beck", 2008);
        books.add(book1);
        books.add(book2);

        // When - Act
        library.checkoutBook(null);

        // Then - Assert
        verify(printStream).println("Sorry, that book is not available\n");
    }

    @Test
    public void shouldListOnlyCheckedOutBooks() {
        // Given - Arrange
        Book book1 = new BookImp("Clean Code", "Robert Martin", 2010);
        Book book2 = new BookImp("TDD by Example", "Kent Beck", 2008);
        books.add(book1);
        books.add(book2);

        // When - Act
        library.checkoutBook(book1);
        List<Book> unavailableBooks = library.listCheckedOutBooks();

        // Then - Assert
        assertThat(unavailableBooks, hasItems(book1));
        assertThat(unavailableBooks, not(hasItems(book2)));
    }

    @Test
    public void shouldReturnTrueInAvailabilityWhenBookIsReturned() {
        // Given - Arrange
        Book book = new BookImp("Clean Code", "Robert Martin", 2010);
        book.setAvailability(false);
        books.add(book);

        // When - Act
        library.returnBook(book);

        // Then - Assert
        assertThat(book.isAvailable(), is(true));
    }

    @Test
    public void shouldDisplaySuccessMessageOnSuccessfulReturn() {
        // Given - Arrange
        Book book1 = new BookImp("Clean Code", "Robert Martin", 2010);
        book1.setAvailability(false);
        Book book2 = new BookImp("TDD by Example", "Kent Beck", 2008);
        books.add(book1);
        books.add(book2);

        // When - Act
        library.returnBook(book1);

        // Then - Assert
        verify(printStream).println("Thank you for returning the book\n");
    }

    @Test
    public void shouldDisplayUnsuccessfulMessageOnUnsuccessfulReturn() {
        // Given - Arrange
        Book book1 = new BookImp("Clean Code", "Robert Martin", 2010);
        Book book2 = new BookImp("TDD by Example", "Kent Beck", 2008);
        books.add(book1);
        books.add(book2);

        // When - Act
        library.returnBook(null);

        // Then - Assert
        verify(printStream).println("That is not a valid book to return\n");
    }
}

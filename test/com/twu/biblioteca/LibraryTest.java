package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.Booking;
import com.twu.biblioteca.interfaces.Composition;
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

    private List<Composition> books;
    private LibraryImp library;
    private PrintStream printStream;
    private BufferedReader bufferedReader;
    Customer customer;

    @Before
    public void setUp() throws Exception {
        books = new ArrayList<Composition>();
        printStream = mock(PrintStream.class);
        bufferedReader = mock(BufferedReader.class);
        library = new LibraryImp(books, printStream, bufferedReader);
        customer = new Customer("Ronald", "ariasron@hotmail.com", "0987654321", "123-4567");
    }

    @Test
    public void shouldPrintAListOfAllLibraryBooks() {
        // Given - Arrange
        Composition book1 = new BookImp("Clean Code", "Robert Martin", 2010);
        this.books.add(book1);

        // When - Act
        List<Composition> books = library.listAllCompositions();
        library.displayCompositions(books);

        // Then - Assert
        verify(printStream).println("Clean Code                         Robert Martin            2010\n");
    }

    @Test
    public void ShouldBeAvailableWhenCompositionIsCreated() {
        // Given - Arrange
        Composition book = new BookImp("Clean Code", "Robert Martin", 2010);
        books.add(book);

        // When - Act
        boolean isAvailable = book.isAvailable();

        // Then - Assert
        assertThat(isAvailable, is(true));
    }

    @Test
    public void ShouldBeNotAvailableWhenCompositionIsCheckedOut() {
        // Given - Arrange
        Composition book = new BookImp("Clean Code", "Robert Martin", 2010);
        books.add(book);

        // When - Act
        library.checkoutComposition(book, customer);
        boolean isAvailable = book.isAvailable();

        // Then - Assert
        assertThat(isAvailable, is(false));
    }

    @Test
    public void shouldNotPrintIfBookIsCheckedOut() {
        // Given - Arrange
        Composition book1 = new BookImp("Clean Code", "Robert Martin", 2010);
        Composition book2 = new BookImp("TDD by Example", "Kent Beck", 2008);
        books.add(book1);
        books.add(book2);

        // When - Act
        library.checkoutComposition(book1, customer);
        List<Composition> availableBooks = library.listAvailableCompositions();

        // Then - Assert
        assertThat(availableBooks, not(hasItems(book1)));
    }

    @Test
    public void shouldReturnBookWhenUserIntroduceNameBook() {
        // Given - Arrange
        Composition book1 = new BookImp("Clean Code", "Robert Martin", 2010);
        Composition book2 = new BookImp("TDD by Example", "Kent Beck", 2008);
        books.add(book1);
        books.add(book2);
        String nameBook = "Clean Code";

        // When - Act
        Composition book = library.findCompositionByName(nameBook);

        // Then - Assert
        assertThat(book.getTitle(), is("Clean Code"));
    }

    @Test
    public void shouldReturnNullWhenUserIntroduceWrongNameBook() {
        // Given - Arrange
        Composition book1 = new BookImp("Clean Code", "Robert Martin", 2010);
        Composition book2 = new BookImp("TDD by Example", "Kent Beck", 2008);
        books.add(book1);
        books.add(book2);
        String nameBook = "Clean Ce";

        // When - Act
        Composition book = library.findCompositionByName(nameBook);

        // Then - Assert
        assertThat(book, is(nullValue()));
    }

    @Test
    public void shouldDisplaySuccessMessageOnCheckOutOfABook() {
        // Given - Arrange
        Composition book1 = new BookImp("Clean Code", "Robert Martin", 2010);
        Composition book2 = new BookImp("TDD by Example", "Kent Beck", 2008);
        books.add(book1);
        books.add(book2);

        // When - Act
        library.checkoutComposition(book1, customer);

        // Then - Assert
        verify(printStream).println("Thank you! Enjoy the book\n");
    }

    @Test
    public void shouldDisplayUnsuccessMessageOnCheckOutOfABook() {
        // Given - Arrange
        Composition book1 = new BookImp("Clean Code", "Robert Martin", 2010);
        book1.setAvailability(false);
        Composition book2 = new BookImp("TDD by Example", "Kent Beck", 2008);
        books.add(book1);
        books.add(book2);

        // When - Act
        library.checkoutComposition(null, customer);

        // Then - Assert
        verify(printStream).println("Sorry, that book is not available\n");
    }

    @Test
    public void shouldListOnlyCheckedOutBooks() {
        // Given - Arrange
        Composition book1 = new BookImp("Clean Code", "Robert Martin", 2010);
        Composition book2 = new BookImp("TDD by Example", "Kent Beck", 2008);
        books.add(book1);
        books.add(book2);

        // When - Act
        library.checkoutComposition(book1, customer);
        List<Composition> unavailableBooks = library.listCheckedOutCompositions();

        // Then - Assert
        assertThat(unavailableBooks, hasItems(book1));
        assertThat(unavailableBooks, not(hasItems(book2)));
    }

    @Test
    public void shouldReturnTrueInAvailabilityWhenCompositionIsReturned() {
        // Given - Arrange
        Composition book = new BookImp("Clean Code", "Robert Martin", 2010);
        book.setAvailability(false);
        books.add(book);

        // When - Act
        library.returnComposition(book, customer);

        // Then - Assert
        assertThat(book.isAvailable(), is(true));
    }

    @Test
    public void shouldDisplaySuccessMessageOnSuccessfulReturn() {
        // Given - Arrange
        Composition book1 = new BookImp("Clean Code", "Robert Martin", 2010);
        book1.setAvailability(false);
        Composition book2 = new BookImp("TDD by Example", "Kent Beck", 2008);
        books.add(book1);
        books.add(book2);

        // When - Act
        library.returnComposition(book1, customer);

        // Then - Assert
        verify(printStream).println("Thank you for returning the book\n");
    }

    @Test
    public void shouldDisplayUnsuccessfulMessageOnUnsuccessfulReturn() {
        // Given - Arrange
        Composition book1 = new BookImp("Clean Code", "Robert Martin", 2010);
        Composition book2 = new BookImp("TDD by Example", "Kent Beck", 2008);
        books.add(book1);
        books.add(book2);

        // When - Act
        library.returnComposition(null, customer);

        // Then - Assert
        verify(printStream).println("That is not a valid book to return\n");
    }

    @Test
    public void shouldListInBookingsIfBookIsCheckedOut() {
        // Given - Arrange
        Composition book1 = new BookImp("Clean Code", "Robert Martin", 2010);
        Composition book2 = new BookImp("TDD by Example", "Kent Beck", 2008);
        books.add(book1);
        books.add(book2);

        // When - Act
        library.checkoutComposition(book1, customer);
        Booking booking = new BookingImp(book1, customer);
        Booking booking2 = new BookingImp(book2, customer);
        List<Booking> bookings = library.listBookingCompositions();

        // Then - Assert
        assertThat(bookings, hasItems(booking));
        assertThat(bookings, not(hasItems(booking2)));
    }

    @Test
    public void shouldContainsAllBookings() {
        // Given - Arrange
        Composition book1 = new BookImp("Clean Code", "Robert Martin", 2010);
        Composition book2 = new BookImp("TDD by Example", "Kent Beck", 2008);
        Customer customer2 = new Customer("Veronica", "vero@hotmail.com", "0976857633", "234-5678");
        books.add(book1);
        books.add(book2);

        // When - Act
        library.checkoutComposition(book1, customer);
        library.checkoutComposition(book2, customer2);
        Booking booking = new BookingImp(book1, customer);
        Booking booking2 = new BookingImp(book2, customer2);
        List<Booking> bookings = library.listBookingCompositions();

        // Then - Assert
        assertThat(bookings, hasItems(booking, booking2));
    }

    @Test
    public void shouldContainsOnlyBookingsByUser() {
        // Given - Arrange
        Composition book1 = new BookImp("Clean Code", "Robert Martin", 2010);
        Composition book2 = new BookImp("TDD by Example", "Kent Beck", 2008);
        Customer customer2 = new Customer("Veronica", "vero@hotmail.com", "0976857633", "234-5678");
        books.add(book1);
        books.add(book2);

        // When - Act
        library.checkoutComposition(book1, customer);
        library.checkoutComposition(book2, customer2);
        Booking booking = new BookingImp(book1, customer);
        Booking booking2 = new BookingImp(book2, customer2);
        List<Booking> bookings = library.listBookingCompositionsByUser(customer2);

        // Then - Assert
        assertThat(bookings, hasItems(booking2));
        assertThat(bookings, not(hasItems(booking)));
    }
}

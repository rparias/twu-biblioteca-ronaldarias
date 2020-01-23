package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class LibraryTest {

    private List<Book> books;
    private Library library;
    private PrintStream printStream;

    @Before
    public void setUp() throws Exception {
        books = new ArrayList<Book>();
        printStream = mock(PrintStream.class);
        library = new Library(books, printStream);
    }

    @Test
    public void shouldPrintAListOfAllLibraryBooks() {
        // Given - Arrange
        Book book1 = new Book("Clean Code", "Robert Martin", 2010);
        books.add(book1);

        // When - Act
        library.listBooks();

        // Then - Assert
        verify(printStream).println("Clean Code                         Robert Martin            2010\n");
    }
}

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

    private List<String> books;
    private Library library;
    private PrintStream printStream;

    @Before
    public void setUp() throws Exception {
        books = new ArrayList<String>();
        printStream = mock(PrintStream.class);
        library = new Library(books, printStream);
    }

    @Test
    public void shouldPrintAListOfAllLibraryBooks() {
        // Given - Arrange
        String book1 = "Test Driven Development by Example";
        String book2 = "Clean Code";
        books.add(book1);
        books.add(book2);

        // When - Act
        library.listBooks();

        // Then - Assert
        verify(printStream).println("Test Driven Development by Example\nClean Code\n");
    }
}

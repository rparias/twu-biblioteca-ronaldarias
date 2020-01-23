package com.twu.biblioteca;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class BookTest {

    @Test
    public void shouldPrintTitleAuthorAndYearBook() {
        // Given - Arrange
        String title = "Clean Code";
        String author = "Robert Martin";
        int year = 2010;
        Book book = new Book(title, author, year);

        // When - Act
        String bookInfo = book.printTitleAuthorAndYearBook();

        // Then - Assert
        assertThat(bookInfo, is("Clean Code                         Robert Martin            2010"));
    }
}

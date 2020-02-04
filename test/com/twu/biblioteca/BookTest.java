package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.Composition;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BookTest {

    @Test
    public void shouldPrintTitleAuthorAndYearBook() {
        // Given - Arrange
        String title = "Clean Code";
        String author = "Robert Martin";
        int year = 2010;
        Composition book = new BookImp(title, author, year);

        // When - Act
        String bookInfo = book.printCompositionInfo();

        // Then - Assert
        assertThat(bookInfo, is("Clean Code                         Robert Martin            2010"));
    }

    @Test
    public void shouldBeEqualsBookImpWithComposition() {
        // Given - Arrange
        Composition book = new BookImp("Clean Code", "Robert Martin", 2010);
        BookImp bookImp = new BookImp("Clean Code", "Robert Martin", 2010);

        // When - Act


        // Then - Assert
        assertThat(book, is(equalTo(bookImp)));
    }
}

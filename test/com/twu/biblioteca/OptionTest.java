package com.twu.biblioteca;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class OptionTest {
    @Test
    public void shouldPrintOptionNumberAndTitle() {
        // Given - Arrange
        String title = "Find A Book";
        int optionNumber = 1;

        // When - Act
        Option option = new Option(optionNumber, title);

        // Then - Assert
        assertThat(option.toString(), is("1 --> Find A Book"));
    }
}

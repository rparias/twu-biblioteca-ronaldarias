package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

public class MenuTest {

    private PrintStream printStream;
    private BufferedReader bufferedReader;
    private Menu menu;
    private Map<String, Option> options;

    @Before
    public void setUp() throws Exception {
        options = new HashMap<String, Option>();
        printStream = mock(PrintStream.class);
        bufferedReader = mock(BufferedReader.class);
        menu = new Menu(options, printStream, bufferedReader);
    }

    @Test
    public void shouldPrintOneMenuOptionWhenIsOneItem() {
        // Given - Arrange
        Option option = new Option(1, "List of Books");

        // When - Act
        options.put("1", option);
        menu.listOptions();

        // Then - Assert
        verify(printStream).println("1 --> List of Books\n");
    }

    @Test
    public void shouldReturnTitleOfOptionWhenUserInputANumber() throws IOException {
        // Given - Arrange
        Option option = new Option(1, "List of Books");
        options.put("1", option);

        // When - Act
        when(bufferedReader.readLine()).thenReturn("1");
        String title = menu.findOptionFromNumber().getTitleOption();

        // Then - Assert
        assertThat(title, is("List of Books"));
    }

    @Test
    public void shouldReturnNotificationWhenChoseInvalidOption() throws IOException {
        // Given - Arrange
        Option option = new Option(1, "List of Books");
        options.put("1", option);

        // When - Act
        when(bufferedReader.readLine()).thenReturn("0");
        Option selectedOption = menu.findOptionFromNumber();
        String title;

        if (selectedOption != null) {
            title = menu.findOptionFromNumber().getTitleOption();
        } else {
            title = "Please select a valid option";
        }

        // Then - Assert
        assertThat(title, is("Please select a valid option"));
    }
}

package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class MenuTest {

    private PrintStream printStream;
    private BufferedReader bufferedReader;
    private Menu menu;
    private Map<Integer, String> options;

    @Before
    public void setUp() throws Exception {
        options = new HashMap<Integer, String>();
        printStream = mock(PrintStream.class);
        bufferedReader = mock(BufferedReader.class);
        menu = new Menu(options, printStream, bufferedReader);
    }

    @Test
    public void shouldPrintOneMenuOptionWhenIsOneItem() {
        // Given - Arrange
        String option = "List of Books";

        // When - Act
        options.put(1, option);
        menu.listOptions();

        // Then - Assert
        verify(printStream).println("1 --> List of Books\n");
    }

    @Test
    public void shouldReturnTitleOfOptionWhenUserInputANumber() throws IOException {
        // Given - Arrange
        String option = "List of Books";
        options.put(1, option);

        // When - Act
        when(bufferedReader.readLine()).thenReturn("1");
        int number = menu.findOptionFromNumber();

        // Then - Assert
        assertThat(number, is(1));
    }

    @Test
    public void shouldReturnNotificationWhenChoseInvalidOption() throws IOException {
        // Given - Arrange
        String option = "List of Books";
        options.put(1, option);

        // When - Act
        when(bufferedReader.readLine()).thenReturn("0");
        int selectedOption = menu.findOptionFromNumber();
        String title;

        if (selectedOption == 0) {
            title = "Please select a valid option";
        } else {
            title = "";
        }

        // Then - Assert
        assertThat(title, is("Please select a valid option"));
    }
}

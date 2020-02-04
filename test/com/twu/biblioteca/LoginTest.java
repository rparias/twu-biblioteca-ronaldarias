package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.Login;
import com.twu.biblioteca.interfaces.User;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LoginTest {

    private PrintStream printStream;
    private List<User> users;
    private BufferedReader bufferedReader;
    private Login login;

    @Before
    public void setUp() throws Exception {
        printStream = mock(PrintStream.class);
        bufferedReader = mock(BufferedReader.class);
        users = new ArrayList<User>();
        login = new LoginImp(printStream, users, bufferedReader);
    }

    @Test
    public void shouldMatchWithIndicatedFormat() {
        // Given - Arrange
        String libraryNumber = "123-4576";

        // When - Act
        boolean isMatching = login.validateLibraryNumberFormat(libraryNumber);

        // Then - Assert
        assertThat(isMatching, is(true));
    }

    @Test
    public void shouldReturnNullIfLoginIsNotSuccessful() {
        // Given - Arrange
        String libraryNumber = "123-4576";
        String password = "654321";
        User testUser = new Customer("Ronald", "ariasron@hotmail.com", "0987654321", libraryNumber);
        users.add(testUser);

        // When - Act
        User user = login.loginUser(libraryNumber, password);

        // Then - Assert
        assertThat(user, is(nullValue()));
    }

    @Test
    public void shouldReturnUserIfLoginIsSuccessful() {
        // Given - Arrange
        String libraryNumber = "123-4576";
        String password = "123456";
        User testUser = new Customer("Ronald", "ariasron@hotmail.com", "0987654321", libraryNumber);
        users.add(testUser);

        // When - Act
        User user = login.loginUser(libraryNumber, password);

        // Then - Assert
        assertThat(user, is(equalTo(testUser)));
    }

    @Test
    public void shouldPrintErrorMessageIfPasswordOrLibraryNumberIsIncorrect() {
        // Given - Arrange
        String libraryNumber = "123-4576";
        String password = "";
        User testUser = new Customer("Ronald", "ariasron@hotmail.com", "0987654321", libraryNumber);
        users.add(testUser);

        // When - Act
        login.loginUser(libraryNumber, password);

        // Then - Assert
        verify(printStream).println("Wrong library number or password\n");
    }

    @Test
    public void shouldPrintWelcomeMessageIfPasswordAndLibraryNumberIsCorrect() {
        // Given - Arrange
        String libraryNumber = "123-4576";
        String password = "123456";
        User testUser = new Customer("Ronald", "ariasron@hotmail.com", "0987654321", libraryNumber);
        users.add(testUser);

        // When - Act
        login.loginUser(libraryNumber, password);

        // Then - Assert
        verify(printStream).println("Welcome " + testUser.getName() + "\n");
    }

    @Test
    public void shouldReturnTrueIfUserIsAdmin() {
        // Given - Arrange
        User normalUser = new Customer("Ronald", "ariasron@hotmail.com", "0987654321", "123-4567");
        User adminUser = new Customer("Librarian", "librarian@hotmail.com", "0997656789", "999-9999");
        users.add(normalUser);
        users.add(adminUser);

        // When - Act
        boolean isAdmin = login.isAdmin(adminUser);
        boolean isNotAdmin = login.isAdmin(normalUser);

        // Then - Assert
        assertThat(isAdmin, is(true));
        assertThat(isNotAdmin, is(not(true)));
    }
}

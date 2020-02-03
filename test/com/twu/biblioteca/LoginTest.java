package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.Login;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LoginTest {

    @Test
    public void shouldMatchWithIndicatedFormat() {
        // Given - Arrange
        String libraryNumber = "123-4576";
        Login login = new LoginImp();

        // When - Act
        boolean isMatching = login.validateLibraryNumberFormat(libraryNumber);

        // Then - Assert
        assertThat(isMatching, is(true));
    }
}

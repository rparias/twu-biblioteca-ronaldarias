package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.Composition;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MovieTest {

    @Test
    public void shouldPrintNameYearDirectorAndRating() {
        // Given - Arrange
        String name = "Clockwork Orange";
        int year = 1971;
        String director = "Stanley Kubrick";
        int rating = 10;
        Composition movie = new MovieImp(name, year, director, rating);

        // When - Act
        String movieInfo = movie.printCompositionInfo();

        // Then - Assert
        assertThat(movieInfo, is("Clockwork Orange         1971      Stanley Kubrick          10"));
    }

    @Test
    public void shouldBeEqualsMovieImpWithComposition() {
        // Given - Arrange
        Composition movie = new MovieImp("Clockwork Orange", 1971, "Stanley Kubrick", 10);
        MovieImp movieImp = new MovieImp("Clockwork Orange", 1971, "Stanley Kubrick", 10);


        // When - Act


        // Then - Assert
        assertThat(movie, is(equalTo(movieImp)));
    }
}

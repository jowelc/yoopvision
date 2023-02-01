package jcastaneda.yoopvision.controller;

import jcastaneda.yoopvision.model.MovieRequest;
import jcastaneda.yoopvision.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MovieControllerTest {
    MovieController movieController;

    @Mock
    MovieService movieService;

    @BeforeEach
    void setUp() {
        movieController = new MovieController(movieService);
    }

    @Test
    void itShouldGetAllMovies() {
        // When
        movieController.getAllMovies();

        // Then
        verify(movieService).getAllMovies();
    }

    @Test
    void itShouldGetAllMoviesByGenre() {
        // Given
        String genre = "math";

        // When
        movieController.getAllMoviesByGenre(genre);

        // Then
        verify(movieService).getAllMoviesByGenre(genre);
    }

    @Test
    void itShouldCreateMovie() {
        // Given
        MovieRequest movieRequest = MovieRequest.builder()
                .name("testName")
                .genre("testGenre")
                .build();

        // When
        movieController.createMovie(movieRequest);

        // Then
        verify(movieService).createMovie(movieRequest);
    }

}
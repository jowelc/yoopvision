package jcastaneda.yoopvision.service;

import jcastaneda.yoopvision.dto.AverageRatingDTO;
import jcastaneda.yoopvision.dto.MovieDTO;
import jcastaneda.yoopvision.entity.MovieEntity;
import jcastaneda.yoopvision.exception.MovieError;
import jcastaneda.yoopvision.model.MovieRequest;
import jcastaneda.yoopvision.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    MovieService movieService;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        movieService = new MovieService(modelMapper, movieRepository);
    }

    @Test
    void itShouldGetAllMovies() {
        // When
        movieService.getAllMovies();

        // Then
        verify(movieRepository).findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Test
    void itShouldGetAllMoviesByGenre() {
        // Given
        String genre = "math";
        MovieEntity movie1 = MovieEntity.builder()
                .id(1L)
                .name("testName1")
                .averageCriticRating(100.0)
                .genre(genre)
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();
        MovieDTO movie2 = new MovieDTO();
                movie2.setId(1L);
                movie2.setName("testName1");
                movie2.setAverageCriticRating(100.0);
                movie2.setGenre(genre);
        AverageRatingDTO expected = AverageRatingDTO.builder()
                .averageCriticRating("100.0")
                .movies(List.of(movie2))
                .build();

        // When
        when(movieRepository.findAllByGenreIgnoreCase(genre, Sort.by(Sort.Direction.ASC, "id")))
                .thenReturn(List.of(movie1));
        when(modelMapper.map(movie1, MovieDTO.class)).thenReturn(movie2);

        // Then
        assertThat(movieService.getAllMoviesByGenre(genre))
                .isExactlyInstanceOf(AverageRatingDTO.class)
                .isEqualTo(expected);
    }

    @Test
    void itShouldThrowMovieErrorWhenGettingAllMoviesByGenre() {
        // Given
        String genre = "test";

        // When
        given(movieRepository.findAllByGenreIgnoreCase(genre, Sort.by(Sort.Direction.ASC, "id")))
                .willReturn(Collections.emptyList());

        // Then
        assertThatThrownBy(() -> movieService.getAllMoviesByGenre(genre))
                .isInstanceOf(MovieError.class)
                .hasMessageContaining("No movie for this genre.");
    }

    @Test
    void itShouldCreateMovie() {
        // Given
        MovieRequest movie = MovieRequest.builder()
                .name("testName1")
                .genre("math")
                .build();

        // When
        movieService.createMovie(movie);

        // Then
        ArgumentCaptor<MovieEntity> movieEntityArgumentCaptor = ArgumentCaptor.forClass(MovieEntity.class);
        verify(movieRepository).save(movieEntityArgumentCaptor.capture());
        MovieEntity capturedMovieEntity = movieEntityArgumentCaptor.getValue();
        assertThat(capturedMovieEntity.getName()).isEqualTo("testName1");
        assertThat(capturedMovieEntity.getGenre()).isEqualTo("math");
    }

    @Test
    void itShouldThrowMovieErrorWhenCreatingMovie() {
        // Given
        MovieRequest movie = MovieRequest.builder()
                .name("testName1")
                .genre("math")
                .build();
        MovieEntity movie1 = MovieEntity.builder()
                .id(1L)
                .name("testName1")
                .averageCriticRating(100.0)
                .genre("math")
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();
        MovieDTO movie2 = new MovieDTO();
                movie2.setId(1L);
                movie2.setName("testName1");
                movie2.setAverageCriticRating(100.0);
                movie2.setGenre("math");

        // When
        when(movieRepository.findAll(Sort.by(Sort.Direction.ASC, "id")))
                .thenReturn(List.of(movie1));
        when(modelMapper.map(movie1, MovieDTO.class)).thenReturn(movie2);

        // Then
        assertThatThrownBy(() -> movieService.createMovie(movie))
                .isInstanceOf(MovieError.class)
                .hasMessageContaining("Movie name already exist in the system.");
    }
}
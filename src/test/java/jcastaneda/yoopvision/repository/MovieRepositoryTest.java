package jcastaneda.yoopvision.repository;

import jcastaneda.yoopvision.entity.MovieEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    void itShouldFindAllByGenreIgnoreCase() {
        // Given
        MovieEntity movie1 = MovieEntity.builder()
                .name("testName1")
                .averageCriticRating(null)
                .genre("test1")
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();
        movieRepository.save(movie1);

        MovieEntity movie2 = MovieEntity.builder()
                .name("testName2")
                .averageCriticRating(null)
                .genre("test1")
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();
        movieRepository.save(movie2);

        MovieEntity movie3 = MovieEntity.builder()
                .name("testName3")
                .averageCriticRating(null)
                .genre("test2")
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();
        movieRepository.save(movie3);

        // When
        List<MovieEntity> mathMovies = movieRepository.findAllByGenreIgnoreCase("test1", Sort.by(Sort.Direction.ASC, "id"));
        List<MovieEntity> scienceMovies = movieRepository.findAllByGenreIgnoreCase("test2", Sort.by(Sort.Direction.ASC, "id"));


        // Then
        assertThat(mathMovies).asList().hasSize(2);
        assertThat(mathMovies).asList().contains(movie1);
        assertThat(mathMovies).asList().contains(movie2);
        assertThat(mathMovies).asList().doesNotContain(movie3);

        assertThat(scienceMovies).asList().hasSize(1);
        assertThat(scienceMovies).asList().doesNotContain(movie1);
        assertThat(scienceMovies).asList().doesNotContain(movie2);
        assertThat(scienceMovies).asList().contains(movie3);
    }
}
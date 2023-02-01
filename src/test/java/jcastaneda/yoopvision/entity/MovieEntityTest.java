package jcastaneda.yoopvision.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MovieEntityTest {

    @Test
    public void isShouldReturnMovieEntity() {
        LocalDateTime localDateTime = LocalDateTime.now();

        MovieEntity movie = new MovieEntity();
        movie.setId(1L);
        movie.setName("testName");
        movie.setAverageCriticRating(100.0);
        movie.setGenre("math");
        movie.setCreatedDate(localDateTime);
        movie.setModifiedDate(localDateTime);

        assertThat(movie.getId()).isEqualTo(1L);
        assertThat(movie.getName()).isEqualTo("testName");
        assertThat(movie.getAverageCriticRating()).isEqualTo(100.0);
        assertThat(movie.getGenre()).isEqualTo("math");
        assertThat(movie.getCreatedDate()).isEqualTo(localDateTime);
        assertThat(movie.getModifiedDate()).isEqualTo(localDateTime);
    }
}
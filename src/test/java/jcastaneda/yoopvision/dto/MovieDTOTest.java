package jcastaneda.yoopvision.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MovieDTOTest {

    @Test
    public void isShouldReturnMovieDTO() {
        MovieDTO movie = new MovieDTO();
        movie.setId(1L);
        movie.setName("testName");
        movie.setAverageCriticRating(100.0);
        movie.setGenre("math");

        assertThat(movie.getId()).isEqualTo(1L);
        assertThat(movie.getName()).isEqualTo("testName");
        assertThat(movie.getAverageCriticRating()).isEqualTo(100.0);
        assertThat(movie.getGenre()).isEqualTo("math");
    }
}
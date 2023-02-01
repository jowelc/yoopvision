package jcastaneda.yoopvision.dto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AverageRatingDTOTest {

    @Test
    public void isShouldReturnAverageRatingDTO() {
        // Given
        MovieDTO movie = new MovieDTO();
        movie.setId(1L);
        movie.setName("testName");
        movie.setAverageCriticRating(null);
        movie.setGenre("math");
        List<MovieDTO> allMovies = new ArrayList<>();
        allMovies.add(movie);

        AverageRatingDTO averageRatingDTO = new AverageRatingDTO(null, allMovies);
        assertThat(averageRatingDTO).isEqualTo(AverageRatingDTO.builder()
                .averageCriticRating(null)
                .movies(allMovies)
                .build());

    }
}
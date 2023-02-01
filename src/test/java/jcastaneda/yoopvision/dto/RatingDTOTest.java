package jcastaneda.yoopvision.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RatingDTOTest {

    @Test
    public void isShouldReturnRatingDTO() {
        RatingDTO rating = new RatingDTO();
        rating.setId(1L);
        rating.setScore(100);
        rating.setMovieId(1L);
        rating.setSubmittedBy("testName");

        assertThat(rating.getId()).isEqualTo(1L);
        assertThat(rating.getScore()).isEqualTo(100);
        assertThat(rating.getMovieId()).isEqualTo(1L);
        assertThat(rating.getSubmittedBy()).isEqualTo("testName");
    }

}
package jcastaneda.yoopvision.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RatingEntityTest {

    @Test
    public void isShouldReturnMovieEntity() {
        LocalDateTime localDateTime = LocalDateTime.now();

        RatingEntity rating = new RatingEntity();
        rating.setId(1L);
        rating.setScore(100);
        rating.setMovieId(1L);
        rating.setSubmittedBy("testName");
        rating.setCreatedDate(localDateTime);
        rating.setModifiedDate(localDateTime);

        assertThat(rating.getId()).isEqualTo(1L);
        assertThat(rating.getScore()).isEqualTo(100);
        assertThat(rating.getMovieId()).isEqualTo(1L);
        assertThat(rating.getSubmittedBy()).isEqualTo("testName");
        assertThat(rating.getCreatedDate()).isEqualTo(localDateTime);
        assertThat(rating.getModifiedDate()).isEqualTo(localDateTime);
    }

}
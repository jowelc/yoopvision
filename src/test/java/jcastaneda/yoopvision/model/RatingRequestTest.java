package jcastaneda.yoopvision.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RatingRequestTest {

    @Test
    public void isShouldReturnRatingRequest() {
        RatingRequest ratingRequest = new RatingRequest(100, 1L, "testName");
        assertThat(ratingRequest).isEqualTo(RatingRequest.builder()
                .score(100)
                .movieId(1L)
                .submittedBy("testName")
                .build());

    }
}
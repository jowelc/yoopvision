package jcastaneda.yoopvision.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MovieRequestTest {

    @Test
    public void isShouldReturnMovieRequest() {
        // Given
        MovieRequest movie = new MovieRequest("testName", "math");

        // Then
        assertThat(movie).isEqualTo(MovieRequest.builder().name("testName").genre("math").build());

    }
}
package jcastaneda.yoopvision.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AppExceptionHandlerTest {

    private final AppExceptionHandler handler = new AppExceptionHandler();

    @Test
    void handlerMovieError() {
        final ResponseEntity<Object> handled = handler.handlerMovieError(new MovieError("TEST"));
        assertThat(handled.getBody()).isExactlyInstanceOf(ApiError.class);
    }

    @Test
    void handlerRatingError() {
        final ResponseEntity<Object> handled = handler.handlerRatingError(new RatingError("TEST"));
        assertThat(handled.getBody()).isExactlyInstanceOf(ApiError.class);
    }
}
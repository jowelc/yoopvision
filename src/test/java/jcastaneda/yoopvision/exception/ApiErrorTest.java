package jcastaneda.yoopvision.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ApiErrorTest {

    ApiError apiError;

    @Test
    void itShouldCreateMovie() {
        // Given
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        apiError = new ApiError("testMessage", HttpStatus.BAD_REQUEST, zonedDateTime);

        assertThat(apiError.getMessage()).isEqualTo("testMessage");
        assertThat(apiError.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(apiError.getTimestamp()).isEqualTo(zonedDateTime);

        apiError.setMessage("test");
        assertThat(apiError).isEqualTo(new ApiError("test", HttpStatus.BAD_REQUEST, zonedDateTime));

    }

}
package jcastaneda.yoopvision.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MovieError.class)
    public ResponseEntity<Object> handlerMovieError(MovieError exception) {
        return new ResponseEntity<>(new ApiError(exception.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RatingError.class)
    public ResponseEntity<Object> handlerRatingError(RatingError exception) {
        return new ResponseEntity<>(new ApiError(exception.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now()), HttpStatus.BAD_REQUEST);
    }
}
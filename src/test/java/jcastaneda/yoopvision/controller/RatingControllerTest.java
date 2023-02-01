package jcastaneda.yoopvision.controller;

import jcastaneda.yoopvision.model.RatingRequest;
import jcastaneda.yoopvision.service.RatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RatingControllerTest {

    RatingController ratingController;

    @Mock
    RatingService ratingService;

    @BeforeEach
    void setUp() {
        ratingController = new RatingController(ratingService);
    }

    @Test
    void itShouldGetAllRatings() {
        // When
        ratingController.getAllRatings();

        // Then
        verify(ratingService).getAllRatings();
    }

    @Test
    void itShouldGetAllRatingsById() {
        // Given
        Long id = 1L;

        // When
        ratingController.getAllRatingsById(id);

        // Then
        verify(ratingService).getAllRatingsById(id);
    }

    @Test
    void itShouldCreateRating() {
        // Given
        RatingRequest ratingRequest = RatingRequest.builder()
                .score(100)
                .movieId(1L)
                .submittedBy("testName")
                .build();

        // When
        ratingController.createRating(ratingRequest);

        // Then
        verify(ratingService).createRating(ratingRequest);
    }
}
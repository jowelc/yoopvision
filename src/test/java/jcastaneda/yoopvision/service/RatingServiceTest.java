package jcastaneda.yoopvision.service;

import jcastaneda.yoopvision.dto.RatingDTO;
import jcastaneda.yoopvision.entity.MovieEntity;
import jcastaneda.yoopvision.entity.RatingEntity;
import jcastaneda.yoopvision.exception.MovieError;
import jcastaneda.yoopvision.exception.RatingError;
import jcastaneda.yoopvision.model.RatingRequest;
import jcastaneda.yoopvision.repository.MovieRepository;
import jcastaneda.yoopvision.repository.RatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RatingServiceTest {

    RatingService ratingService;

    @Mock
    private RatingRepository ratingRepository;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        ratingService = new RatingService(modelMapper, ratingRepository, movieRepository);
    }

    @Test
    void itShouldGetAllRatings() {
        // When
        ratingService.getAllRatings();

        // Then
        verify(ratingRepository).findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Test
    void itShouldGetAllRatingsById() {
        // Given
        Long movieId = 1L;
        RatingEntity ratingEntity1 = RatingEntity.builder()
                .score(100)
                .movieId(movieId)
                .submittedBy("testName1")
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();
        RatingEntity ratingEntity2 = RatingEntity.builder()
                .score(80)
                .movieId(movieId)
                .submittedBy("testName2")
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();
        List<RatingEntity> allRatings = new ArrayList<>();
            allRatings.add(ratingEntity1);
            allRatings.add(ratingEntity2);
        RatingDTO ratingDTO1 = new RatingDTO();
            ratingDTO1.setId(ratingEntity1.getMovieId());
            ratingDTO1.setScore(ratingEntity1.getScore());
            ratingDTO1.setMovieId(ratingEntity1.getMovieId());
            ratingDTO1.setSubmittedBy(ratingEntity1.getSubmittedBy());
        RatingDTO ratingDTO2 = new RatingDTO();
            ratingDTO2.setId(ratingEntity1.getMovieId());
            ratingDTO2.setScore(ratingEntity1.getScore());
            ratingDTO2.setMovieId(ratingEntity1.getMovieId());
            ratingDTO2.setSubmittedBy(ratingEntity1.getSubmittedBy());
        List<RatingDTO> allRatingsDTO = new ArrayList<>();
            allRatingsDTO.add(ratingDTO1);
            allRatingsDTO.add(ratingDTO2);

        // When
        ratingService.getAllRatingsById(movieId);
        when(ratingRepository.findAllByMovieId(movieId, Sort.by(Sort.Direction.ASC, "id"))).thenReturn(allRatings);
        when(modelMapper.map(ratingEntity1, RatingDTO.class)).thenReturn(ratingDTO1);
        when(modelMapper.map(ratingEntity2, RatingDTO.class)).thenReturn(ratingDTO2);

        // Then
        assertThat(ratingService.getAllRatingsById(movieId)).isEqualTo(allRatingsDTO);
    }

    @Test
    void itShouldThrowMovieErrorWhenCreatingRating() {
        // Given
        Long movieId = 1L;
        RatingRequest ratingRequest = RatingRequest.builder()
                .score(100)
                .movieId(movieId)
                .submittedBy("testName1")
                .build();

        // When
        when(movieRepository.findById(movieId)).thenReturn(Optional.empty());

        // Then
        assertThatThrownBy(() -> ratingService.createRating(ratingRequest))
                .isInstanceOf(MovieError.class)
                .hasMessageContaining("Movie doesn't exist in the system.");
    }

    @Test
    void itShouldThrowRatingErrorWhenCreatingRating() {
        // Given
        Long movieId = 1L;
        RatingRequest ratingRequest1 = RatingRequest.builder()
                .score(0)
                .movieId(movieId)
                .submittedBy("testName1")
                .build();
        RatingRequest ratingRequest2 = RatingRequest.builder()
                .score(101)
                .movieId(movieId)
                .submittedBy("testName1")
                .build();
        MovieEntity movie = MovieEntity.builder()
                .id(movieId)
                .name("testName1")
                .averageCriticRating(100.0)
                .genre("math")
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();

        // When
        when(movieRepository.findById(movieId)).thenReturn(Optional.ofNullable(movie));

        // Then
        assertThatThrownBy(() -> ratingService.createRating(ratingRequest1))
                .isInstanceOf(RatingError.class)
                .hasMessageContaining("Rating should be integer ranging from 1 to 100.");
        assertThatThrownBy(() -> ratingService.createRating(ratingRequest2))
                .isInstanceOf(RatingError.class)
                .hasMessageContaining("Rating should be integer ranging from 1 to 100.");
    }

    @Test
    void createRating() {
        // Given
        Long movieId = 1L;
        RatingRequest ratingRequest = RatingRequest.builder()
                .score(100)
                .movieId(movieId)
                .submittedBy("testName1")
                .build();
        MovieEntity movie = MovieEntity.builder()
                .id(movieId)
                .name("testName1")
                .averageCriticRating(100.0)
                .genre("math")
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();
        RatingEntity ratingEntity = RatingEntity.builder()
                .score(ratingRequest.getScore())
                .movieId(ratingRequest.getMovieId())
                .submittedBy(ratingRequest.getSubmittedBy())
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();
        RatingDTO ratingDTO = new RatingDTO();
            ratingDTO.setId(ratingRequest.getMovieId());
            ratingDTO.setScore(ratingRequest.getScore());
            ratingDTO.setMovieId(ratingRequest.getMovieId());
            ratingDTO.setSubmittedBy(ratingRequest.getSubmittedBy());
        List<RatingEntity> ratings = new ArrayList<>();
            ratings.add(ratingEntity);

        // When
        when(movieRepository.findById(movieId)).thenReturn(Optional.ofNullable(movie));
        when(ratingRepository.save(any())).thenReturn(ratingEntity);
        when(ratingRepository.findAllByMovieId(ratingRequest.getMovieId(), Sort.by(Sort.Direction.ASC, "id"))).thenReturn(ratings);
        when(modelMapper.map(ratingEntity, RatingDTO.class)).thenReturn(ratingDTO);

        // Then
        assertThat(ratingService.createRating(ratingRequest)).isEqualTo(ratingDTO);
    }
}
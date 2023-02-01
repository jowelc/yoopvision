package jcastaneda.yoopvision.repository;

import jcastaneda.yoopvision.entity.RatingEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class RatingRepositoryTest {

    @Autowired
    private RatingRepository ratingRepository;

    @Test
    void itShouldFindAllByMovieId() {
        // Given
        long movieId = 1;
        RatingEntity rating1 = RatingEntity.builder()
                .score(100)
                .movieId(movieId)
                .submittedBy("testUser1")
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();
        ratingRepository.save(rating1);

        RatingEntity rating2 = RatingEntity.builder()
                .score(80)
                .movieId(movieId)
                .submittedBy("testUser2")
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();
        ratingRepository.save(rating2);

        // When
        List<RatingEntity> expected = ratingRepository.findAllByMovieId(movieId, Sort.by(Sort.Direction.ASC, "id"));

        // Then
        assertThat(expected).asList().contains(rating1);
        assertThat(expected).asList().contains(rating2);


    }
}
package jcastaneda.yoopvision.service;

import jcastaneda.yoopvision.dto.MovieDTO;
import jcastaneda.yoopvision.dto.RatingDTO;
import jcastaneda.yoopvision.entity.MovieEntity;
import jcastaneda.yoopvision.entity.RatingEntity;
import jcastaneda.yoopvision.exception.MovieError;
import jcastaneda.yoopvision.exception.RatingError;
import jcastaneda.yoopvision.model.RatingRequest;
import jcastaneda.yoopvision.repository.MovieRepository;
import jcastaneda.yoopvision.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final ModelMapper modelMapper;
    private final RatingRepository ratingRepository;
    private final MovieRepository movieRepository;

    public List<RatingDTO> getAllRatings() {
        List<RatingEntity> allRatings = ratingRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return convertRatingsToDTO(allRatings);
    }

    @Transactional
    public RatingDTO createRating(RatingRequest ratingRequest) {
        // Check if movie exist
        Optional<MovieEntity> movie = movieRepository.findById(ratingRequest.getMovieId());
        if (movie.isEmpty()) throw new MovieError("Movie doesn't exist in the system.");

        // Check if score is integer ranging from 1 to 100
        if (ratingRequest.getScore() < 1 || ratingRequest.getScore() > 100)
            throw new RatingError("Rating should be integer ranging from 1 to 100.");

        // Add to database
        RatingEntity ratingEntity = ratingRepository.save(RatingEntity.builder()
                .score(ratingRequest.getScore())
                .movieId(ratingRequest.getMovieId())
                .submittedBy(ratingRequest.getSubmittedBy())
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build());

        // Update movie rating
        List<RatingEntity> ratings = ratingRepository.findAllByMovieId(ratingRequest.getMovieId(), Sort.by(Sort.Direction.ASC, "id"));
        OptionalDouble average = ratings.stream()
                .mapToInt(RatingEntity::getScore)
                .average();

        if (Objects.equals(movie.get().getGenre(), "transactional")) throw new RatingError("Transactional Error!!");

        movieRepository.save(MovieEntity.builder()
                .id(movie.get().getId())
                .name(movie.get().getName())
                .averageCriticRating(average.orElseThrow(IllegalStateException::new))
                .genre(movie.get().getGenre())
                .createdDate(movie.get().getCreatedDate())
                .modifiedDate(LocalDateTime.now())
                .build());

        return modelMapper.map(ratingEntity, RatingDTO.class);

    }

    public List<RatingDTO> getAllRatingsById(Long Id) {
        // Get all ratings from database
        return convertRatingsToDTO(ratingRepository.findAllByMovieId(Id, Sort.by(Sort.Direction.ASC, "id")));
    }

    private List<RatingDTO> convertRatingsToDTO(List<RatingEntity> allRatings) {
        List<RatingDTO> allRatingsDTO =  new ArrayList<>();

        for (RatingEntity rating : allRatings) {
            allRatingsDTO.add(modelMapper.map(rating, RatingDTO.class));
        }

        return allRatingsDTO;
    }
}

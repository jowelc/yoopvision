package jcastaneda.yoopvision.service;

import jcastaneda.yoopvision.dto.AverageRatingDTO;
import jcastaneda.yoopvision.dto.MovieDTO;
import jcastaneda.yoopvision.entity.MovieEntity;
import jcastaneda.yoopvision.exception.MovieError;
import jcastaneda.yoopvision.model.MovieRequest;
import jcastaneda.yoopvision.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final ModelMapper modelMapper;
    private final MovieRepository movieRepository;

    public List<MovieDTO> getAllMovies() {
        List<MovieEntity> allMovies = movieRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return convertMoviesToDTO(allMovies);
    }

    public List<MovieDTO> createMovie(MovieRequest movieRequest) {
        // Check if movie already exist
        boolean movieExist = getAllMovies().stream().anyMatch(movie -> movie.getName().equalsIgnoreCase(movieRequest.getName()));
        if (movieExist) throw new MovieError("Movie name already exist in the system.");

        // Add to database
        movieRepository.save(MovieEntity.builder()
                .name(movieRequest.getName())
                .averageCriticRating(null)
                .genre(movieRequest.getGenre())
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build());

        return getAllMovies();
    }

    public AverageRatingDTO getAllMoviesByGenre(String genre) {
        // Get all movies from database with the given genre
        List<MovieDTO> movies = convertMoviesToDTO(movieRepository.findAllByGenreIgnoreCase(genre, Sort.by(Sort.Direction.ASC, "id")));

        // Check Movies
        if (movies.isEmpty()) throw new MovieError("No movie for this genre.");

        // Get average ratings for the given genre
        OptionalDouble average = movies.stream()
                .filter(movie -> movie.getAverageCriticRating() != null)
                .mapToDouble(MovieDTO::getAverageCriticRating)
                .average();

        return AverageRatingDTO.builder()
                .averageCriticRating(average.isEmpty() ? "No rating yet for this genre." : String.valueOf(average.getAsDouble()))
                .movies(movies)
                .build();
    }

    private List<MovieDTO> convertMoviesToDTO(List<MovieEntity> allMovies) {
        List<MovieDTO> allMoviesDTO =  new ArrayList<>();

        for (MovieEntity movie : allMovies) {
            allMoviesDTO.add(modelMapper.map(movie, MovieDTO.class));
        }

        return allMoviesDTO;
    }

}

package jcastaneda.yoopvision.controller;

import jcastaneda.yoopvision.dto.AverageRatingDTO;
import jcastaneda.yoopvision.dto.MovieDTO;
import jcastaneda.yoopvision.model.MovieRequest;
import jcastaneda.yoopvision.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public List<MovieDTO> getAllMovies() {
        return movieService.getAllMovies();
    }

    @PostMapping
    public List<MovieDTO> createMovie(@RequestBody MovieRequest movieRequest) {
        return movieService.createMovie(movieRequest);
    }

    @GetMapping("/genre/{genre}")
    public AverageRatingDTO getAllMoviesByGenre(@PathVariable String genre) {
        return movieService.getAllMoviesByGenre(genre);
    }
}

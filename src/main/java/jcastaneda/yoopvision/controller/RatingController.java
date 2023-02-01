package jcastaneda.yoopvision.controller;

import jcastaneda.yoopvision.dto.RatingDTO;
import jcastaneda.yoopvision.model.RatingRequest;
import jcastaneda.yoopvision.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ratings")
public class RatingController {
    private final RatingService ratingService;

    @GetMapping
    public List<RatingDTO> getAllRatings() {
        return ratingService.getAllRatings();
    }

    @PutMapping
    public RatingDTO createRating(@RequestBody RatingRequest ratingRequest) {
        return ratingService.createRating(ratingRequest);
    }

    @GetMapping("/movie/{id}")
    public List<RatingDTO> getAllRatingsById(@PathVariable Long id) {
        return ratingService.getAllRatingsById(id);
    }
}

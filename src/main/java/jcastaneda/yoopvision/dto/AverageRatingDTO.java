package jcastaneda.yoopvision.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
@Builder
public class AverageRatingDTO {
    @Nullable
    private String averageCriticRating;
    private List<MovieDTO> movies;
}

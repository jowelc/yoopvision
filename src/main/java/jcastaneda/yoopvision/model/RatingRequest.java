package jcastaneda.yoopvision.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RatingRequest {
    private int score;
    private Long movieId;
    private String submittedBy;
}

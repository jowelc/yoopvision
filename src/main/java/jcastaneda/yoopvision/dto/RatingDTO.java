package jcastaneda.yoopvision.dto;

import lombok.Data;

@Data
public class RatingDTO {
    private Long id;
    private int score;
    private Long movieId;
    private String submittedBy;
}

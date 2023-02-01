package jcastaneda.yoopvision.dto;

import lombok.Data;

@Data
public class MovieDTO {
    private Long id;
    private String name;
    private Double averageCriticRating;
    private String genre;
}

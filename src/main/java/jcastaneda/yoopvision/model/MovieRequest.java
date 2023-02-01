package jcastaneda.yoopvision.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieRequest {
    private String name;
    private String genre;
}

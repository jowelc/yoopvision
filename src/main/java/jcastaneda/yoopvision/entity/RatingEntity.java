package jcastaneda.yoopvision.entity;

import jakarta.persistence.*;
import jcastaneda.yoopvision.config.SchemaConfiguration;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = SchemaConfiguration.SCHEMA_NAME, name = SchemaConfiguration.RATINGS_TABLE)
public class RatingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int score;
    private Long movieId;
    private String submittedBy;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}

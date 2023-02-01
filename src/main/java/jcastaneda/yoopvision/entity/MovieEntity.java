package jcastaneda.yoopvision.entity;

import jakarta.persistence.*;
import jcastaneda.yoopvision.config.SchemaConfiguration;
import lombok.*;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = SchemaConfiguration.SCHEMA_NAME, name = SchemaConfiguration.MOVIES_TABLE)
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Nullable
    private Double averageCriticRating;
    private String genre;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}

package jcastaneda.yoopvision.repository;

import jcastaneda.yoopvision.entity.RatingEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RatingRepository extends JpaRepository<RatingEntity, Long> {
    List<RatingEntity> findAllByMovieId(Long movieId, Sort sort);
}

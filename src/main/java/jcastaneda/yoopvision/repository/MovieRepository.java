package jcastaneda.yoopvision.repository;

import jcastaneda.yoopvision.entity.MovieEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    List<MovieEntity> findAllByGenreIgnoreCase(String genre, Sort sort);
}

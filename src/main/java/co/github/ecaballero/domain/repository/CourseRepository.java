package co.github.ecaballero.domain.repository;

import co.github.ecaballero.domain.models.CourseModel;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {

  CourseModel save(CourseModel course);

  Optional<CourseModel> findByCourseId(Long courseId);

  List<CourseModel> findAll();

  boolean existsByCourseId(Long courseId);

  boolean existsByCode(String code);

  void delete(Long courseId);

  Optional<CourseModel> update(CourseModel course);
}
package co.github.ecaballero.application.service;

import co.github.ecaballero.domain.models.CourseModel;

import java.util.List;
import java.util.Optional;

public interface CourseService {
  Optional<CourseModel> update(CourseModel course);

  boolean existsByCourseId(Long courseId);

  CourseModel save(CourseModel course);

  void delete(Long courseId);

  Optional<CourseModel> findByCourseId(Long courseId);

  List<CourseModel> findAll();
}

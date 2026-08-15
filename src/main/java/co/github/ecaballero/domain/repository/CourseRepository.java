package main.java.co.github.ecaballero.domain.repository;

import main.java.co.github.ecaballero.domain.models.CourseModel;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {

  List<CourseModel> findAll();

  Optional<CourseModel> findByCourseId(Long courseId);

  void delete(Long courseId);

  CourseModel save(CourseModel course);

  boolean existsByCourseId(Long courseId);

  Optional<CourseModel> update(CourseModel course);

}

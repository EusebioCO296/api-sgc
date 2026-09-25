package co.github.ecaballero.application.service;

import co.github.ecaballero.application.dto.CreateCourseDto;
import co.github.ecaballero.application.dto.UpdateCourseDto;
import co.github.ecaballero.domain.models.CourseModel;

import java.util.List;
import java.util.Optional;

public interface CourseService {
  CourseModel create(CreateCourseDto course);

  CourseModel findByCourseId(Long courseId);

  List<CourseModel> findAll();

  CourseModel update(Long courseId, UpdateCourseDto course);

  void delete(Long courseId);

  boolean existsByCourseId(Long courseId);

  boolean existsByCode(String code);
}

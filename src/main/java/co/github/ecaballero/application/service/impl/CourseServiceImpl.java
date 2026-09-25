package co.github.ecaballero.application.service.impl;

import co.github.ecaballero.application.dto.CreateCourseDto;
import co.github.ecaballero.application.dto.UpdateCourseDto;
import co.github.ecaballero.application.service.CourseService;
import co.github.ecaballero.domain.exception.CourseCodeAlreadyExistsException;
import co.github.ecaballero.domain.exception.CourseNotFoundException;
import co.github.ecaballero.domain.exception.ResourceAlreadyExistsException;
import co.github.ecaballero.domain.models.CourseModel;
import co.github.ecaballero.domain.repository.CourseRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

  private final CourseRepository courseRepository;

  public CourseServiceImpl(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  @Override
  @Transactional
  public CourseModel create(CreateCourseDto dto) {

    if (courseRepository.existsByCode(dto.code())) {
      throw new CourseCodeAlreadyExistsException(
          "Course code already exists: " + dto.code()
      );
    }

    CourseModel course = new CourseModel();

    course.setCode(dto.code());
    course.setName(dto.name());
    course.setDescription(dto.description());
    course.setMaxCapacity(dto.maxCapacity());

    return courseRepository.save(course);
  }

  @Override
  @Transactional(readOnly = true)
  public CourseModel findByCourseId(Long courseId) {

    validateId(courseId);

    return courseRepository.findByCourseId(courseId)
        .orElseThrow(() ->
            new CourseNotFoundException(
                "Course not found with id: " + courseId
            )
        );
  }

  @Override
  @Transactional(readOnly = true)
  public List<CourseModel> findAll() {

    List<CourseModel> courses =
        courseRepository.findAll();

    if (courses.isEmpty()) {
      throw new CourseNotFoundException(
          "No courses found"
      );
    }

    return courses;
  }

  @Override
  @Transactional
  public CourseModel update(
      Long courseId,
      UpdateCourseDto dto) {

    validateId(courseId);

    CourseModel existingCourse =
        courseRepository.findByCourseId(courseId)
            .orElseThrow(() ->
                new CourseNotFoundException(
                    "Course not found with id: "
                        + courseId
                ));

    existingCourse.setCode(dto.code());
    existingCourse.setName(dto.name());
    existingCourse.setDescription(dto.description());
    existingCourse.setMaxCapacity(dto.maxCapacity());

    return courseRepository.update(existingCourse)
        .orElseThrow(() ->
            new CourseNotFoundException(
                "Course not found with id: "
                    + courseId
            ));
  }

  @Override
  @Transactional
  public void delete(Long courseId) {

    validateId(courseId);

    if (!courseRepository.existsByCourseId(courseId)) {
      throw new CourseNotFoundException(
          "Course not found with id: " + courseId
      );
    }

    courseRepository.delete(courseId);
  }

  @Override
  @Transactional(readOnly = true)
  public boolean existsByCourseId(Long courseId) {

    validateId(courseId);

    return courseRepository.existsByCourseId(courseId);
  }

  @Override
  @Transactional(readOnly = true)
  public boolean existsByCode(String code) {

    if (code == null || code.isBlank()) {
      return false;
    }

    return courseRepository.existsByCode(code);
  }

  private void validateId(Long id) {

    if (id == null || id <= 0) {
      throw new IllegalArgumentException(
          "Invalid course id"
      );
    }
  }
}
package co.github.ecaballero.application.service.impl;

import co.github.ecaballero.domain.exception.CourseNotFoundException;
import co.github.ecaballero.application.service.CourseService;
import co.github.ecaballero.domain.models.CourseModel;
import co.github.ecaballero.domain.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

public class CourseServiceImpl implements CourseService {

  private final CourseRepository courseRepository;

  public CourseServiceImpl(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  @Override
  public Optional<CourseModel> update(CourseModel course) {
    if (courseRepository.existsByCourseId(course.getId())) {
      throw new RuntimeException("Course already exists" + course.getId());
    }
    return courseRepository.update(course);
  }

  @Override
  public boolean existsByCourseId(Long courseId) {
    if (courseRepository.findByCourseId(courseId).isEmpty()) {
      throw new CourseNotFoundException("Course not found" + courseId);
    }
    return courseRepository.existsByCourseId(courseId);
  }

  @Override
  public CourseModel save(CourseModel course) {
    if (courseRepository.existsByCourseId(course.getId())) {
      throw new RuntimeException("Courser with id " + course.getId() + " already exists");
    }
    return courseRepository.save(course);
  }

  @Override
  public void delete(Long courseId) {
    if(courseRepository.existsByCourseId(courseId)) {
      throw new RuntimeException("Course with id " + courseId + " already exists");
    }
    courseRepository.delete(courseId);
  }

  @Override
  public Optional<CourseModel> findByCourseId(Long courseId) {
    if(courseRepository.findByCourseId(courseId).isEmpty()) {
      throw new CourseNotFoundException("Course with id: " + courseId + " not found");
    }
    return courseRepository.findByCourseId(courseId);
  }

  @Override
  public List<CourseModel> findAll() {
    if(courseRepository.findAll().isEmpty()) {
      throw new RuntimeException("Course not found");
    }
    return courseRepository.findAll();
  }
}

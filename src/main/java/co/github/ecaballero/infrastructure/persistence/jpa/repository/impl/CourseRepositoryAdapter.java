package co.github.ecaballero.infrastructure.persistence.jpa.repository.impl;

import co.github.ecaballero.domain.models.CourseModel;
import co.github.ecaballero.domain.repository.CourseRepository;
import co.github.ecaballero.infrastructure.persistence.jpa.entity.CourseEntity;
import co.github.ecaballero.infrastructure.persistence.jpa.mapper.CourseMapper;
import co.github.ecaballero.infrastructure.persistence.jpa.repository.JpaCourseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepositoryAdapter implements CourseRepository {

  private final JpaCourseRepository courseJpaRepository;
  private final CourseMapper courseMapper;

  public CourseRepositoryAdapter(
      JpaCourseRepository courseJpaRepository,
      CourseMapper courseMapper) {

    this.courseJpaRepository = courseJpaRepository;
    this.courseMapper = courseMapper;
  }

  @Override
  public CourseModel save(CourseModel course) {

    CourseEntity entity =
        courseMapper.toEntity(course);

    CourseEntity savedEntity =
        courseJpaRepository.save(entity);

    return courseMapper.toModel(savedEntity);
  }

  @Override
  public Optional<CourseModel> findByCourseId(Long courseId) {

    return courseJpaRepository.findById(courseId)
        .map(courseMapper::toModel);
  }

  @Override
  public List<CourseModel> findAll() {

    return courseJpaRepository.findAll()
        .stream()
        .map(courseMapper::toModel)
        .toList();
  }

  @Override
  public boolean existsByCourseId(Long courseId) {

    return courseJpaRepository.existsById(courseId);
  }

  @Override
  public boolean existsByCode(String code) {

    return courseJpaRepository.existsByCode(code);
  }

  @Override
  public void delete(Long courseId) {

    courseJpaRepository.deleteById(courseId);
  }

  @Override
  public Optional<CourseModel> update(CourseModel course) {

    if (course == null || course.getId() == null) {
      return Optional.empty();
    }

    return courseJpaRepository.findById(course.getId())
      .map(entity -> {
      entity.setCode(course.getCode());
      entity.setName(course.getName());
      entity.setDescription(course.getDescription());
      entity.setMaxCapacity(course.getMaxCapacity());

      CourseEntity updatedEntity = courseJpaRepository.save(entity);

      return courseMapper.toModel(updatedEntity);
    });
  }
}
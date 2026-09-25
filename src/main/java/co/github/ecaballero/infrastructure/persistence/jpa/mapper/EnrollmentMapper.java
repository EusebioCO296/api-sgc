package co.github.ecaballero.infrastructure.persistence.jpa.mapper;

import co.github.ecaballero.domain.models.EnrollmentModel;
import co.github.ecaballero.infrastructure.persistence.jpa.entity.CourseEntity;
import co.github.ecaballero.infrastructure.persistence.jpa.entity.EnrollmentEntity;
import co.github.ecaballero.infrastructure.persistence.jpa.entity.StudentEntity;
import co.github.ecaballero.infrastructure.persistence.jpa.repository.JpaCourseRepository;
import co.github.ecaballero.infrastructure.persistence.jpa.repository.JpaStudentRepository;

import org.springframework.stereotype.Component;

@Component
public class EnrollmentMapper {

  private final JpaStudentRepository studentJpaRepository;
  private final JpaCourseRepository courseJpaRepository;

  public EnrollmentMapper(
      JpaStudentRepository studentJpaRepository,
      JpaCourseRepository courseJpaRepository) {

    this.studentJpaRepository = studentJpaRepository;
    this.courseJpaRepository = courseJpaRepository;
  }

  public EnrollmentModel toModel(
      EnrollmentEntity entity) {

    if (entity == null) {
      return null;
    }

    return new EnrollmentModel(
        entity.getId(),
        entity.getStudent().getId(),
        entity.getCourse().getId(),
        entity.getEnrollmentDate(),
        entity.getStatus()
    );
  }

  public EnrollmentEntity toEntity(
      EnrollmentModel model) {

    if (model == null) {
      return null;
    }

    StudentEntity student =
        studentJpaRepository.findById(
            model.getStudentId()
        ).orElseThrow(() ->
            new IllegalArgumentException(
                "Student not found: "
                    + model.getStudentId()
            )
        );

    CourseEntity course =
        courseJpaRepository.findById(
            model.getCourseId()
        ).orElseThrow(() ->
            new IllegalArgumentException(
                "Course not found: "
                    + model.getCourseId()
            )
        );

    return new EnrollmentEntity(
        model.getId(),
        student,
        course,
        model.getEnrollmentDate(),
        model.getStatus()
    );
  }
}
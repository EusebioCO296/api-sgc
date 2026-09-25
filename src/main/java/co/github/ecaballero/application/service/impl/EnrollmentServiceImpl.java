package co.github.ecaballero.application.service.impl;

import co.github.ecaballero.application.dto.CreateEnrollmentDto;
import co.github.ecaballero.application.dto.UpdateEnrollmentDto;
import co.github.ecaballero.application.service.EnrollmentService;
import co.github.ecaballero.domain.exception.CourseCapacityExceededException;
import co.github.ecaballero.domain.exception.CourseNotFoundException;
import co.github.ecaballero.domain.exception.EnrollmentAlreadyExistsException;
import co.github.ecaballero.domain.exception.EnrollmentNotFoundException;
import co.github.ecaballero.domain.exception.StudentNotFoundException;
import co.github.ecaballero.domain.models.CourseModel;
import co.github.ecaballero.domain.models.EnrollmentModel;
import co.github.ecaballero.domain.models.EnrollmentStatus;
import co.github.ecaballero.domain.repository.CourseRepository;
import co.github.ecaballero.domain.repository.EnrollmentRepository;
import co.github.ecaballero.domain.repository.StudentRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class EnrollmentServiceImpl
    implements EnrollmentService {

  private final EnrollmentRepository enrollmentRepository;

  private final StudentRepository studentRepository;

  private final CourseRepository courseRepository;

  public EnrollmentServiceImpl(
      EnrollmentRepository enrollmentRepository,
      StudentRepository studentRepository,
      CourseRepository courseRepository) {

    this.enrollmentRepository = enrollmentRepository;
    this.studentRepository = studentRepository;
    this.courseRepository = courseRepository;
  }

  @Override
  @Transactional
  public EnrollmentModel create(
      CreateEnrollmentDto dto) {

    validateId(dto.studentId());
    validateId(dto.courseId());

    if (!studentRepository.existsByStudentId(
        dto.studentId())) {

      throw new StudentNotFoundException(
          "Student not found with id: "
              + dto.studentId()
      );
    }

    if (!courseRepository.existsByCourseId(
        dto.courseId())) {

      throw new CourseNotFoundException(
          "Course not found with id: "
              + dto.courseId()
      );
    }

    if (enrollmentRepository
        .existsByStudentIdAndCourseId(
            dto.studentId(),
            dto.courseId())) {

      throw new EnrollmentAlreadyExistsException(
          "Student is already enrolled in this course"
      );
    }

    CourseModel course =
        courseRepository.findByCourseId(
                dto.courseId())
            .orElseThrow(() ->
                new CourseNotFoundException(
                    "Course not found"
                ));

    long currentEnrollments =
        enrollmentRepository.countByCourseId(
            dto.courseId()
        );

    if (currentEnrollments >=
        course.getMaxCapacity()) {

      throw new CourseCapacityExceededException(
          "Course has reached maximum capacity"
      );
    }

    EnrollmentModel enrollment =
        new EnrollmentModel();

    enrollment.setStudentId(
        dto.studentId()
    );

    enrollment.setCourseId(
        dto.courseId()
    );

    enrollment.setEnrollmentDate(
        LocalDate.now()
    );

    enrollment.setStatus(
        EnrollmentStatus.ACTIVE
    );

    return enrollmentRepository.save(
        enrollment
    );
  }

  @Override
  @Transactional(readOnly = true)
  public EnrollmentModel findByEnrollmentId(
      Long enrollmentId) {

    validateId(enrollmentId);

    return enrollmentRepository
        .findByEnrollmentId(enrollmentId)
        .orElseThrow(() ->
            new EnrollmentNotFoundException(
                "Enrollment not found with id: "
                    + enrollmentId
            ));
  }

  @Override
  @Transactional(readOnly = true)
  public List<EnrollmentModel> findAll() {

    List<EnrollmentModel> enrollments =
        enrollmentRepository.findAll();

    if (enrollments.isEmpty()) {

      throw new EnrollmentNotFoundException(
          "No enrollments found"
      );
    }

    return enrollments;
  }

  @Override
  @Transactional
  public EnrollmentModel update(
      Long enrollmentId,
      UpdateEnrollmentDto dto) {

    validateId(enrollmentId);

    EnrollmentModel enrollment =
        enrollmentRepository
            .findByEnrollmentId(
                enrollmentId)
            .orElseThrow(() ->
                new EnrollmentNotFoundException(
                    "Enrollment not found with id: "
                        + enrollmentId
                ));

    enrollment.setStatus(
        dto.status()
    );

    return enrollmentRepository
        .update(enrollment)
        .orElseThrow(() ->
            new EnrollmentNotFoundException(
                "Enrollment not found with id: "
                    + enrollmentId
            ));
  }

  @Override
  @Transactional
  public void delete(Long enrollmentId) {

    validateId(enrollmentId);

    if (!enrollmentRepository
        .existsByEnrollmentId(
            enrollmentId)) {

      throw new EnrollmentNotFoundException(
          "Enrollment not found with id: "
              + enrollmentId
      );
    }

    enrollmentRepository.delete(
        enrollmentId
    );
  }

  @Override
  @Transactional(readOnly = true)
  public boolean existsByEnrollmentId(
      Long enrollmentId) {

    validateId(enrollmentId);

    return enrollmentRepository
        .existsByEnrollmentId(
            enrollmentId
        );
  }

  @Override
  @Transactional(readOnly = true)
  public List<EnrollmentModel> findByStudentId(
      Long studentId) {

    validateId(studentId);

    return enrollmentRepository
        .findByStudentId(
            studentId
        );
  }

  @Override
  @Transactional(readOnly = true)
  public List<EnrollmentModel> findByCourseId(
      Long courseId) {

    validateId(courseId);

    return enrollmentRepository
        .findByCourseId(
            courseId
        );
  }

  private void validateId(Long id) {

    if (id == null || id <= 0) {

      throw new IllegalArgumentException(
          "Invalid id provided"
      );
    }
  }
}
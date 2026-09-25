package co.github.ecaballero.domain.repository;

import co.github.ecaballero.domain.models.EnrollmentModel;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository {

  EnrollmentModel save(EnrollmentModel enrollment);

  Optional<EnrollmentModel> findByEnrollmentId(Long enrollmentId);

  List<EnrollmentModel> findAll();

  Optional<EnrollmentModel> update(EnrollmentModel enrollment);

  void delete(Long enrollmentId);

  boolean existsByEnrollmentId(Long enrollmentId);

  boolean existsByStudentIdAndCourseId(
      Long studentId,
      Long courseId
  );

  long countByCourseId(Long courseId);

  List<EnrollmentModel> findByStudentId(Long studentId);

  List<EnrollmentModel> findByCourseId(Long courseId);
}
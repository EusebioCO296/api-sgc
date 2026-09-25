package co.github.ecaballero.application.service;

import co.github.ecaballero.application.dto.CreateEnrollmentDto;
import co.github.ecaballero.application.dto.UpdateEnrollmentDto;
import co.github.ecaballero.domain.models.EnrollmentModel;

import java.util.List;

public interface EnrollmentService {

  EnrollmentModel create(CreateEnrollmentDto enrollment);

  EnrollmentModel findByEnrollmentId(Long enrollmentId);

  List<EnrollmentModel> findAll();

  EnrollmentModel update(
      Long enrollmentId,
      UpdateEnrollmentDto enrollment);

  void delete(Long enrollmentId);

  boolean existsByEnrollmentId(Long enrollmentId);

  List<EnrollmentModel> findByStudentId(Long studentId);

  List<EnrollmentModel> findByCourseId(Long courseId);
}

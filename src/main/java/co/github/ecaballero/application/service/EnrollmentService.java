package co.github.ecaballero.application.service;

import co.github.ecaballero.domain.models.EnrollmentModel;
import co.github.ecaballero.domain.repository.EnrollmentRepository;

import java.util.List;
import java.util.Optional;

public class EnrollmentService implements EnrollmentRepository {

  private final EnrollmentRepository enrollmentRepository;

  public EnrollmentService(EnrollmentRepository enrollmentRepository) {
    this.enrollmentRepository = enrollmentRepository;
  }

  @Override
  public List<EnrollmentModel> findAll() {
    if (enrollmentRepository.findAll().isEmpty()) {
      throw new RuntimeException("No enrollments found");
    }
    return enrollmentRepository.findAll();
  }

  @Override
  public Optional<EnrollmentModel> findByEnrollmentId(Long enrollmentId) {
    if (enrollmentRepository.findByEnrollmentId(enrollmentId).isEmpty()) {
      throw new RuntimeException("No enrollments found");
    }
    return enrollmentRepository.findByEnrollmentId(enrollmentId);
  }

  @Override
  public void delete(Long enrollmentId) {
    if (enrollmentRepository.existsByEnrollmentId(enrollmentId)) {
      throw new RuntimeException("Enrollment already exists" + enrollmentId);
    }
    enrollmentRepository.delete(enrollmentId);
  }

  @Override
  public boolean existsByEnrollmentId(Long enrollmentId) {
    if (enrollmentRepository.findByEnrollmentId(enrollmentId).isEmpty()) {
      throw new RuntimeException("Enrollment not found" + enrollmentId);
    }
    return enrollmentRepository.existsByEnrollmentId(enrollmentId);
  }

  @Override
  public Optional<EnrollmentModel> update(EnrollmentModel enrollment) {
    if (enrollmentRepository.existsByEnrollmentId(enrollment.getId())) {
      throw new RuntimeException("Enrollment already exists" + enrollment.getId());
    }
    return enrollmentRepository.update(enrollment);
  }

  @Override
  public EnrollmentModel save(EnrollmentModel enrollment) {
    if (enrollmentRepository.existsByEnrollmentId(enrollment.getId())) {
      throw new RuntimeException("Enrollment already exists" + enrollment.getId());
    }
    return enrollmentRepository.save(enrollment);
  }
}

package main.java.co.github.ecaballero.domain.repository;

import main.java.co.github.ecaballero.domain.models.EnrollmentModel;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository {

  List<EnrollmentModel> findAll();

  Optional<EnrollmentModel> findByEnrollmentId(Long enrollmentId);

  void delete(Long enrollmentId);

  boolean existsByEnrollmentId(Long enrollmentId);

  Optional<EnrollmentModel> update(EnrollmentModel enrollment);

  EnrollmentModel save(EnrollmentModel enrollment);

}

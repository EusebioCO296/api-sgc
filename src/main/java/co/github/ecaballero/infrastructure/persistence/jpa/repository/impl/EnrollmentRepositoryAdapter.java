package co.github.ecaballero.infrastructure.persistence.jpa.repository.impl;

import co.github.ecaballero.domain.models.EnrollmentModel;
import co.github.ecaballero.domain.repository.EnrollmentRepository;
import co.github.ecaballero.infrastructure.persistence.jpa.entity.EnrollmentEntity;
import co.github.ecaballero.infrastructure.persistence.jpa.mapper.EnrollmentMapper;
import co.github.ecaballero.infrastructure.persistence.jpa.repository.JpaEnrollmentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EnrollmentRepositoryAdapter
    implements EnrollmentRepository {

  private final JpaEnrollmentRepository enrollmentJpaRepository;

  private final EnrollmentMapper enrollmentMapper;

  public EnrollmentRepositoryAdapter(
      JpaEnrollmentRepository enrollmentJpaRepository,
      EnrollmentMapper enrollmentMapper) {

    this.enrollmentJpaRepository = enrollmentJpaRepository;
    this.enrollmentMapper = enrollmentMapper;
  }

  @Override
  public EnrollmentModel save(
      EnrollmentModel enrollment) {

    EnrollmentEntity entity =
        enrollmentMapper.toEntity(enrollment);

    EnrollmentEntity savedEntity =
        enrollmentJpaRepository.save(entity);

    return enrollmentMapper.toModel(savedEntity);
  }

  @Override
  public Optional<EnrollmentModel> findByEnrollmentId(
      Long enrollmentId) {

    return enrollmentJpaRepository.findById(enrollmentId)
        .map(enrollmentMapper::toModel);
  }

  @Override
  public List<EnrollmentModel> findAll() {

    return enrollmentJpaRepository.findAll()
        .stream()
        .map(enrollmentMapper::toModel)
        .toList();
  }

  @Override
  public Optional<EnrollmentModel> update(
      EnrollmentModel enrollment) {

    if (enrollment == null ||
        enrollment.getId() == null) {

      return Optional.empty();
    }

    return enrollmentJpaRepository
        .findById(enrollment.getId())
        .map(entity -> {

          entity.setEnrollmentDate(
              enrollment.getEnrollmentDate()
          );

          entity.setStatus(
              enrollment.getStatus()
          );

          EnrollmentEntity updatedEntity =
              enrollmentJpaRepository.save(entity);

          return enrollmentMapper
              .toModel(updatedEntity);
        });
  }

  @Override
  public void delete(Long enrollmentId) {

    enrollmentJpaRepository.deleteById(
        enrollmentId
    );
  }

  @Override
  public boolean existsByEnrollmentId(
      Long enrollmentId) {

    return enrollmentJpaRepository.existsById(
        enrollmentId
    );
  }

  @Override
  public boolean existsByStudentIdAndCourseId(
      Long studentId,
      Long courseId) {

    return enrollmentJpaRepository
        .existsByStudent_IdAndCourse_Id(
            studentId,
            courseId
        );
  }

  @Override
  public long countByCourseId(
      Long courseId) {

    return enrollmentJpaRepository
        .countByCourse_Id(courseId);
  }

  @Override
  public List<EnrollmentModel> findByStudentId(
      Long studentId) {

    return enrollmentJpaRepository
        .findByStudent_Id(studentId)
        .stream()
        .map(enrollmentMapper::toModel)
        .toList();
  }

  @Override
  public List<EnrollmentModel> findByCourseId(
      Long courseId) {

    return enrollmentJpaRepository
        .findByCourse_Id(courseId)
        .stream()
        .map(enrollmentMapper::toModel)
        .toList();
  }
}
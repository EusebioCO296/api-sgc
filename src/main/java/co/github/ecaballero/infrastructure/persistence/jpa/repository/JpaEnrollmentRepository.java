package co.github.ecaballero.infrastructure.persistence.jpa.repository;

import co.github.ecaballero.infrastructure.persistence.jpa.entity.EnrollmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaEnrollmentRepository
    extends JpaRepository<EnrollmentEntity, Long> {

  /**
   * Verifica si un estudiante ya está matriculado
   * en un curso específico.
   */
  boolean existsByStudent_IdAndCourse_Id(
      Long studentId,
      Long courseId
  );

  /**
   * Cuenta cuántos estudiantes están matriculados
   * actualmente en un curso.
   */
  long countByCourse_Id(Long courseId);

  /**
   * Obtiene todas las matrículas de un estudiante.
   */
  List<EnrollmentEntity> findByStudent_Id(Long studentId);

  /**
   * Obtiene todas las matrículas de un curso.
   */
  List<EnrollmentEntity> findByCourse_Id(Long courseId);
}
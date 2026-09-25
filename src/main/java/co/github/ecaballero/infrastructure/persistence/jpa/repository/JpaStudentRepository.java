package co.github.ecaballero.infrastructure.persistence.jpa.repository;

import co.github.ecaballero.infrastructure.persistence.jpa.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaStudentRepository extends JpaRepository<StudentEntity, Long> {

  Optional<StudentEntity> findByEmail(String email);

  boolean existsByEmail(String email);

}
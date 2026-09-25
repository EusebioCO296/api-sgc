package co.github.ecaballero.infrastructure.persistence.jpa.repository;

import co.github.ecaballero.infrastructure.persistence.jpa.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCourseRepository extends JpaRepository<CourseEntity, Long> {
  boolean existsByCode(String code);
}
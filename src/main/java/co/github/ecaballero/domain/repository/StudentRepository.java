package co.github.ecaballero.domain.repository;

import co.github.ecaballero.domain.models.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface StudentRepository {

  StudentModel save(StudentModel student);

  Optional<StudentModel> findByStudentId(Long studentId);

  List<StudentModel> findAll();

  boolean existsByStudentId(Long studentId);

  void delete(Long studentId);

  Optional<StudentModel> update(StudentModel student);

  boolean existsByEmail(String email);
}

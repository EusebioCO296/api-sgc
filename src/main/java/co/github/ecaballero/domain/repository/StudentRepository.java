package main.java.co.github.ecaballero.domain.repository;

import main.java.co.github.ecaballero.domain.models.StudentModel;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

  StudentModel save(StudentModel student);

  Optional<StudentModel> findByStudentId(Long studentId);

  List<StudentModel> findAll();

  boolean existsByStudentId(Long studentId);

  void delete(Long studentId);

  Optional<StudentModel> update(StudentModel student);

  boolean existsByEmail(String email);
}

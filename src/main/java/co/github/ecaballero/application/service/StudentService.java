package co.github.ecaballero.application.service;

import co.github.ecaballero.application.dto.CreateStudentDto;
import co.github.ecaballero.domain.models.StudentModel;

import java.util.List;
import java.util.Optional;

public interface StudentService {
  StudentModel create(CreateStudentDto student);

  Optional<StudentModel> findByStudentId(Long studentId);

  List<StudentModel> findAll();

  boolean existsByStudentId(Long studentId);

  void delete(Long studentId);

  Optional<StudentModel> update(StudentModel student);

  boolean existsByEmail(String email);
}

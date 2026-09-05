package co.github.ecaballero.infrastructure.persistence.jpa.repository.impl;

import co.github.ecaballero.domain.models.StudentModel;
import co.github.ecaballero.domain.repository.StudentRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
@Service
public class StudentRepositoryService implements StudentRepository {
  private final StudentRepository studentRepository;

  public StudentRepositoryService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @Override
  public StudentModel save(StudentModel student) {
    if (student == null) {
      throw new IllegalArgumentException("Student cannot be null");
    }
    if (existsByStudentId(student.getId())) {
      throw new IllegalArgumentException("Student with id " + student.getId() + " already exists");
    }

    return studentRepository.save(student);
  }

  @Override
  public Optional<StudentModel> findByStudentId(Long studentId) {
    return studentRepository.findByStudentId(studentId);
  }

  @Override
  public List<StudentModel> findAll() {
    return studentRepository.findAll();
  }

  @Override
  public boolean existsByStudentId(Long studentId) {
    return studentRepository.existsByStudentId(studentId);
  }

  @Override
  public void delete(Long studentId) {
    studentRepository.delete(studentId);
  }

  @Override
  public boolean existsByEmail(String email) {
    return studentRepository.existsByEmail(email);
  }

  @Override
  public Optional<StudentModel> update(StudentModel student) {
    return studentRepository.update(student);
  }
}

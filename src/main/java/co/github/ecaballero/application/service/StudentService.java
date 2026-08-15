package co.github.ecaballero.application.service;

import co.github.ecaballero.application.Exceptions.StudentNotFoundException;
import co.github.ecaballero.domain.models.StudentModel;
import co.github.ecaballero.domain.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

public class StudentService implements StudentRepository {

  private final StudentRepository studentRepository;

  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @Override
  public StudentModel save(StudentModel student) {
    if (studentRepository.existsByStudentId(student.getId())) {
      throw new RuntimeException("Student with id " + student.getId() + " already exists");
    }

    if (studentRepository.existsByEmail(student.getEmail())) {
      throw new RuntimeException("Student with email " + student.getEmail() + " already exists");
    }
    return studentRepository.save(student);
  }

  @Override
  public Optional<StudentModel> findByStudentId(Long studentId) {
    if (studentRepository.findByStudentId(studentId).isEmpty()) {
      throw new StudentNotFoundException("Not student found" + studentId);
    }
    return studentRepository.findByStudentId(studentId);
  }

  @Override
  public List<StudentModel> findAll() {
    if (studentRepository.findAll().isEmpty()) {
      throw new RuntimeException("No students found");
    }
    return studentRepository.findAll();
  }

  @Override
  public boolean existsByStudentId(Long studentId) {
    if (studentRepository.findByStudentId(studentId).isEmpty()) {
      throw new StudentNotFoundException("Not student found" + studentId);
    }
    return studentRepository.existsByStudentId(studentId);
  }

  @Override
  public void delete(Long studentId) {
    if (studentRepository.existsByStudentId(studentId)) {
      throw new RuntimeException("Student with id " + studentId + " already exists");
    }
    studentRepository.delete(studentId);
  }

  @Override
  public Optional<StudentModel> update (StudentModel student) {
    if (studentRepository.findByStudentId(student.getId())) {
      throw new RuntimeException("Student id already exists" + student.getId());
    }
    if (studentRepository.existsByEmail(student.getEmail())) {
      throw new RuntimeException("Student email already exists" + student.getEmail());
    }
    return studentRepository.update(student);
  }

  @Override
  public boolean existsByEmail(String email) {
    if (studentRepository.existsByEmail(email)) {
      throw new RuntimeException("Student with email " + email + " already exists");
    }
    return studentRepository.existsByEmail(email);
  }
}

package co.github.ecaballero.application.service.impl;

import co.github.ecaballero.application.dto.CreateStudentDto;
import co.github.ecaballero.domain.exception.StudentNotFoundException;
import co.github.ecaballero.application.service.StudentService;
import co.github.ecaballero.domain.models.StudentModel;
import co.github.ecaballero.domain.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

  private final StudentRepository studentRepository;

  public StudentServiceImpl(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @Override
  public StudentModel create(CreateStudentDto createStudent) {

    StudentModel student = new StudentModel(
      createStudent.id(),
      createStudent.firstName(),
      createStudent.lastName(),
      createStudent.email(),
      createStudent.birthDate()
    );

    if (student.getId() != null && studentRepository.existsByStudentId(student.getId())) {
      throw new IllegalArgumentException("Student with id " + student.getId() + " already exists");
    }

    if (studentRepository.existsByEmail(student.getEmail())) {
      throw new IllegalArgumentException("Student with email " + student.getEmail() + " already exists");
    }

    return studentRepository.save(student);
  }
  
  @Override
  public Optional<StudentModel> findByStudentId(Long studentId) {
    validateId(studentId);

    Optional<StudentModel> student = studentRepository.findByStudentId(studentId);
    if (student.isEmpty()) {
      throw new StudentNotFoundException("Student not found with id: " + studentId);
    }
    return student;
  }

  @Override
  public List<StudentModel> findAll() {
    List<StudentModel> students = studentRepository.findAll();
    if (students.isEmpty()) {
      throw new StudentNotFoundException("No students found");
    }
    return students;
  }

  @Override
  public boolean existsByStudentId(Long studentId) {
    if (studentId == null || studentId <= 0) {
      return false;
    }
    return studentRepository.existsByStudentId(studentId);
  }

  @Override
  public void delete(Long studentId) {
    validateId(studentId);

    if (!studentRepository.existsByStudentId(studentId)) {
      throw new StudentNotFoundException("Cannot delete. Student not found with id: " + studentId);
    }

    studentRepository.delete(studentId);
  }

  @Override
  public Optional<StudentModel> update(StudentModel student) {
    if (student == null) {
      throw new IllegalArgumentException("Student payload cannot be null");
    }

    validateId(student.getId());
    validateStudentData(student);

    if (!studentRepository.existsByStudentId(student.getId())) {
      throw new StudentNotFoundException("Cannot update. Student not found with id: " + student.getId());
    }

    // Al devolver Optional<StudentModel>, 'existing' es de tipo StudentModel
    studentRepository.findByEmail(student.getEmail()).ifPresent(existing -> {
      if (existing.getId() != null && !existing.getId().equals(student.getId())) {
        throw new IllegalArgumentException("Email " + student.getEmail() + " is already in use by another student");
      }
    });

    return studentRepository.update(student);
  }

  @Override
  public boolean existsByEmail(String email) {
    if (email == null || email.trim().isEmpty()) {
      return false;
    }
    return studentRepository.existsByEmail(email);
  }

  // --- Métodos Privados de Validación ---

  private void validateStudentData(StudentModel student) {
    if (student == null) {
      throw new IllegalArgumentException("Student payload cannot be null");
    }
    if (student.getFirstName() == null || student.getFirstName().trim().isEmpty()) {
      throw new IllegalArgumentException("First name is required");
    }
    if (student.getFirstName().length() > 100) {
      throw new IllegalArgumentException("First name exceeds maximum length of 100 characters");
    }
    if (student.getLastName() == null || student.getLastName().trim().isEmpty()) {
      throw new IllegalArgumentException("Last name is required");
    }
    if (student.getLastName().length() > 100) {
      throw new IllegalArgumentException("Last name exceeds maximum length of 100 characters");
    }
    if (student.getEmail() == null || student.getEmail().trim().isEmpty()) {
      throw new IllegalArgumentException("Email is required");
    }
    if (student.getEmail().length() > 100) {
      throw new IllegalArgumentException("Email exceeds maximum length of 100 characters");
    }
    if (student.getBirthDate() == null) {
      throw new IllegalArgumentException("Birth date is required");
    }
    if (student.getBirthDate().isAfter(LocalDate.now())) {
      throw new IllegalArgumentException("Birth date cannot be in the future");
    }
  }

  private void validateId(Long id) {
    if (id == null || id <= 0) {
      throw new IllegalArgumentException("Invalid student ID provided");
    }
  }
}
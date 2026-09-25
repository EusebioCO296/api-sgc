package co.github.ecaballero.infrastructure.persistence.jpa.repository.impl;

import co.github.ecaballero.domain.models.StudentModel;
import co.github.ecaballero.domain.repository.StudentRepository;
import co.github.ecaballero.infrastructure.persistence.jpa.entity.StudentEntity;
import co.github.ecaballero.infrastructure.persistence.jpa.mapper.StudentMapper;
import co.github.ecaballero.infrastructure.persistence.jpa.repository.JpaStudentRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepositoryAdapter implements StudentRepository {
  private final JpaStudentRepository studentRepository;
  private final StudentMapper studentMapper;

  public StudentRepositoryAdapter(JpaStudentRepository studentRepository, StudentMapper studentMapper) {
    this.studentRepository = studentRepository;
    this.studentMapper = studentMapper;
  }

  @Override
  public StudentModel save(StudentModel student) {
    StudentEntity entity = studentMapper.toEntity(student);
    StudentEntity saved = studentRepository.save(entity);
    return studentMapper.toModel(saved);
  }

  @Override
  public Optional<StudentModel> findByStudentId(Long studentId) {
    return studentRepository.findById(studentId).map(studentMapper::toModel);
  }

  @Override
  public List<StudentModel> findAll() {
    return studentRepository.findAll().stream().map(studentMapper::toModel).toList();
  }

  @Override
  public boolean existsByStudentId(Long studentId) {
    return studentRepository.existsById(studentId);
  }

  @Override
  public void delete(Long studentId) {
    studentRepository.deleteById(studentId);
  }

  @Override
  public boolean existsByEmail(String email) {
    return studentRepository.existsByEmail(email);
  }

  @Override
  public Optional<StudentModel> update(StudentModel student) {
    if (student == null || student.getId() == null) {
      return Optional.empty();
    }
    return studentRepository.findById(student.getId())
        .map(entity -> {
          entity.setFirstName(student.getFirstName());
          entity.setLastName(student.getLastName());
          entity.setEmail(student.getEmail());
          entity.setBirthDate(student.getBirthDate());
          StudentEntity updatedEntity =
              studentRepository.save(entity);
          return studentMapper.toModel(updatedEntity);
        });
  }
}

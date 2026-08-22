package co.github.ecaballero.presentation.controller;

import co.github.ecaballero.domain.models.StudentModel;
import co.github.ecaballero.domain.repository.StudentRepository;
import co.github.ecaballero.infrastructure.persistence.jpa.repository.JpaStudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

  private final JpaStudentRepository studentRepository;

  public StudentController(JpaStudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @GetMapping
  public List<StudentModel> list() {
   return studentRepository.findAll();
  }

  @PostMapping
  public StudentModel create(@RequestBody StudentModel student) {
    return studentRepository.save(student);
  }

//  @DeleteMapping
//  public void delete(@RequestParam Long id) {
//    studentRepository.deleteById(id);
//  }
//
//  @GetMapping
//  public Optional<StudentModel> findById(@RequestParam Long id) {
//    return studentRepository.findById(id);
//  }
//
//  @PutMapping
//  public StudentModel save(@RequestParam Long id, @RequestBody StudentModel student) {
//    return studentRepository.save(student);
//  }
}

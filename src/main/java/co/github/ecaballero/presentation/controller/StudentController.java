package co.github.ecaballero.presentation.controller;

import co.github.ecaballero.presentation.response.ErrorResponse;
import co.github.ecaballero.application.Exceptions.BusinessException;
import co.github.ecaballero.application.service.StudentService;
import co.github.ecaballero.domain.models.StudentModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

  private final StudentService studentService;

  // Inyección por constructor de la INTERFAZ StudentService
  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @PostMapping
  public ResponseEntity<StudentModel> createStudent(@RequestBody StudentModel student) {
    StudentModel createdStudent = studentService.save(student);
    return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<StudentModel> getStudentById(@PathVariable Long id) {
    return studentService.findByStudentId(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping
  public ResponseEntity<Object> getAllStudents() {
    try {
      var students = studentService.findAll().stream()
          .map(student -> { return new StudentModel(student.getId(),student.getFirstName(),student.getLastName(),student.getEmail(),student.getBirthDate());})
          .toList();
      return ResponseEntity.ok(students);
    } catch (BusinessException e) {
      return ResponseEntity.badRequest().body(ErrorResponse.of(e.getMessage()));
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<StudentModel> updateStudent(@PathVariable Long id, @RequestBody StudentModel student) {
    student.setId(id); // Asigna el ID del PathVariable al objeto usando getId/setId
    return studentService.update(student)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
    studentService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/exists/email")
  public ResponseEntity<Boolean> existsByEmail(@RequestParam String email) {
    return ResponseEntity.ok(studentService.existsByEmail(email));
  }
}
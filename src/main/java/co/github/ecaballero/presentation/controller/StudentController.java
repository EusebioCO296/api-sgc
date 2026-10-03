package co.github.ecaballero.presentation.controller;

import co.github.ecaballero.application.dto.CreateStudentDto;
import co.github.ecaballero.application.dto.response.StudentResponseDto;
import co.github.ecaballero.application.service.StudentService;
import co.github.ecaballero.domain.models.StudentModel;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @PostMapping
  public ResponseEntity<StudentResponseDto> createStudent(
      @Valid @RequestBody CreateStudentDto createStudentDto) {

    StudentModel created =
        studentService.create(createStudentDto);

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(StudentResponseDto.from(created));
  }

  @GetMapping("/{id}")
  public ResponseEntity<StudentResponseDto> getStudentById(
      @PathVariable Long id) {

    StudentModel student =
        studentService.findByStudentId(id);
    return ResponseEntity.ok(
        StudentResponseDto.from(student)
    );
  }

  @GetMapping
  public ResponseEntity<List<StudentResponseDto>> getAllStudents() {

    List<StudentResponseDto> students =
        studentService.findAll()
            .stream()
            .map(StudentResponseDto::from)
            .toList();

    return ResponseEntity.ok(students);
  }

  @PutMapping("/{id}")
  public ResponseEntity<StudentResponseDto> updateStudent(
      @PathVariable Long id,
      @RequestBody StudentModel student) {

    student.setId(id);

    return studentService.update(student)
        .map(StudentResponseDto::from)
        .map(ResponseEntity::ok)
        .orElseGet(() ->
            ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteStudent(
      @PathVariable Long id) {

    studentService.delete(id);

    return ResponseEntity.noContent().build();
  }

  @GetMapping("/exists/email")
  public ResponseEntity<Boolean> existsByEmail(
      @RequestParam String email) {

    return ResponseEntity.ok(
        studentService.existsByEmail(email)
    );
  }
}
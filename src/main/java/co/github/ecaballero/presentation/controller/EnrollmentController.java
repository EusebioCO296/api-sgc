package co.github.ecaballero.presentation.controller;

import co.github.ecaballero.application.dto.CreateEnrollmentDto;
import co.github.ecaballero.application.dto.UpdateEnrollmentDto;
import co.github.ecaballero.application.dto.response.EnrollmentResponseDto;
import co.github.ecaballero.application.service.EnrollmentService;
import co.github.ecaballero.domain.models.EnrollmentModel;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enrollments")
public class EnrollmentController {

  private final EnrollmentService enrollmentService;

  public EnrollmentController(
      EnrollmentService enrollmentService) {

    this.enrollmentService = enrollmentService;
  }

  @PostMapping
  public ResponseEntity<EnrollmentResponseDto> createEnrollment(
      @Valid @RequestBody CreateEnrollmentDto dto) {

    EnrollmentModel enrollment =
        enrollmentService.create(dto);

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(
            EnrollmentResponseDto.from(
                enrollment
            )
        );
  }

  @GetMapping("/{id}")
  public ResponseEntity<EnrollmentResponseDto> getEnrollmentById(
      @PathVariable Long id) {

    EnrollmentModel enrollment =
        enrollmentService.findByEnrollmentId(id);

    return ResponseEntity.ok(
        EnrollmentResponseDto.from(
            enrollment
        )
    );
  }

  @GetMapping
  public ResponseEntity<List<EnrollmentResponseDto>> getAllEnrollments() {

    List<EnrollmentResponseDto> enrollments =
        enrollmentService.findAll()
            .stream()
            .map(EnrollmentResponseDto::from)
            .toList();

    return ResponseEntity.ok(enrollments);
  }

  @PutMapping("/{id}")
  public ResponseEntity<EnrollmentResponseDto> updateEnrollment(
      @PathVariable Long id,
      @Valid @RequestBody UpdateEnrollmentDto dto) {

    EnrollmentModel updated =
        enrollmentService.update(id, dto);

    return ResponseEntity.ok(
        EnrollmentResponseDto.from(
            updated
        )
    );
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteEnrollment(
      @PathVariable Long id) {

    enrollmentService.delete(id);

    return ResponseEntity.noContent().build();
  }

  @GetMapping("/student/{studentId}")
  public ResponseEntity<List<EnrollmentResponseDto>> findByStudentId(
      @PathVariable Long studentId) {

    List<EnrollmentResponseDto> enrollments =
        enrollmentService.findByStudentId(studentId)
            .stream()
            .map(EnrollmentResponseDto::from)
            .toList();

    return ResponseEntity.ok(enrollments);
  }

  @GetMapping("/course/{courseId}")
  public ResponseEntity<List<EnrollmentResponseDto>> findByCourseId(
      @PathVariable Long courseId) {

    List<EnrollmentResponseDto> enrollments =
        enrollmentService.findByCourseId(courseId)
            .stream()
            .map(EnrollmentResponseDto::from)
            .toList();

    return ResponseEntity.ok(enrollments);
  }

  @GetMapping("/exists/{id}")
  public ResponseEntity<Boolean> existsByEnrollmentId(
      @PathVariable Long id) {

    return ResponseEntity.ok(
        enrollmentService.existsByEnrollmentId(id)
    );
  }
}
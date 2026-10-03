package co.github.ecaballero.presentation.controller;

import co.github.ecaballero.application.dto.CreateCourseDto;
import co.github.ecaballero.application.dto.UpdateCourseDto;
import co.github.ecaballero.application.dto.response.CourseResponseDto;
import co.github.ecaballero.application.service.CourseService;
import co.github.ecaballero.domain.models.CourseModel;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

  private final CourseService courseService;

  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @PostMapping
  public ResponseEntity<CourseResponseDto> createCourse(
      @Valid @RequestBody CreateCourseDto dto) {

    CourseModel created =
        courseService.create(dto);

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(CourseResponseDto.from(created));
  }

  @GetMapping("/{id}")
  public ResponseEntity<CourseResponseDto> getCourseById(
      @PathVariable Long id) {

    CourseModel course =
        courseService.findByCourseId(id);

    return ResponseEntity.ok(
        CourseResponseDto.from(course)
    );
  }

  @GetMapping
  public ResponseEntity<List<CourseResponseDto>> getAllCourses() {

    List<CourseResponseDto> courses =
        courseService.findAll()
            .stream()
            .map(CourseResponseDto::from)
            .toList();

    return ResponseEntity.ok(courses);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CourseResponseDto> updateCourse(
      @PathVariable Long id,
      @Valid @RequestBody UpdateCourseDto dto) {

    CourseModel updated =
        courseService.update(id, dto);

    return ResponseEntity.ok(
        CourseResponseDto.from(updated)
    );
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCourse(
      @PathVariable Long id) {

    courseService.delete(id);

    return ResponseEntity.noContent().build();
  }

  @GetMapping("/exists/code")
  public ResponseEntity<Boolean> existsByCode(
      @RequestParam String code) {

    return ResponseEntity.ok(
        courseService.existsByCode(code)
    );
  }
}
package co.github.ecaballero.presentation.advice;

import co.github.ecaballero.application.dto.response.ErrorResponse;
import co.github.ecaballero.domain.exception.*;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(CourseCodeAlreadyExistsException.class)
  public ResponseEntity<ErrorResponse> handleCourseCodeAlreadyExists(
      CourseCodeAlreadyExistsException ex) {
    return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body(ErrorResponse.of(ex.getMessage()));
  }

  @ExceptionHandler(StudentNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleStudentNotFound(
      StudentNotFoundException ex) {

    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(ErrorResponse.of(ex.getMessage()));
  }
  @ExceptionHandler(CourseNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleCourseNotFound(
  CourseNotFoundException ex) {

    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(ErrorResponse.of(ex.getMessage()));
  }

  @ExceptionHandler(EnrollmentNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleEnrollmentNotFound(
      EnrollmentNotFoundException ex) {

    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(ErrorResponse.of(ex.getMessage()));
  }

    /*
     * CONFLICT
     */

  @ExceptionHandler(EnrollmentAlreadyExistsException.class)
  public ResponseEntity<ErrorResponse> handleEnrollmentAlreadyExists(
  EnrollmentAlreadyExistsException ex) {

    return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body(ErrorResponse.of(ex.getMessage()));
  }

  @ExceptionHandler(CourseCapacityExceededException.class)
  public ResponseEntity<ErrorResponse> handleCapacityExceeded(CourseCapacityExceededException ex) {

    return ResponseEntity
      .status(HttpStatus.CONFLICT)
      .body(ErrorResponse.of(ex.getMessage()));
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(
      DataIntegrityViolationException ex) {

    return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body(
            ErrorResponse.of(
                "Database constraint violation"
            )
        );
  }
    /*
     * BAD REQUEST
     */
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponse> handleIllegalArgument(
      IllegalArgumentException ex) {

    return ResponseEntity
        .badRequest()
        .body(ErrorResponse.of(ex.getMessage()));
  }
    /*
     * FALLBACK
     */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGeneralException(
  Exception ex) {
  return ResponseEntity
      .status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body(
        ErrorResponse.of(
          "Unexpected internal error"
          )
        );
  }
}
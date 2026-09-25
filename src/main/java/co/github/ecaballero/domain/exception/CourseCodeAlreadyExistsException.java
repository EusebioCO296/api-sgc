package co.github.ecaballero.domain.exception;

public class CourseCodeAlreadyExistsException extends RuntimeException {
  public CourseCodeAlreadyExistsException(String code) {
    super("Course code already exists: " + code);
  }
}

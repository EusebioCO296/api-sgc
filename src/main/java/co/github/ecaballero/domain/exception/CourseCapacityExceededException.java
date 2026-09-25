package co.github.ecaballero.domain.exception;

public class CourseCapacityExceededException
    extends RuntimeException {

  public CourseCapacityExceededException(String message) {
    super(message);
  }
}
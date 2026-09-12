package co.github.ecaballero.domain.exception;

public class CourseNotFoundException extends ResourceNotFoundException {

  public CourseNotFoundException(String message) {
    super(message);
  }

  public CourseNotFoundException(Long courseId) {
    super("Could not find course with id " + courseId);
  }

}

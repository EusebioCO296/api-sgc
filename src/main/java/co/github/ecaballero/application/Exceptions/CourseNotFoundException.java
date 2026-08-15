package main.java.co.github.ecaballero.application.Exceptions;

public class CourseNotFoundException extends RuntimeException {

  public CourseNotFoundException(String message) {
    super(message);
  }

  public CourseNotFoundException(Long courseId) {
    super("Could not find course with id " + courseId);
  }

}

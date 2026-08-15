package main.java.co.github.ecaballero.application.Exceptions;

public class EnrollmentNotFoundException extends RuntimeException {
  public EnrollmentNotFoundException(String message) {
    super(message);
  }

  public EnrollmentNotFoundException(Long studentId, Long courseId) {
    super("Could not find enrollment with id " + studentId + " and course id " + courseId)  ;
  }
}

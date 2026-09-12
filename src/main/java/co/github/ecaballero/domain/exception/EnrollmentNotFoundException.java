package co.github.ecaballero.domain.exception;

public class EnrollmentNotFoundException extends ResourceNotFoundException {
  public EnrollmentNotFoundException(String message) {
    super(message);
  }

  public EnrollmentNotFoundException(Long studentId, Long courseId) {
    super("Could not find enrollment with id " + studentId + " and course id " + courseId)  ;
  }
}

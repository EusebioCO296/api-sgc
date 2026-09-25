package co.github.ecaballero.domain.exception;

public class EnrollmentAlreadyExistsException
    extends RuntimeException {

  public EnrollmentAlreadyExistsException(String message) {
    super(message);
  }
}

package co.github.ecaballero.domain.exception;

public class StudentEmailAlreadyException extends ResourceNotFoundException {
  public StudentEmailAlreadyException(String message) {
    super(message);
  }

  public static StudentEmailAlreadyException forEmail(String email) {
    return new StudentEmailAlreadyException("Student with email " + email + " already exists");
  }
}

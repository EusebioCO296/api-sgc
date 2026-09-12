package co.github.ecaballero.domain.exception;

public class StudentPhoneAlreadyExistsException extends ResourceNotFoundException {
  public StudentPhoneAlreadyExistsException(String message) {
    super(message);
  }

  public static StudentPhoneAlreadyExistsException forPhone(String phone) {
    return new StudentPhoneAlreadyExistsException("Student with phone " + phone + " already exists");
  }
}

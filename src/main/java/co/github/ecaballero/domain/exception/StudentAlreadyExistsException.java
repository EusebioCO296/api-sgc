package co.github.ecaballero.domain.exception;

public class StudentAlreadyExistsException extends ResourceNotFoundException {
  public StudentAlreadyExistsException(String message) {
    super(message);
  }

  public StudentAlreadyExistsException(Long id) {
    super("Student with id " + id + " already exists");
  }

}

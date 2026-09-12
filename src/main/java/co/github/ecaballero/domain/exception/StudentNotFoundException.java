package co.github.ecaballero.domain.exception;

public class StudentNotFoundException extends ResourceNotFoundException {

  public StudentNotFoundException(String message) {
    super(message);
  }

  public StudentNotFoundException(Long studentId) {
    super("Student with id " + studentId + " not found");
  }

  public StudentNotFoundException(){
    super("Student not found");
  }

}

package co.github.ecaballero.application.Exceptions;

public class StudentNotFoundException extends RuntimeException {

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

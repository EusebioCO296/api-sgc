package co.github.ecaballero.application.dto.response;

import co.github.ecaballero.domain.models.StudentModel;

import java.time.LocalDate;

public record StudentResponseDto(
    Long id,
    String firstName,
    String lastName,
    String email,
    LocalDate birthDate
) {
  public static StudentResponseDto from(StudentModel student) {
    return new StudentResponseDto(
        student.getId(),
        student.getFirstName(),
        student.getLastName(),
        student.getEmail(),
        student.getBirthDate()
    );
  }
}
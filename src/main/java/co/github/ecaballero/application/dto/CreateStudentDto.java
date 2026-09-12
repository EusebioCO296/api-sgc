package co.github.ecaballero.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateStudentDto(
    @NotNull
    Long id,
    @NotBlank
    String firstName,
    @NotBlank
    String lastName,
    @Email
    String email,
    @NotBlank
    LocalDate birthDate
) {

}

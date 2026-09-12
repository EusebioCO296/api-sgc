package co.github.ecaballero.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public record UpdateStudentDto(
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

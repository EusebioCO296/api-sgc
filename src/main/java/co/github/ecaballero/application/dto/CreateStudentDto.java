package co.github.ecaballero.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateStudentDto(

    @NotBlank
    String firstName,

    @NotBlank
    String lastName,

    @Email
    @NotBlank
    String email,

    @NotNull
    LocalDate birthDate

) {}
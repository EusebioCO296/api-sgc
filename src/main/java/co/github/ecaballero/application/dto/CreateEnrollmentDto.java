package co.github.ecaballero.application.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateEnrollmentDto(

    @NotNull
    @Positive
    Long studentId,

    @NotNull
    @Positive
    Long courseId

) {
}
package co.github.ecaballero.application.dto;

import co.github.ecaballero.domain.models.EnrollmentStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateEnrollmentDto(

    @NotNull
    EnrollmentStatus status

) {
}
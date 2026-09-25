package co.github.ecaballero.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateCourseDto(

    @NotBlank
    String code,

    @NotBlank
    String name,

    String description,

    @NotNull
    @Positive
    Integer maxCapacity

) {
}
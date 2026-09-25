package co.github.ecaballero.application.dto.response;

import co.github.ecaballero.domain.models.CourseModel;

public record CourseResponseDto(

    Long id,
    String code,
    String name,
    String description,
    Integer maxCapacity

) {

  public static CourseResponseDto from(CourseModel course) {

    return new CourseResponseDto(
        course.getId(),
        course.getCode(),
        course.getName(),
        course.getDescription(),
        course.getMaxCapacity()
    );
  }
}
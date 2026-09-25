package co.github.ecaballero.application.dto.response;

import co.github.ecaballero.domain.models.EnrollmentModel;
import co.github.ecaballero.domain.models.EnrollmentStatus;

import java.time.LocalDate;

public record EnrollmentResponseDto(

    Long id,

    Long studentId,

    Long courseId,

    LocalDate enrollmentDate,

    EnrollmentStatus status

) {

  public static EnrollmentResponseDto from(
      EnrollmentModel enrollment) {

    return new EnrollmentResponseDto(
        enrollment.getId(),
        enrollment.getStudentId(),
        enrollment.getCourseId(),
        enrollment.getEnrollmentDate(),
        enrollment.getStatus()
    );
  }
}
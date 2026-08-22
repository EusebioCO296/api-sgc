package co.github.ecaballero.domain.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "enrollments")

public class EnrollmentModel {

  @Id
  private Long id;
  @Column (
      name = "student_id",
      nullable = false
  )
  private Long studentId;
  @Column (
      name = "course_id",
      nullable = false
  )
  private Long courseId;
  @Column (
      name = "enrollment_date",
      nullable = false
  )
  private LocalDate enrollmentDate;
  @Enumerated(EnumType.STRING)
  @Column (
      nullable = false,
      length = 20
  )
  private EnrollmentStatus status;

  public EnrollmentModel(){

  }

  public EnrollmentModel(Long id, Long studentId, Long courseId, LocalDate enrollmentDate, EnrollmentStatus status) {
    this.id = id;
    this.studentId = studentId;
    this.courseId = courseId;
    this.enrollmentDate = enrollmentDate;
    this.status = status;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getStudentId() {
    return studentId;
  }

  public void setStudentId(Long studentId) {
    this.studentId = studentId;
  }

  public Long getCourseId() {
    return courseId;
  }

  public void setCourseId(Long courseId) {
    this.courseId = courseId;
  }

  public LocalDate getEnrollmentDate() {
    return enrollmentDate;
  }

  public void setEnrollmentDate(LocalDate enrollmentDate) {
    this.enrollmentDate = enrollmentDate;
  }

  public EnrollmentStatus getStatus() {
    return status;
  }

  public void setStatus(EnrollmentStatus status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "EnrollmentModel{" +
        "id=" + id +
        ", studentId=" + studentId +
        ", courseId=" + courseId +
        ", enrollmentDate=" + enrollmentDate +
        ", status=" + status +
        '}';
  }
}

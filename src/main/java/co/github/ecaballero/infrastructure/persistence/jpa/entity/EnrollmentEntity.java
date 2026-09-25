package co.github.ecaballero.infrastructure.persistence.jpa.entity;

import co.github.ecaballero.domain.models.EnrollmentStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(
    name = "enrollments",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_student_course",
            columnNames = {
                "student_id",
                "course_id"
            }
        )
    }
)
public class EnrollmentEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(
      name = "student_id",
      nullable = false,
      foreignKey = @ForeignKey(
          name = "fk_enrollment_student"
      )
  )
  private StudentEntity student;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(
      name = "course_id",
      nullable = false,
      foreignKey = @ForeignKey(
          name = "fk_enrollment_course"
      )
  )
  private CourseEntity course;

  @Column(
      name = "enrollment_date",
      nullable = false
  )
  private LocalDate enrollmentDate;

  @Enumerated(EnumType.STRING)
  @Column(
      nullable = false,
      length = 20
  )
  private EnrollmentStatus status;

  public EnrollmentEntity() {
  }

  public EnrollmentEntity(
      Long id,
      StudentEntity student,
      CourseEntity course,
      LocalDate enrollmentDate,
      EnrollmentStatus status) {

    this.id = id;
    this.student = student;
    this.course = course;
    this.enrollmentDate = enrollmentDate;
    this.status = status;
  }

  public Long getId() {
    return id;
  }

  public StudentEntity getStudent() {
    return student;
  }

  public void setStudent(StudentEntity student) {
    this.student = student;
  }

  public CourseEntity getCourse() {
    return course;
  }

  public void setCourse(CourseEntity course) {
    this.course = course;
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

  public void setId(Long id) {
    this.id = id;
  }
}
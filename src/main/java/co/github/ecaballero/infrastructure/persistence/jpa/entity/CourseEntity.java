package co.github.ecaballero.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(
    name = "courses",
    indexes = {
        @Index(
            name = "idx_course_code",
            columnList = "code",
            unique = true
        )
    }
)
public class CourseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(
      nullable = false,
      unique = true,
      length = 20
  )
  private String code;
  @Column(
      nullable = false,
      length = 100
  )
  private String name;
  @Column(length = 500)
  private String description;
  @Column(
      name = "max_capacity",
      nullable = false
  )
  private Integer maxCapacity;

  public CourseEntity() {
  }

  public CourseEntity(
      Long id,
      String code,
      String name,
      String description,
      Integer maxCapacity) {

    this.id = id;
    this.code = code;
    this.name = name;
    this.description = description;
    this.maxCapacity = maxCapacity;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getMaxCapacity() {
    return maxCapacity;
  }

  public void setMaxCapacity(Integer maxCapacity) {
    this.maxCapacity = maxCapacity;
  }
}

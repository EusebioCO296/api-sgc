package co.github.ecaballero.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "courses")

public class CourseModel {
  @Id
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
  @Column(
      length = 500
  )
  private String description;
  @Column(
      name = "max_capacity",
      nullable = false
  )
  private Integer maxCapacity;

  public CourseModel() {}

  public CourseModel(Long id, String code, String name, String description, Integer maxCapacity) {
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

  @Override
  public String toString() {
    return "CourseModel{" +
        "id=" + id +
        ", code='" + code + '\'' +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", maxCapacity=" + maxCapacity +
        '}';
  }
}

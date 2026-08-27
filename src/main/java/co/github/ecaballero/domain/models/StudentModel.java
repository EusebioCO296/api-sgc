package co.github.ecaballero.domain.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "students")

public class StudentModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column (
      name = "first_name",
      nullable = false,
      length = 100
  )
  private String firstName;
  @Column (
      name = "last_name",
      nullable = false,
      length = 100
  )
  private String lastName;
  @Column (
      nullable = false,
      length = 100
  )
  private String email;
  @Column (
      nullable = false
  )
  private LocalDate birthDate;

  public StudentModel(){

  }

  public StudentModel(Long id, String firstName, String lastName, String email, LocalDate birthDate) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.birthDate = birthDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  @Override
  public String toString() {
    return "StudentModel{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", email='" + email + '\'' +
        ", birthDate=" + birthDate +
        '}';
  }
}


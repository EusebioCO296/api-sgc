package co.github.ecaballero.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(
    name = "students",
    indexes = {
        @Index(name = "idx_student_email", columnList = "email")
    }
)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;
}
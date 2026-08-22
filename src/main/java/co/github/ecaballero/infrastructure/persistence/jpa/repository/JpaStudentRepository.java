  package co.github.ecaballero.infrastructure.persistence.jpa.repository;

  import co.github.ecaballero.domain.models.StudentModel;
  import org.springframework.data.jpa.repository.JpaRepository;

  import java.util.List;
  import java.util.Optional;

  public interface JpaStudentRepository extends JpaRepository<StudentModel, Long> {
    StudentModel save(StudentModel student);

    Optional<StudentModel> findById(Long id);

    List<StudentModel> findAll();

    boolean existsById(Long id);

    //void delete(Long studentId);

    //Optional<StudentModel> update(StudentModel student);

    boolean existsByEmail(String email);
  }

package co.github.ecaballero.infrastructure.persistence.jpa.mapper;

import co.github.ecaballero.domain.models.StudentModel;
import co.github.ecaballero.infrastructure.persistence.jpa.entity.StudentEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentMapper {
  public StudentModel toModel(StudentEntity entity) {

    if (entity == null) {
      return null;
    }

    return new StudentModel(
        entity.getId(),
        entity.getFirstName(),
        entity.getLastName(),
        entity.getEmail(),
        entity.getBirthDate()
    );
  }

  public StudentEntity toEntity(StudentModel model) {

    if (model == null) {
      return null;
    }

    return new StudentEntity(
        model.getId(),
        model.getFirstName(),
        model.getLastName(),
        model.getEmail(),
        model.getBirthDate()
    );
  }

  public List<StudentModel> toModelList(List<StudentEntity> entities) {

    return entities.stream()
        .map(this::toModel)
        .toList();
  }

  public List<StudentEntity> toEntityList(List<StudentModel> models) {

    return models.stream()
        .map(this::toEntity)
        .toList();
  }
}
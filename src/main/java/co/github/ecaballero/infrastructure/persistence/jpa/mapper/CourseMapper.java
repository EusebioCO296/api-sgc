package co.github.ecaballero.infrastructure.persistence.jpa.mapper;

import co.github.ecaballero.domain.models.CourseModel;
import co.github.ecaballero.infrastructure.persistence.jpa.entity.CourseEntity;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {
  public CourseModel toModel(CourseEntity entity) {

    if (entity == null) {
      return null;
    }

    return new CourseModel(
        entity.getId(),
        entity.getCode(),
        entity.getName(),
        entity.getDescription(),
        entity.getMaxCapacity()
    );
  }

  public CourseEntity toEntity(CourseModel model) {

    if (model == null) {
      return null;
    }

    return new CourseEntity(
        model.getId(),
        model.getCode(),
        model.getName(),
        model.getDescription(),
        model.getMaxCapacity()
    );
  }
}

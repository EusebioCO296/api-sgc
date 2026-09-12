package co.github.ecaballero.presentation.advice;

import co.github.ecaballero.domain.exception.ResourceAlreadyExistsException;
import co.github.ecaballero.domain.exception.ResourceConflictException;
import co.github.ecaballero.domain.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public String handleResourceNotFoundException(ResourceNotFoundException ex) {
    return ex.getMessage();
  }

  public String handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex) {
    return ex.getMessage();
  }

  public String handleResourceConflictException(ResourceConflictException ex) {
    return ex.getMessage();
  }
}

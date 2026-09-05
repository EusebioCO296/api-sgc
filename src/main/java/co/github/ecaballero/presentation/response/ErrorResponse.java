package co.github.ecaballero.presentation.response;

public class ErrorResponse {

  private String message;

  public ErrorResponse() {
  }

  public ErrorResponse(String message) {
    this.message = message;
  }

  public static ErrorResponse of(String message) {
    return new ErrorResponse(message);
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
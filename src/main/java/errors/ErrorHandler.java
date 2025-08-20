package errors;

public class ErrorHandler {
  public static String getErrorMessage(BoopError error) {
    return """
        Arr me bad! %s
        """.formatted(error.getMessage());
  }
}

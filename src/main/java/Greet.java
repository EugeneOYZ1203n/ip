public class Greet {
  public static String greeting(String name) {
    return """
           Hello! I'm %s. :D
           What can I do for you?
           """.formatted(name);
  }
}

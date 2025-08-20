public class Greet {
  public static String greeting(String name) {
    return """
           Yo! I'm %s. :D
           Wat can ye do for thee, young lass?
           """.formatted(name);
  }
}

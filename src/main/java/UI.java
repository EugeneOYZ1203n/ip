import errors.BoopError;

public class UI {
  static String LINE = "____________________________________________________________";
  
  public static void printSection(String content) {
      System.out.println("%s\n%s%s\n"
          .formatted(LINE, content, LINE)
          .replaceAll("(?m)^", "\t"));
  }
  
  public static void greeting(String name) {
    printSection("""
      Yo! I'm %s. :D
      Wat can ye do for thee, young lass?
      """.formatted(name));
  }

  public static void farewell() {
    printSection("""
           Later young lass. Hope ta see ya around these here parts!
           """);
  }

  public static void errorMessage(BoopError error) {
    printSection("""
        Arr me bad! %s
        """.formatted(error.getMessage()));
  }
}

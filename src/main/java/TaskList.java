
import java.util.ArrayList;
import java.util.List;

public class TaskList {
  static List<String> tasks = new ArrayList<>();

  public static String addToList(String newItem) {
    tasks.add(newItem);

    return """
        Me remember for ye: %s
        """.formatted(newItem);
  }

  public static String display() {
    StringBuilder sb = new StringBuilder();

    for (int i = 1; i <= tasks.size(); i++) {
      sb.append("%d.\t".formatted(i))
        .append(tasks.get(i-1))
        .append("\n"); // add space between words
    }

    return sb.toString();
  }
}

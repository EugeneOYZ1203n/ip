
import java.util.ArrayList;
import java.util.List;

public class TaskList {
  static List<Task> tasks = new ArrayList<>();

  public static String addToList(String newItemName) {
    tasks.add(new Task(newItemName));

    return """
        Me remember for ye: %s
        """.formatted(newItemName);
  }

  public static String display() {
    StringBuilder sb = new StringBuilder();

    for (int i = 1; i <= tasks.size(); i++) {
      sb.append("%d.\t".formatted(i))
        .append(tasks.get(i-1).toString())
        .append("\n");
    }

    return sb.toString();
  }
}

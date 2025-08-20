
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

  public static String mark(int index) {
    Task task = tasks.get(index-1);
    task.complete();

    return """
        Yosh! Gotcha! Marked dat as completed!
        \t%s
        """.formatted(task.toString());
  }

  public static String unmark(int index) {
    Task task = tasks.get(index-1);
    task.uncomplete();

    return """
        Aye no worries! Marked dat as not done yet!
        \t%s
        """.formatted(task.toString());
  }
}

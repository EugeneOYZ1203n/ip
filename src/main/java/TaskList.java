
import java.util.ArrayList;
import java.util.List;
import tasks.Task;

public class TaskList {
  static List<Task> tasks = new ArrayList<>();

  public static String addToList(Task newTask) {
    tasks.add(newTask);

    return """
        Ho! Me remember for ye:
        \t%s
        Now ya got like %d tasks ta do!
        """.formatted(newTask.toString(), tasks.size());
  }

  public static String display() {
    StringBuilder sb = new StringBuilder();

    for (int i = 1; i <= tasks.size(); i++) {
      sb.append("%d.\t".formatted(i))
        .append(tasks.get(i-1).toString())
        .append("\n");
    }

    return """
        Here ye go, ya tasks young lass:
        %s
        """.formatted(sb.toString());
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

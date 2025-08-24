
import errors.BoopError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import tasks.Task;

public class TaskList {
  static List<Task> tasks = new ArrayList<>();
  static SaveHandler saveHandler = new SaveHandler("./data/tasks.txt");

  public static void loadTasks() {
    try {
      String[] saveStrings = saveHandler.load();
      for (String saveString : saveStrings) {
        tasks.add(Task.fromSaveString(saveString));
      }
    } catch (IOException e) {
      throw new BoopError("Young lass ya save file ain't loadin rite!");
    }
  }

  private static void saveTasks() {
    try {
      String[] saveStrings = new String[tasks.size()];
      for (int i = 0; i < tasks.size(); i++) {
        saveStrings[i] = tasks.get(i).toSaveString();
      }

      saveHandler.save(saveStrings);
    } catch (IOException e) {
      throw new BoopError("Young lass ya save file ain't loadin rite!");
    }
  }

  public static String addToList(Task newTask) {
    tasks.add(newTask);
    saveTasks();

    return """
        Ho! Me remember for ye:
        \t%s
        Now ya got like %d tasks ta do!
        """.formatted(newTask.toString(), tasks.size());
  }

  public static String deleteTask(int index) {
    Task task = tasks.get(index-1);
    tasks.remove(index-1);
    saveTasks();

    return """
        Aite little missy! It shall be gone this here task:
        \t%s
        Now ya got like %d tasks ta do!
        """.formatted(task.toString(), tasks.size());
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
    saveTasks();

    return """
        Yosh! Gotcha! Marked dat as completed!
        \t%s
        """.formatted(task.toString());
  }

  public static String unmark(int index) {
    Task task = tasks.get(index-1);
    task.uncomplete();
    saveTasks();

    return """
        Aye no worries! Marked dat as not done yet!
        \t%s
        """.formatted(task.toString());
  }

  public static boolean isValidIndex(int index) {
    return index <= tasks.size() && index >= 1;
  }
}

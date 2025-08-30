package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import errors.BoopError;
import tasks.Task;

public final class TaskList {
  List<Task> tasks;
  SaveHandler saveHandler;

  public TaskList(String savePathName) {
    tasks = new ArrayList<>();
    saveHandler = new SaveHandler(savePathName);
  }

  public TaskList(SaveHandler saveHandler) {
    tasks = new ArrayList<>();
    this.saveHandler = saveHandler;
  }

  public void loadTasks() throws BoopError {
    try {
      String[] saveStrings = saveHandler.load();
      for (String saveString : saveStrings) {
        tasks.add(Task.fromSaveString(saveString));
      }
    } catch (IOException e) {
      tasks = new ArrayList<>();
      throw new BoopError("Young lass ya save file ain't loadin rite!");
    }
  }

  private void saveTasks() {
    try {
      String[] saveStrings = new String[tasks.size()];
      for (int i = 0; i < tasks.size(); i++) {
        saveStrings[i] = tasks.get(i).toSaveString();
      }

      saveHandler.save(saveStrings);
    } catch (IOException e) {
      throw new BoopError("Young lass ya save file ain't savin rite!");
    }
  }

  public void addToList(Task newTask) {
    tasks.add(newTask);
    saveTasks();
  }

  public Task deleteTask(int index) {
    Task task = tasks.get(index-1);
    tasks.remove(index-1);
    saveTasks();
    return task;
  }

  public int getTaskslistLength() {
    return tasks.size();
  }

  public String filterDisplay(String regex) {
    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    StringBuilder sb = new StringBuilder();

    for (int i = 1; i <= tasks.size(); i++) {
      String taskStr = tasks.get(i - 1).toString();
      if (pattern.matcher(taskStr).find()) {
            sb.append("%d.\t".formatted(i))
              .append(taskStr)
              .append("\n");
        }
    }

    return sb.toString();
  }

  public String display() {
    return filterDisplay("");
  }

  public Task mark(int index) {
    Task task = tasks.get(index-1);
    task.complete();
    saveTasks();
    return task; 
  }

  public Task unmark(int index) {
    Task task = tasks.get(index-1);
    task.uncomplete();
    saveTasks();
    return task;
  }

  public boolean isValidIndex(int index) {
    return index <= tasks.size() && index >= 1;
  }
}

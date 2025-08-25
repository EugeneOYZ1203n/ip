package app;

import errors.BoopError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import tasks.Task;

public final class TaskList {
  List<Task> tasks;
  SaveHandler saveHandler;

  public TaskList() {
    tasks = new ArrayList<>();
    saveHandler = new SaveHandler("./data/tasks.txt");
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
      throw new BoopError("Young lass ya save file ain't loadin rite!");
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

  public String display() {
    StringBuilder sb = new StringBuilder();

    for (int i = 1; i <= tasks.size(); i++) {
      sb.append("%d.\t".formatted(i))
        .append(tasks.get(i-1).toString())
        .append("\n");
    }

    return sb.toString();
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

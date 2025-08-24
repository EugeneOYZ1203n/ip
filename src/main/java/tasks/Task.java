package tasks;

import errors.BoopError;

public class Task {
  String name;
  boolean isComplete;

  public Task(String name, boolean isComplete) {
    this.name = name;
    this.isComplete = isComplete;
  }

  public Task(String name) {
    this(name, false);
  }
  
  public void complete() { this.isComplete = true; }
  public void uncomplete() { this.isComplete = false; }

  @Override
  public String toString() {
    return "[%s] %s".formatted(
      this.isComplete ? "X" : " ",
      this.name
    );
  }

  public String toSaveString() {
    return "%s | %s".formatted(
      this.isComplete ? "X" : " ",
      this.name
    );
  }

  public static Task fromSaveString(String saveString) throws BoopError {
    String[] parts = saveString.split(" \\| ");

    if (parts.length < 3) { 
      throw new BoopError("Save file might be corrupted, cancelling loading process!!"); 
    }

    String type = parts[0];

    return switch (type) {
        case "T" -> Todo.fromSaveString(saveString);
        case "D" -> Deadline.fromSaveString(saveString);
        case "E" -> Event.fromSaveString(saveString);
        default -> throw new BoopError("There was an issue with loadin' missy. Dun know wat dis ere is: " + type);
    };
  }
}

package tasks;

import errors.BoopError;

public class Todo extends Task {

  public Todo(String name, boolean isComplete) {
    super(name, isComplete);
  }
  public Todo(String name) {
    this(name, false);
  }

  @Override
  public String toString() {
      return "[T]%s".formatted(super.toString());
  }
  
  @Override
  public String toSaveString() {
    return "T | %s".formatted(super.toSaveString());
  }

  public static Todo fromSaveString(String saveString) throws BoopError {
    String[] parts = saveString.split(" \\| ");
    String type = parts[0];

    if (!type.equals("T")) { throw new BoopError("Some issue occured! This function is for Todos not for: " + type); }

    if (parts.length < 3) { 
      throw new BoopError("Save file might be corrupted, cancelling loading process!!"); 
    }

    boolean isDone = parts[1].equals("X");
    String name = parts[2];

    return new Todo(name, isDone);
  }
}

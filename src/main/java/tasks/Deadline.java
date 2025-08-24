package tasks;

import errors.BoopError;

public class Deadline extends Task {
  String deadline;

  public Deadline(String name, boolean isComplete, String deadline) {
    super(name, isComplete);
    this.deadline = deadline;
  }
  public Deadline(String name, String deadline) {
    this(name, false, deadline);
  }

  @Override
  public String toString() {
      return "[D]%s (by: %s)".formatted(super.toString(), this.deadline);
  }

  @Override
  public String toSaveString() {
    return "D | %s | %s".formatted(super.toString(), this.deadline);
  }

  public static Deadline fromSaveString(String saveString) throws BoopError {
    String[] parts = saveString.split(" \\| ");
    String type = parts[0];

    if (!type.equals("D")) { throw new BoopError("Some issue occured! This function is for Deadline not for: " + type); }

    if (parts.length < 4) { 
      throw new BoopError("Save file might be corrupted, cancelling loading process!!"); 
    }

    boolean isDone = parts[1].equals("X");
    String name = parts[2];
    String deadline = parts[3];

    return new Deadline(name, isDone, deadline);
  }
}

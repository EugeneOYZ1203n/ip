package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import errors.BoopError;

public class Deadline extends Task {
  LocalDate deadline;

  public Deadline(String name, boolean isComplete, LocalDate deadline) {
    super(name, isComplete);
    this.deadline = deadline;
  }
  public Deadline(String name, LocalDate deadline) {
    this(name, false, deadline);
  }

  @Override
  public String toString() {
      return "[D]%s (by: %s)".formatted(
        super.toString(), 
        this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
  }

  @Override
  public String toSaveString() {
    return "D | %s | %s".formatted(super.toSaveString(), this.deadline.toString());
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
    LocalDate deadline;
    try {
      deadline = LocalDate.parse(parts[3]);
    } catch (DateTimeParseException e) {
      throw new BoopError("Save file might be corrupted, cancelling loading process!!"); 
    }

    return new Deadline(name, isDone, deadline);
  }
}

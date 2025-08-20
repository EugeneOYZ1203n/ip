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
}

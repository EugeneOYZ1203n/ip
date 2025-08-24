package tasks;
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
}

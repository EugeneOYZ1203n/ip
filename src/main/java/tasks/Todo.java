package tasks;
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
    return "T | %s | %s".formatted(super.toString());
  }
}

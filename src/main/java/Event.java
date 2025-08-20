public class Event extends Task {
  String fromTime;
  String toTime;

  public Event(String name, boolean isComplete, String fromTime, String toTime) {
    super(name, isComplete);
    this.fromTime = fromTime;
    this.toTime = toTime;
  }
  public Event(String name, String fromTime, String toTime) {
    this(name, false, fromTime, toTime);
  }

  @Override
  public String toString() {
      return "[E]%s (from: %s to: %s)".formatted(super.toString(), this.fromTime, this.toTime);
  }
}

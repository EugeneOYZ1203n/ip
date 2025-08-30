package tasks;

import errors.BoopError;

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

    @Override
    public String toSaveString() {
        return "E | %s | %s | %s".formatted(super.toSaveString(), this.fromTime, this.toTime);
    }

    public static Event fromSaveString(String saveString) throws BoopError {
        String[] parts = saveString.split(" \\| ");
        String type = parts[0];

        if (!type.equals("E")) {
            throw new BoopError("Some issue occured! This function is for Events not for: " + type);
        }

        if (parts.length < 5) {
            throw new BoopError("Save file might be corrupted, cancelling loading process!!");
        }

        boolean isDone = parts[1].equals("X");
        String name = parts[2];
        String fromTime = parts[3];
        String toTime = parts[4];

        return new Event(name, isDone, fromTime, toTime);
    }
}

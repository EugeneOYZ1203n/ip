package tasks;

import errors.BoopError;

/**
 * Represents an event task with a start and end time.
 */
public class Event extends Task {
    private String fromTime;
    private String toTime;

    /**
     * Creates an Event with a specified completion status.
     *
     * @param name       Name of the event
     * @param isComplete Whether the event is marked as complete
     * @param fromTime   Start time of the event
     * @param toTime     End time of the event
     */
    public Event(String name, boolean isComplete, String fromTime, String toTime) {
        super(name, isComplete);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    /**
     * Creates an incomplete Event by default.
     *
     * @param name     Name of the event
     * @param fromTime Start time of the event
     * @param toTime   End time of the event
     */
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

    /**
     * Converts a Save String format of a Event back into a Event instance
     *
     * @param saveString Event in format written in save file
     * @return Event using data from save file
     * @throws BoopError
     */
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

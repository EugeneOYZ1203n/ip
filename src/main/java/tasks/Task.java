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

    public void complete() {
        this.isComplete = true;
    }

    public void uncomplete() {
        this.isComplete = false;
    }

    /**
     * @return String format of task
     */
    @Override
    public String toString() {
        return "[%s] %s".formatted(
                this.isComplete ? "X" : " ",
                this.name);
    }

    /**
     * Returns the Save String format of the task
     * 
     * @return Format for writing into save file
     */
    public String toSaveString() {
        return "%s | %s".formatted(
                this.isComplete ? "X" : " ",
                this.name);
    }

    /**
     * Converts a Save String format of a task back into a Task instance
     * 
     * @param saveString task in format written in save file
     * @return Task using data from save file
     * @throws BoopError
     */
    public static Task fromSaveString(String saveString) throws BoopError {
        assert saveString != null && !saveString.isEmpty() : "saveString must not be null or empty";

        String[] parts = saveString.split(" \\| ");
        assert parts.length >= 3 : "saveString must have at least 3 parts";

        if (parts.length < 3) {
            throw new BoopError("Save file might be corrupted, cancelling loading process!!");
        }

        String type = parts[0];
        assert type.equals("T") || type.equals("D") || type.equals("E")
                : "Task type must be T, D, or E, but got: " + type;

        return switch (type) {
            case "T" -> Todo.fromSaveString(saveString);
            case "D" -> Deadline.fromSaveString(saveString);
            case "E" -> Event.fromSaveString(saveString);
            default -> throw new BoopError("There was an issue with loadin' missy. Dun know wat dis ere is: " + type);
        };
    }
}

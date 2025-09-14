package tasks;

import app.Messages;
import errors.BoopError;

/**
 * Represents a generic task with a name and completion status.
 *
 * This is the base class for all specific task types, such as Todo, Deadline
 * and Event.
 */
public class Task {
    private String name;
    private boolean isComplete;

    /**
     * Constructs a Task with the specified name and completion status.
     *
     * @param name       Name of the task
     * @param isComplete Whether the task is completed
     */
    public Task(String name, boolean isComplete) {
        this.name = name;
        this.isComplete = isComplete;
    }

    /**
     * Constructs a Task with the specified name and sets completion status to
     * false.
     *
     * @param name Name of the task
     */
    public Task(String name) {
        this(name, false);
    }

    /**
     * Marks this task as completed.
     */
    public void complete() {
        this.isComplete = true;
    }

    /**
     * Marks this task as not completed.
     */
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
        String[] parts = saveString.split(" \\| ");

        if (parts.length < 3) {
            throw new BoopError(Messages.ERROR_SAVE_CORRUPTED);
        }

        String type = parts[0];

        return switch (type) {
        case "T" -> Todo.fromSaveString(saveString);
        case "D" -> Deadline.fromSaveString(saveString);
        case "E" -> Event.fromSaveString(saveString);
        default -> throw new BoopError(String.format(Messages.ERROR_UNKNOWN_TASK_TYPE, type));
        };
    }
}

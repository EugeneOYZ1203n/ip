package tasks;

import errors.BoopError;

/**
 * Represents a Todo task with just a name and completion status.
 */
public class Todo extends Task {

    /**
     * Creates a Todo with a specified completion status.
     *
     * @param name       Name of the todo task
     * @param isComplete Whether the todo task is marked as complete
     */
    public Todo(String name, boolean isComplete) {
        super(name, isComplete);
    }

    /**
     * Creates an incomplete Todo.
     *
     * @param name Name of the todo task
     */
    public Todo(String name) {
        this(name, false);
    }

    @Override
    public String toString() {
        return "[T]%s".formatted(super.toString());
    }

    @Override
    public String toSaveString() {
        return "T | %s".formatted(super.toSaveString());
    }

    /**
     * Converts a Save String format of a Todo back into a Todo instance
     *
     * @param saveString Todo in format written in save file
     * @return Todo using data from save file
     * @throws BoopError
     */
    public static Todo fromSaveString(String saveString) throws BoopError {
        String[] parts = saveString.split(" \\| ");
        String type = parts[0];

        if (!type.equals("T")) {
            throw new BoopError("Some issue occured! This function is for Todos not for: " + type);
        }

        if (parts.length < 3) {
            throw new BoopError("Save file might be corrupted, cancelling loading process!!");
        }

        boolean isDone = parts[1].equals("X");
        String name = parts[2];

        return new Todo(name, isDone);
    }
}

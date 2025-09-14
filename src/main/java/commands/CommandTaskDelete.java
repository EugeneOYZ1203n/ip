package commands;

import app.Messages;
import app.TaskList;
import errors.BoopError;
import tasks.Task;

/**
 * This command deletes a task from the task list by its index.
 */
public class CommandTaskDelete extends Command {
    private final int index;
    private Task task;
    private int taskSize;

    /**
     * Creates a Delete command using the given user input.
     *
     * @param input Raw user input string containing the index of the task to delete
     */
    public CommandTaskDelete(String input) {
        index = CommandHelpers.getIndexArgument(input);
    }

    @Override
    public void execute(TaskList tasklist) throws BoopError {
        if (!tasklist.isValidIndex(index)) {
            throw new BoopError("Ya given index ain't correct lass!");
        }

        task = tasklist.deleteTask(index);
        taskSize = tasklist.getTaskslistLength();
    }

    @Override
    public String getMessage() {
        return Messages.COMMAND_DELETE.formatted(task.toString(), taskSize);
    }
}

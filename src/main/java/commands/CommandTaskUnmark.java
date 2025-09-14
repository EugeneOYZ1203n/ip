package commands;

import app.TaskList;
import errors.BoopError;
import tasks.Task;

/**
 * This command marks a task in the task list as not completed by its index.
 */
public class CommandTaskUnmark extends Command {
    private final int index;
    private Task task;

    /**
     * Creates a Unmark command using the given user input.
     *
     * @param input Raw user input string containing the index of the task to unmark
     */
    public CommandTaskUnmark(String input) {
        index = CommandHelpers.getIndexArgument(input);
    }

    @Override
    public void execute(TaskList tasklist) throws BoopError {
        if (!tasklist.isValidIndex(index)) {
            throw new BoopError("Ya given index ain't correct lass!");
        }

        task = tasklist.unmark(index);
    }

    @Override
    public String getMessage() {
        return """
                Aye no worries! Marked dat as not done yet!
                \t%s
                """.formatted(task.toString());
    }
}

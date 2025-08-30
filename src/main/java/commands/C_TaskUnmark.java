package commands;

import app.TaskList;
import errors.BoopError;
import tasks.Task;

public class C_TaskUnmark extends Command {
    private final int index;
    private Task task;

    public C_TaskUnmark(String input) {
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

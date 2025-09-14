package commands;

import app.TaskList;
import errors.BoopError;

/**
 * This command displays all tasks currently stored in the task list.
 */
public class CommandTaskList extends Command {
    private String taskDisplay;

    @Override
    public void execute(TaskList tasklist) throws BoopError {
        taskDisplay = tasklist.display();
    }

    @Override
    public String getMessage() {
        return """
                Here ye go, ya tasks young lass:
                %s
                """.formatted(taskDisplay);
    }
}

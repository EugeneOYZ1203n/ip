package commands;

import app.TaskList;
import errors.BoopError;

public abstract class Command {
    /**
     * Executes the command, performing the necessary tasks
     * Uses parameters provided when command was generated
     * 
     * @param tasklist Tasklist that may be used by the command
     * @throws BoopError Error related to Boop process
     */
    public abstract void execute(TaskList tasklist) throws BoopError;

    /**
     * @return UI message of the command
     */
    public abstract String getMessage();

    /**
     * @return Whether the command exits the process
     */
    public boolean isExit() {
        return false;
    }
}

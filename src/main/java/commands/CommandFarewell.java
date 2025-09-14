package commands;

import app.TaskList;
import errors.BoopError;

/**
 * This command signals that the program should exit
 * after displaying a farewell message.
 */
public class CommandFarewell extends Command {
    @Override
    public void execute(TaskList tasklist) throws BoopError {
    }

    @Override
    public String getMessage() {
        return """
                Later young lass. Hope ta see ya around these here parts!
                """;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

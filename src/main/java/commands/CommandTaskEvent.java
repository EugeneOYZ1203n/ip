package commands;

import java.util.List;
import java.util.Map;

import app.Messages;
import app.TaskList;
import commands.CommandHelpers.Flags;
import errors.BoopError;
import tasks.Event;

/**
 * This command creates a new Event task.
 * The task is then added to the task list and saved.
 */
public class CommandTaskEvent extends Command {
    private static final Map<String, List<String>> flagNames = Map.of(
            "from", List.of("from", "f"),
            "to", List.of("to", "t"));

    private final Event event;
    private int taskSize;

    /**
     * Creates a Event command from the given user input.
     *
     * @param input Raw user input string
     * @throws BoopError if the task name or start and end times is missing
     */
    public CommandTaskEvent(String input) throws BoopError {
        Flags flags = Flags.parseFlags(flagNames, input);

        if (!flags.has("")) {
            throw new BoopError("Name not given!");
        }
        if (!flags.has("from")) {
            throw new BoopError("Start time not given!");
        }
        if (!flags.has("to")) {
            throw new BoopError("End time not given!");
        }

        event = new Event(
                flags.get(""),
                flags.get("from"),
                flags.get("to"));
    }

    @Override
    public void execute(TaskList tasklist) throws BoopError {
        tasklist.addToList(event);
        taskSize = tasklist.getTaskslistLength();
    }

    @Override
    public String getMessage() {
        return Messages.COMMAND_EVENT.formatted(event.toString(), taskSize);
    }
}

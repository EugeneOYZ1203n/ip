package commands;

import java.util.Map;

import app.TaskList;
import commands.CommandHelpers.Flags;
import errors.BoopError;

public class C_TaskFind extends Command {
    private String taskDisplay;
    private String filterRegex;

    public C_TaskFind(String input) throws BoopError {
        Flags flags = Flags.parseFlags(Map.of(), input);

        if (!flags.has("")) {
            throw new BoopError("Filter keyword not given!");
        }

        filterRegex = flags.get("");
    }

    @Override
    public void execute(TaskList tasklist) throws BoopError {
        taskDisplay = tasklist.filterDisplay(filterRegex);
    }

    @Override
    public String getMessage() {
        return """
                Here ye go, tasks that contain [%s] young lass:
                %s
                """.formatted(filterRegex, taskDisplay);
    }
}

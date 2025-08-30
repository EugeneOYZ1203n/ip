package commands;

import java.util.List;
import java.util.Map;

import app.TaskList;
import commands.CommandHelpers.Flags;
import errors.BoopError;
import tasks.Event;

public class C_TaskEvent extends Command {
  private static final Map<String, List<String>> flagNames = Map.of(
    "from", List.of("from", "f"),
    "to", List.of("to", "t")
  );

  private final Event event;
  private int taskSize;

  public C_TaskEvent(String input) throws BoopError {
    Flags flags = Flags.parseFlags(flagNames, input);

    if (!flags.has("")) { throw new BoopError("Name not given!"); }
    if (!flags.has("from")) { throw new BoopError("Start time not given!"); }
    if (!flags.has("to")) { throw new BoopError("End time not given!"); }

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
    return """
        Ho! Me remember for ye:
        \t%s
        Now ya got like %d tasks ta do!
        """.formatted(event.toString(), taskSize);
  }
}

package commands;

import app.TaskList;
import commands.CommandHelpers.Flags;
import errors.BoopError;
import java.util.Map;
import tasks.Todo;

public class C_TaskTodo extends Command {
  private final Todo todo;
  private int taskSize;

  public C_TaskTodo(String input) throws BoopError {
    Flags flags = Flags.parseFlags(Map.of(), input);

    if (!flags.has("")) { throw new BoopError("Name not given!"); }

    todo = new Todo(flags.get(""));
  }

  @Override
  public void execute(TaskList tasklist) throws BoopError {
    tasklist.addToList(todo);
    taskSize = tasklist.getTaskslistLength();
  }

  @Override
  public String getMessage() {
    return """
        Ho! Me remember for ye:
        \t%s
        Now ya got like %d tasks ta do!
        """.formatted(todo.toString(), taskSize);
  }
}

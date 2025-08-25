package commands;

import app.TaskList;
import errors.BoopError;
import tasks.Task;

public class C_TaskMark extends Command {
  private final int index;
  private Task task;
  
  public C_TaskMark(String input) {
    index = CommandHelpers.getIndexArgument(input);
  }

  @Override
  public void execute(TaskList tasklist) throws BoopError {
    if (!tasklist.isValidIndex(index)) {
      throw new BoopError("Ya given index ain't correct lass!");
    }

    task = tasklist.mark(index);
  }

  @Override
  public String getMessage() {
    return """
        Yosh! Gotcha! Marked dat as completed!
        \t%s
        """.formatted(task.toString());
  }
}

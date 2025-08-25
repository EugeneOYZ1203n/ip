package commands;

import app.TaskList;
import errors.BoopError;
import tasks.Task;

public class C_TaskDelete extends Command {
  private final int index;
  private Task task;
  private int taskSize;

  public C_TaskDelete(String input) {
    index = CommandHelpers.getIndexArgument(input);
  }
  
  @Override
  public void execute(TaskList tasklist) throws BoopError {
    if (!tasklist.isValidIndex(index)) {
      throw new BoopError("Ya given index ain't correct lass!");
    }

    task = tasklist.deleteTask(index);
    taskSize = tasklist.getTaskslistLength();
  }

  @Override
  public String getMessage() {
    return """
        Aite little missy! It shall be gone this here task:
        \t%s
        Now ya got like %d tasks ta do!
        """.formatted(task.toString(), taskSize);
  }
}

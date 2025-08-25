package commands;

import app.TaskList;
import errors.BoopError;

public class C_TaskList extends Command {
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

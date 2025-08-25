package commands;

import app.TaskList;
import errors.BoopError;

public class C_Farewell extends Command {
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

package commands;

import app.TaskList;
import errors.BoopError;

public abstract class Command {
  public abstract void execute(TaskList tasklist) throws BoopError;
  public abstract String getMessage();
  public boolean isExit() {
    return false;
  }
}

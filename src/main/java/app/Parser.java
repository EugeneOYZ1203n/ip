package app;
import commands.C_Farewell;
import commands.C_TaskDeadline;
import commands.C_TaskDelete;
import commands.C_TaskEvent;
import commands.C_TaskList;
import commands.C_TaskMark;
import commands.C_TaskTodo;
import commands.C_TaskUnmark;
import commands.Command;
import errors.BoopError;
import java.util.Scanner;

public final class Parser {
  static Scanner sc = new Scanner(System.in);

  public Command getNextCommand() throws BoopError {
    String nextLine = sc.nextLine();

    String[] words = nextLine.split(" ", 2);
    String commandName = words[0].trim();

    switch (commandName) {
    case "bye" -> {
        return new C_Farewell();
    }
    case "mark" -> {
        return new C_TaskMark(nextLine);
    }
    case "unmark" -> {
        return new C_TaskUnmark(nextLine);
    }
    case "delete" -> {
        return new C_TaskDelete(nextLine);
    }
    case "list" -> {
        return new C_TaskList();
    }
    case "todo" -> {
        return new C_TaskTodo(nextLine);
    }
    case "deadline" -> {
        return new C_TaskDeadline(nextLine);
    }
    case "event" -> {
        return new C_TaskEvent(nextLine);
    }
    default -> throw new BoopError("Don't get wut ya sayin missy. Say it again!");
    }
  }
}

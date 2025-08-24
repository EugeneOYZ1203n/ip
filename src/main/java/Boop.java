import errors.BoopError;
import java.time.LocalDate;
import java.util.Scanner;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

public class Boop {
    static String LINE = "____________________________________________________________";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        TaskList.loadTasks();

        UI.greeting("Boop");

        OUTER:
        while (true) {
            String line = getNextLine();
            String[] words = line.split(" ", 2);
            String command = words[0].trim();

            try {
                switch (command) {
                case "bye" -> {
                    UI.farewell();
                    break OUTER;
                }
                case "mark" -> {
                    UI.printSection(TaskList.mark(getIndexArgument(words)));
                }
                case "unmark" -> {
                    UI.printSection(TaskList.unmark(getIndexArgument(words)));
                }
                case "delete" -> {
                    UI.printSection(TaskList.deleteTask(getIndexArgument(words)));
                }
                case "list" -> UI.printSection(TaskList.display());
                case "todo" -> {
                    if (words.length < 2) { throw new BoopError("Ya missing da name!"); }
                    UI.printSection(TaskList.addToList(new Todo(words[1])));
                }
                case "deadline" -> {
                    if (words.length < 2) { throw new BoopError("Ya missing da name!"); }
                    String[] parts = words[1].split("/by", 2);
                    if (parts.length < 2) { throw new BoopError("Ya missing da deadline!"); }
                    String name = parts[0].trim();
                    LocalDate by = LocalDate.parse(parts[1].trim());
                    UI.printSection(TaskList.addToList(new Deadline(name, by)));
                }
                case "event" -> {
                    if (words.length < 2) { throw new BoopError("Ya missing da name!"); }
                    String[] parts = words[1].split("/from|/to");
                    if (parts.length < 2) { throw new BoopError("Ya missing da start time!"); }
                    if (parts.length < 3) { throw new BoopError("Ya missing da end time!"); }
                    String name = parts[0].trim();
                    String from = parts[1].trim();
                    String to = parts[2].trim();
                    UI.printSection(TaskList.addToList(new Event(name, from, to)));
                }
                default -> throw new BoopError("Don't get wut ya sayin missy. Say it again!");
                }
            } catch (BoopError e) {
                UI.errorMessage(e);
            }
        }
        
    }

    public static String getNextLine() {
        return sc.nextLine();
    }

    public static int getIndexArgument(String[] words) throws BoopError {
        if (words.length < 2) { throw new BoopError("Ya missing da index!"); }

        int index;
        try {
            index = Integer.parseInt(words[1]);
        } catch (NumberFormatException e) {
            throw new BoopError("Ya number ain't numbering!");
        }

        if (!TaskList.isValidIndex(index)) {
            throw new BoopError("Ya index ain't right, nothin with that number!");
        }

        return index;
    }
}
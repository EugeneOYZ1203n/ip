import errors.BoopError;
import errors.ErrorHandler;
import java.util.Scanner;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

public class Boop {
    static String LINE = "____________________________________________________________";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        printSection(Greet.greeting("Boop"));

        OUTER:
        while (true) {
            String line = getNextLine();
            String[] words = line.split(" ", 2);
            String command = words[0].trim();

            try {
                switch (command) {
                    case "bye" -> {
                        printSection(Farewell.farewell());
                        break OUTER;
                    }
                    case "mark" -> {
                        int index = Integer.parseInt(words[1]);
                        printSection(TaskList.mark(index));
                    }
                    case "unmark" -> {
                        int index = Integer.parseInt(words[1]);
                        printSection(TaskList.unmark(index));
                    }
                    case "list" -> printSection(TaskList.display());
                    case "todo" -> {
                        if (words.length < 2) { throw new BoopError("Ya missing da name!"); }
                        printSection(TaskList.addToList(new Todo(words[1])));
                    }
                    case "deadline" -> {
                        if (words.length < 2) { throw new BoopError("Ya missing da name!"); }
                        String[] parts = words[1].split("/by", 2);
                        if (parts.length < 2) { throw new BoopError("Ya missing da deadline!"); }
                        String name = parts[0].trim();
                        String by = parts[1].trim();
                        printSection(TaskList.addToList(new Deadline(name, by)));
                    }
                    case "event" -> {
                        if (words.length < 2) { throw new BoopError("Ya missing da name!"); }
                        String[] parts = words[1].split("/from|/to");
                        if (parts.length < 2) { throw new BoopError("Ya missing da start time!"); }
                        if (parts.length < 3) { throw new BoopError("Ya missing da end time!"); }
                        String name = parts[0].trim();
                        String from = parts[1].trim();
                        String to = parts[2].trim();
                        printSection(TaskList.addToList(new Event(name, from, to)));
                    }
                    default -> throw new BoopError("Don't get wut ya sayin missy. Say it again!");
                }
            } catch (BoopError e) {
                printSection(ErrorHandler.getErrorMessage(e));
            }
        }
        
    }

    public static void printSection(String content) {
        System.out.println("%s\n%s%s\n"
            .formatted(LINE, content, LINE)
            .replaceAll("(?m)^", "\t"));
    }

    public static String getNextLine() {
        return sc.nextLine();
    }
}
import java.util.Scanner;

public class Boop {
    static String LINE = "____________________________________________________________";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        printSection(Greet.greeting("Boop"));

        OUTER:
        while (true) {
            String line = getNextLine();
            String[] words = line.split(" ", 2);
            String command = words[0];

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
                    printSection(TaskList.addToList(new Todo(words[1])));
                }
                case "deadline" -> {
                    String[] parts = words[1].split("/by", 2);
                    String name = parts[0].trim();
                    String by = parts[1].trim();
                    printSection(TaskList.addToList(new Deadline(name, by)));
                }
                case "event" -> {
                    String[] parts = words[1].split("/from|/to");
                    String name = parts[0].trim();
                    String from = parts[1].trim();
                    String to = parts[2].trim();
                    printSection(TaskList.addToList(new Event(name, from, to)));
                }
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
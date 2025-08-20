import java.util.Scanner;

public class Boop {
    static String LINE = "____________________________________________________________";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        printSection(Greet.greeting("Boop"));

        OUTER:
        while (true) {
            String line = getNextLine();
            String[] words = line.split(" ");
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
                default -> printSection(TaskList.addToList(line));
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
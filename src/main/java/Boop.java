import java.util.Scanner;

public class Boop {
    static String LINE = "____________________________________________________________";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        printSection(Greet.greeting("Boop"));

        OUTER:
        while (true) {
            String command = getNextCommand();
            
            switch (command) {
                case "bye" -> {
                    printSection(Farewell.farewell());
                    break OUTER;
                }
                case "list" -> printSection(TaskList.display());
                default -> printSection(TaskList.addToList(command));
            }
        }
    }

    public static void printSection(String content) {
        System.out.println("%s\n%s%s\n"
            .formatted(LINE, content, LINE)
            .replaceAll("(?m)^", "\t"));
    }

    public static String getNextCommand() {
        return sc.nextLine();
    }
}
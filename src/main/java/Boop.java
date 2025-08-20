import java.util.Scanner;

public class Boop {
    static String LINE = "____________________________________________________________";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        printSection(Greet.greeting("Boop"));

        while (true) {
            String command = getNextCommand();

            if (command.equals("bye")) {
                printSection(Farewell.farewell());
                break;
            } else {
                printSection(command+"\n");
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
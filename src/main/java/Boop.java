public class Boop {
    static String LINE = "____________________________________________________________";

    public static void main(String[] args) {
        printSection("");
        printSection(Greet.greeting("Boop"));
        printSection(Farewell.farewell());
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
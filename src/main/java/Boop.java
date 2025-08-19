public class Boop {
    static String LINE = "____________________________________________________________";

    public static void main(String[] args) {
        printSection("");
        printSection(Greet.greeting("Boop"));
        printSection(Farewell.farewell());
    }

    public static void printSection(String content) {
        System.out.println("%s%s".formatted(content, LINE));
    }
}
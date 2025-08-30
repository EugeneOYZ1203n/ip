package app;

import errors.BoopError;

public final class UI {
    final String LINE = "____________________________________________________________";
    final String WAIT_LINE = "____________________________________________________________...";
    String name = "Boop";

    /**
     * Prints a new UI Message wrapped in lines
     * 
     * @param content Message to be shown to users
     */
    public void printSection(String content) {
        System.out.println("%s\n%s%s\n"
                .formatted(LINE, content, WAIT_LINE)
                .replaceAll("(?m)^", "\t"));
    }

    public void greeting() {
        printSection("""
                Yo! I'm %s. :D
                Wat can ye do for thee, young lass?
                """.formatted(name));
    }

    /**
     * Prints a UI Error message
     * 
     * @param error Error that has occured
     */
    public void errorMessage(BoopError error) {
        printSection("""
                Arr me bad! %s
                """.formatted(error.getMessage()));
    }
}

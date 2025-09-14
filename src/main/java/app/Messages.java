package app;

/**
 * Central repository for all user-facing message strings.
 */
public final class Messages {

    public static final String GREETING = "Yo! I'm %s. :D\nWat can ye do for thee, young lass?";

    public static final String TASKS_LOADED = "Tasks loaded miss. :D";

    public static final String ERROR_PREFIX = "Arr me bad! %s";

    public static final String COMMAND_FAREWELL = """
            Later young lass. Hope ta see ya around these here parts!
            """;

    public static final String COMMAND_DEADLINE = """
            Ho! Me remember for ye:
            \t%s
            Now ya got like %d tasks ta do!
            """;

    public static final String COMMAND_DELETE = """
            Aite little missy! It shall be gone this here task:
            \t%s
            Now ya got like %d tasks ta do!
            """;

    public static final String COMMAND_EVENT = """
            Ho! Me remember for ye:
            \t%s
            Now ya got like %d tasks ta do!
            """;

    public static final String COMMAND_FIND = """
            Here ye go, tasks that contain [%s] young lass:
            %s
            """;

    public static final String COMMAND_LIST = """
            Here ye go, ya tasks young lass:
            %s
            """;

    public static final String COMMAND_MARK = """
            Yosh! Gotcha! Marked dat as completed!
            \t%s
            """;

    public static final String COMMAND_UNMARK = """
            Aye no worries! Marked dat as not done yet!
            \t%s
            """;

    public static final String COMMAND_TODO = """
            Ho! Me remember for ye:
            \t%s
            Now ya got like %d tasks ta do!
            """;

    private Messages() {
    }

}

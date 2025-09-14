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

    public static final String ERROR_UNKNOWN_COMMAND = "Don't get wut ya sayin missy. Say it again!";

    public static final String ERROR_LOAD_SAVE_FILE = "Young lass ya save file ain't loadin rite!";

    public static final String ERROR_SAVE_SAVE_FILE = "Young lass ya save file ain't savin rite!";

    public static final String ERROR_INDEX_MISSING = "Ya missing da index!";

    public static final String ERROR_PARSE_INDEX = "Ya number ain't numbering!";

    public static final String ERROR_UNKNOWN_FLAG = "Unknown flag was used: %s";

    public static final String ERROR_DUPLICATE_FLAG = "Duplicate flag was used: %s";

    public static final String ERROR_NAME_NOT_GIVEN = "Name not given!";

    public static final String ERROR_DEADLINE_NOT_GIVEN = "Deadline not given!";

    public static final String ERROR_STARTTIME_NOT_GIVEN = "Start time not given!";

    public static final String ERROR_ENDTIME_NOT_GIVEN = "End time not given!";

    public static final String ERROR_FILTER_NOT_GIVEN = "Filter keyword not given!";

    public static final String ERROR_INVALID_DATE_FORMAT = "Incorrect date format given lass.";

    public static final String ERROR_INVALID_INDEX = "Ya given index ain't correct lass!";

    public static final String ERROR_WRONG_TYPE_TASKSAVESTRING =
            "Some issue occurred! This function is for %s not for: %s";

    public static final String ERROR_SAVE_CORRUPTED = "Save file might be corrupted, cancelling loading process!!";

    public static final String ERROR_UNKNOWN_TASK_TYPE = "There was an issue with loading missy. Unknown task type: %s";

    private Messages() {
    }

}

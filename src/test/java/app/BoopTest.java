package app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoopTest {
    private Boop boop;

    @BeforeEach
    void setUp() throws IOException {
        Path tempTaskFile = Files.createTempFile("test-tasks", ".txt");
        // Build a Boop instance with test doubles
        boop = new Boop(tempTaskFile.toString());
    }

    @Test
    void boop_greet_sendGreeting() {
        String greeting = boop.initialize();
        assertTrue(greeting.contains("Boop"),
                "Should greet user. Printed: " + greeting);
    }

    @Test
    void boop_wrongCommands_errorMessage() {
        Boop.BoopResponse out = boop.getResponse("blah");
        assertTrue(out.message.contains("Say it again!"),
                "Should print error for invalid command. Printed: " + out.message);
    }

    @Test
    void boop_addTodo_todoAdded() {
        Boop.BoopResponse out = boop.getResponse("todo read book");
        assertTrue(out.message.contains("read book"),
                "Should confirm task added. Printed: " + out.message);
        assertTrue(out.message.contains("[ ]"),
                "Should show uncompleted. Printed: " + out.message);
    }

    @Test
    void boop_addDeadline_deadlineAdded() {
        Boop.BoopResponse out = boop.getResponse("deadline return book /by 2025-09-30");
        assertTrue(out.message.contains("return book (by: Sep 30 2025)"),
                "Should confirm deadline added. Printed: " + out.message);
        assertTrue(out.message.contains("[ ]"),
                "Should show uncompleted. Printed: " + out.message);
    }

    @Test
    void boop_addEvent_eventAdded() {
        Boop.BoopResponse out = boop.getResponse("event party /from dawn /to dusk");
        assertTrue(out.message.contains("party (from: dawn to: dusk)"),
                "Should confirm deadline added. Printed: " + out.message);
        assertTrue(out.message.contains("[ ]"),
                "Should show uncompleted. Printed: " + out.message);
    }

    @Test
    void boop_listTasks_showTasks() {
        boop.getResponse("todo activityA");
        boop.getResponse("todo activityB");
        Boop.BoopResponse out = boop.getResponse("list");
        assertTrue(out.message.contains("activityA") && out.message.contains("activityB"),
                "Should list tasks. Printed: " + out.message);
    }

    @Test
    void boop_markUnmarkTasks_markCompleteIncomplete() {
        boop.getResponse("todo write essay");
        Boop.BoopResponse out1 = boop.getResponse("mark 1");
        assertTrue(out1.message.contains("[X]"),
                "Should show task completed. Printed: " + out1.message);
        Boop.BoopResponse out2 = boop.getResponse("unmark 1");
        assertTrue(out2.message.contains("[ ]"),
                "Should show task uncompleted. Printed: " + out2.message);
    }

    @Test
    void boop_deleteTasks_deleteTasks() {
        boop.getResponse("todo activityA");
        boop.getResponse("todo activityB");
        boop.getResponse("delete 1");
        Boop.BoopResponse out = boop.getResponse("list");
        assertTrue(!out.message.contains("activityA") && out.message.contains("activityB"),
                "Should no longer have deleted tasks. Printed: " + out.message);
    }

    @Test
    void boop_findTasks_showFilteredTasks() {
        boop.getResponse("todo FILTER activityA");
        boop.getResponse("todo activityB");
        boop.getResponse("todo activityC");
        boop.getResponse("todo FILTER activityD");
        boop.getResponse("todo activityE");
        boop.getResponse("todo FILTER activityF");
        Boop.BoopResponse out = boop.getResponse("find filter");
        assertTrue(out.message.contains("activityA") &&
                !out.message.contains("activityB") &&
                !out.message.contains("activityC") &&
                out.message.contains("activityD") &&
                !out.message.contains("activityE") &&
                out.message.contains("activityF"),
                "Should contain only filtered tasks. Printed: " + out.message);
        assertTrue(out.message.contains("1") &&
                !out.message.contains("2") &&
                !out.message.contains("3") &&
                out.message.contains("4") &&
                !out.message.contains("5") &&
                out.message.contains("6"),
                "Numbers remain in sequence. Printed: " + out.message);
    }

    @Test
    void boop_exitCommand_setsExitFlag() {
        Boop.BoopResponse out = boop.getResponse("bye");
        assertTrue(out.isExit,
                "Exit command should set exit flag.");
    }
}

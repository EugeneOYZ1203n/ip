package app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.*;

public class BoopTest {
    private PipedOutputStream testInWriter;
    private ByteArrayOutputStream testOut;

    private Thread appThread;

    @BeforeEach
    void setUp() throws Exception {
        // Setup fake System.in
        PipedInputStream testIn = new PipedInputStream();
        testInWriter = new PipedOutputStream(testIn);
        System.setIn(testIn);

        // Setup fake System.out
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));

        // Setup fake persistent data
        Boop.config.taskSavePathName = "./data/test/tasks.txt";

        // Run Boop.main in another thread
        appThread = new Thread(() -> Boop.main(new String[]{}));
        appThread.start();
    }

    private String sendCommand(String command, long waitMs) throws Exception {
        // Write one command line to "System.in"
        testInWriter.write((command + System.lineSeparator()).getBytes());
        testInWriter.flush();

        // Give Boop some time to process
        Thread.sleep(waitMs);

        // Capture output and reset for next command
        String output = testOut.toString();
        testOut.reset();
        return output;
    }

    @Test
    void boop_greet_sendGreeting() throws Exception {
        // Greeting should appear at start
        String greetingOut = sendCommand("", 200);
        assertTrue(greetingOut.contains("Boop"), "Should greet user. Printed: " + greetingOut);
    }

    @Test
    void boop_wrongCommands_errorMessage() throws Exception {
        // Send an invalid command
        sendCommand("", 200);
        String badCommandOut = sendCommand("blah", 200);
        assertTrue(badCommandOut.contains("Say it again!"), "Should print error for invalid command. Printed:" + badCommandOut);
    }

    @Test
    void boop_todoListCommands_displaysCorrectly() throws Exception {
        // Add a todo
        sendCommand("", 200);
        String todoOut = sendCommand("todo borrow book", 200);
        assertTrue(todoOut.contains("borrow book"), todoOut);

        // List tasks
        String listOut = sendCommand("list", 200);
        assertTrue(listOut.contains("1."), "List should show numbered tasks");
        assertTrue(listOut.contains("borrow book"), "List should show todo name");

        // Delete a task
        String deleteOut = sendCommand("delete 1", 200);
        assertTrue(deleteOut.contains("borrow book"));

        // Exit
        String byeOut = sendCommand("bye", 200);
        assertTrue(byeOut.contains("Later"));
    }

    @AfterEach
    void tearDown() throws Exception {
        // Make sure program ends
        appThread.join(1000);
    }
}

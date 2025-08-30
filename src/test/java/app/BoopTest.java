package app;

import java.io.ByteArrayOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoopTest {
    private PipedOutputStream testInWriter;
    private ByteArrayOutputStream testOut;

    private Thread appThread;
    private Path tempTaskFile;

    @BeforeEach
    void setUp() throws Exception {
        // Setup fake System.in
        PipedInputStream testIn = new PipedInputStream();
        testInWriter = new PipedOutputStream(testIn);
        System.setIn(testIn);

        // Setup fake System.out
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));

        // Setup fake persistent data with a temp file
        tempTaskFile = Files.createTempFile("test-tasks", ".txt");
        Boop.config.taskSavePathName = tempTaskFile.toString();

        // Run Boop.main in another thread
        appThread = new Thread(() -> Boop.main(new String[]{}));
        appThread.start();
    }

    private String sendCommand(String command) throws Exception {
        // Write one command line to "System.in"
        testInWriter.write((command + System.lineSeparator()).getBytes());
        testInWriter.flush();

        long start = System.currentTimeMillis();
        while (true) {
            String output = testOut.toString();
            if (output.contains("...")) {
                testOut.reset();
                return output;
            }
            if (System.currentTimeMillis() - start > 10000) {
                throw new RuntimeException("Timeout waiting for Boop response: " + output);
            }
            Thread.sleep(50);
        }
    }

    @Test
    void boop_greet_sendGreeting() throws Exception {
        // Greeting should appear at start
        String greetingOut = sendCommand("");
        assertTrue(greetingOut.contains("Boop"), "Should greet user. Printed: " + greetingOut);
    }

    @Test
    void boop_wrongCommands_errorMessage() throws Exception {
        // Send an invalid command
        String badCommandOut = sendCommand("blah");
        assertTrue(badCommandOut.contains("Say it again!"), "Should print error for invalid command. Printed:" + badCommandOut);
    }

    @AfterEach
    void tearDown() throws Exception {
        // Make sure program ends
        appThread.join(1000);
    }
}

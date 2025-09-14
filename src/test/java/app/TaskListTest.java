package app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    void setup() {
        taskList = new TaskList(new SaveHandlerStub());
    }

    @Test
    void tasklist_addLengthList_normalOperation() {
        Todo todo = new Todo("borrow book");
        taskList.addToList(todo);

        assertEquals(1, taskList.getTaskslistLength());
        assertTrue(taskList.display().contains("borrow book"));
    }

    @Test
    void tasklist_deleteTask_deleteCorrectly() {
        Event event = new Event("event", "From", "To");
        taskList.addToList(event);

        assertEquals(1, taskList.getTaskslistLength());
        assertTrue(taskList.display().contains("event"));

        Deadline deadline = new Deadline("deadline", LocalDate.parse("2003-08-20"));
        taskList.addToList(deadline);

        assertEquals(2, taskList.getTaskslistLength());
        assertTrue(taskList.display().contains("deadline"));

        taskList.deleteTask(2);

        assertEquals(1, taskList.getTaskslistLength());
        assertTrue(!taskList.display().contains("deadline"));
        assertTrue(taskList.display().contains("event"));
    }
}

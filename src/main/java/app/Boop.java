package app;

import commands.Command;
import errors.BoopError;

public class Boop {
    public static void main(String[] args) {
        UI ui = new UI();
        TaskList tasklist = new TaskList();
        Parser parser = new Parser();

        ui.greeting();

        try {
            tasklist.loadTasks();
        } catch (BoopError e) {
            ui.errorMessage(e);
        }

        boolean isExit = false;
        while (!isExit) {
            try {
                Command c = parser.getNextCommand();

                c.execute(tasklist);

                String msg = c.getMessage();
                ui.printSection(msg);

                isExit = c.isExit();
            } catch (BoopError e) {
                ui.errorMessage(e);
            }
        }
    }

    
}
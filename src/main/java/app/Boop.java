package app;

import commands.Command;
import errors.BoopError;

public class Boop {
    public static class Config {
        public String taskSavePathName = "./data/tasks.txt";
    }

    public static Config config = new Config();

    public static void main(String[] args) {
        UI ui = new UI();
        TaskList tasklist = new TaskList(config.taskSavePathName);
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

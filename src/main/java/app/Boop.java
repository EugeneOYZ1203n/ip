package app;

import commands.Command;
import errors.BoopError;

public class Boop {
    private MessageHandler messageHandler;
    private TaskList taskList;
    private Parser parser;

    public class BoopResponse {
        public String message;
        public boolean isExit;

        public BoopResponse(String message, boolean isExit) {
            this.message = message;
            this.isExit = isExit;
        }
    }

    public Boop() {
        this("./data/tasks.txt");
    }

    public Boop(String taskSavePathName) {
        messageHandler = new MessageHandler();
        taskList = new TaskList(taskSavePathName);
        parser = new Parser();
    }

    public String initialize() {
        return messageHandler.greeting();
    }

    public String loadTasks() {
        try {
            taskList.loadTasks();
        } catch (BoopError e) {
            return messageHandler.errorMessage(e);
        }

        return messageHandler.finishLoading();
    }

    public BoopResponse getResponse(String input) {
        try {
            Command c = parser.getNextCommand(input);

            c.execute(taskList);

            return new BoopResponse(c.getMessage(), c.isExit());
        } catch (BoopError e) {
            return new BoopResponse(messageHandler.errorMessage(e), false);
        }
    }
}

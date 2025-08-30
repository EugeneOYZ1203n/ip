package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import errors.BoopError;
import tasks.Task;

public final class TaskList {
    List<Task> tasks;
    SaveHandler saveHandler;

    public TaskList(String savePathName) {
        tasks = new ArrayList<>();
        saveHandler = new SaveHandler(savePathName);
    }

    public TaskList(SaveHandler saveHandler) {
        tasks = new ArrayList<>();
        this.saveHandler = saveHandler;
    }

    /**
     * Uses savehandler to retrieve saved tasks and converts them back to tasks
     * 
     * @throws BoopError
     */
    public void loadTasks() throws BoopError {
        try {
            String[] saveStrings = saveHandler.load();
            for (String saveString : saveStrings) {
                tasks.add(Task.fromSaveString(saveString));
            }
        } catch (IOException e) {
            tasks = new ArrayList<>();
            throw new BoopError("Young lass ya save file ain't loadin rite!");
        }
    }

    /**
     * Uses savehandler to save tasks and write them into save file
     * 
     * @throws BoopError
     */
    private void saveTasks() {
        try {
            String[] saveStrings = new String[tasks.size()];
            for (int i = 0; i < tasks.size(); i++) {
                saveStrings[i] = tasks.get(i).toSaveString();
            }

            saveHandler.save(saveStrings);
        } catch (IOException e) {
            throw new BoopError("Young lass ya save file ain't savin rite!");
        }
    }

    /**
     * Adds a new task to the task list
     * 
     * @param newTask Task to be added
     */
    public void addToList(Task newTask) {
        tasks.add(newTask);
        saveTasks();
    }

    /**
     * Deletes a task at the given index of the task list
     * 
     * @param index Starts from 1
     * @return Task that was deleted
     */
    public Task deleteTask(int index) {
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        saveTasks();
        return task;
    }

    /**
     * Returns the length of the list
     * 
     * @return Integer length of list
     */
    public int getTaskslistLength() {
        return tasks.size();
    }

    /**
     * Returns the String representation of the Task list
     * Only includes tasks that fit the filter regex
     * 
     * @param regex Regex used to filter tasks
     * @return String representation of the Task list
     */
    public String filterDisplay(String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= tasks.size(); i++) {
            String taskStr = tasks.get(i - 1).toString();
            if (pattern.matcher(taskStr).find()) {
                sb.append("%d.\t".formatted(i))
                        .append(taskStr)
                        .append("\n");
            }
        }

        return sb.toString();
    }

    /**
     * Returns the String representation of the Task list
     * 
     * @return String representation of the Task list
     */
    public String display() {
        return filterDisplay("");
    }

    /**
     * Marks a task at the given index of the task list
     * 
     * @param index Starts from 1
     * @return Task that was deleted
     */
    public Task mark(int index) {
        Task task = tasks.get(index - 1);
        task.complete();
        saveTasks();
        return task;
    }

    /**
     * Unmarks a task at the given index of the task list
     * 
     * @param index Starts from 1
     * @return Task that was deleted
     */
    public Task unmark(int index) {
        Task task = tasks.get(index - 1);
        task.uncomplete();
        saveTasks();
        return task;
    }

    /**
     * Checks if a given index is valid
     * 
     * @param index Starts from 1
     * @return Whether the index is valid
     */
    public boolean isValidIndex(int index) {
        return index <= tasks.size() && index >= 1;
    }
}

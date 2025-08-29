package bot.task;

import bot.exception.InvalidCommandException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Add To-do task to task list
     *
     * @param taskName bot.task.Task name to be added to taskList
     * @return The To-do task that is added to task list
     */
    public Task addTask(String taskName) {
        Task newTask = new Todo(taskName); // create new to-do task
        this.taskList.add(newTask); // add task to task list
        return newTask;
    }

    /**
     * Add bot.task.Deadline task to task list
     *
     * @param taskName bot.task.Task name to be added to taskList
     * @param deadline Date time of the task deadline
     * @throws IllegalArgumentException If string date time is in unsupported format
     * @return The new bot.task.Deadline task that is added to task list
     */
    public Task addTask(String taskName, String deadline) throws IllegalArgumentException {
        Task newTask = new Deadline(taskName, deadline); // create new deadline task
        this.taskList.add(newTask);
        return newTask;
    }

    /**
     * Add bot.task.Event task to task list
     *
     * @param taskName bot.task.Task name to be added to taskList
     * @param startTime start date time of the event task
     * @param endTime end date time of the event task
     * @throws IllegalArgumentException If string date time is in unsupported format
     * @return The new bot.task.Event task that is added to task list
     */
    public Task addTask(String taskName, String startTime, String endTime)
            throws IllegalArgumentException {
        Task newTask = new Event(taskName, startTime, endTime); // create new event task
        this.taskList.add(newTask); // add task to task list
        return newTask;
    }

    /**
     * Remove the task from task list and print confirmation message
     *
     * @param index bot.task.Task index position in bot.task.Task List, starting from 1
     * @throws InvalidCommandException if index is out of bound
     * @return The task that is removed from task list
     */
    public Task removeTask(int index) throws InvalidCommandException {
        // Validation for index number
        if (index > this.taskList.size() || index < 1) {
            throw new InvalidCommandException("Invalid task number");
        }

        // Remove the task from task list
        // Index given starts from 1
        return this.taskList.remove(index - 1);
    }

    /**
     * Set task status as done
     *
     * @param index bot.task.Task index position in bot.task.Task List, starts from 1
     * @throws InvalidCommandException if index is out of bound
     * @return The task which status is mark as done
     */
    public Task markTaskAsDone(int index) throws InvalidCommandException {
        // Validation for index number
        if (index > this.taskList.size() || index < 1) {
            throw new InvalidCommandException("Invalid task number");
        }

        Task task = this.taskList.get(index - 1); // Index given starts from 1
        task.markDone(); // Set task status to done

        return task;
    }

    /**
     * Set task status as not done
     *
     * @param index bot.task.Task index position in bot.task.Task List, starts from 1
     * @throws InvalidCommandException if index is out of bound
     * @return The task which status is mark as not done
     */
    public Task markTaskAsNotDone(int index) throws InvalidCommandException {
        // Validation for index number
        if (index > this.taskList.size() || index < 1) {
            throw new InvalidCommandException("Invalid task number");
        }

        Task task = this.taskList.get(index - 1); // Index given starts from 1
        task.markNotDone(); // Set task status to not done

        return task;
    }

    /**
     * Search for tasks whose names contain the specified keyword (case-insensitive).
     *
     * @param keyword the keyword to search for within task names
     * @return a list of tasks whose names contain the keyword, or an empty list if no matches are found
     */
    public List<Task> searchTasksByName(String keyword) {
        List<Task> filteredList = new ArrayList<>();

        // Add to filter list if task name matches keyword
        for (Task task : this.taskList) {
            if (task.isNameMatch(keyword)) {
                filteredList.add(task);
            }
        }

        return filteredList;
    }

    /**
     * Getter for task list
     *
     * @return bot.task.Task list in {@code List<bot.task.Task>}
     */
    public List<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Return the count for task list
     *
     * @return bot.task.Task count in task list
     */
    public int getSize() {
        return this.taskList.size();
    }
}

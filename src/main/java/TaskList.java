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
     * @param taskName Task name to be added to taskList
     * @return The To-do task that is added to task list
     */
    public Task addTask(String taskName) {
        Task newTask = new Todo(taskName); // create new to-do task
        this.taskList.add(newTask); // add task to task list
        return newTask;
    }

    /**
     * Add Deadline task to task list
     *
     * @param taskName Task name to be added to taskList
     * @param deadline Date time of the task deadline
     * @throws IllegalArgumentException If string date time is in unsupported format
     * @return The new Deadline task that is added to task list
     */
    public Task addTask(String taskName, String deadline) throws IllegalArgumentException {
        Task newTask = new Deadline(taskName, deadline); // create new deadline task
        this.taskList.add(newTask);
        return newTask;
    }

    /**
     * Add Event task to task list
     *
     * @param taskName Task name to be added to taskList
     * @param startTime start date time of the event task
     * @param endTime end date time of the event task
     * @throws IllegalArgumentException If string date time is in unsupported format
     * @return The new Event task that is added to task list
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
     * @param index Task index position in Task List, starting from 1
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
     * @param index Task index position in Task List, starts from 1
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
     * @param index Task index position in Task List, starts from 1
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
     * Getter for task list
     *
     * @return Task list in {@code List<Task>}
     */
    public List<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Return the count for task list
     *
     * @return Task count in task list
     */
    public int getSize() {
        return this.taskList.size();
    }
}

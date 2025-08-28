import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bot {
    /** Name of the bot */
    private final String name;

    /** List of text entered by user **/
    private List<Task> taskList;

    /** File service API that write and read from file **/
    private final FileServices fileServices;

    private final Ui ui;

    public Bot(String name) {
        this.name = name;
        this.ui = new Ui();
        this.taskList = new ArrayList<>();
        this.fileServices = new FileServices("data/taskData.txt");
        loadTaskList(); // Load task list from file
    }

    /**
     * Getter function for bot name
     */
    public String getName() {
        return name;
    }

    /**
     * Add To-do task to task list and write task string to file
     *
     * @param taskName Task name to be added to taskList
     */
    public void addTask(String taskName) {
        try {
            Task newTask = new Todo(taskName); // create new to-do task
            this.taskList.add(newTask); // add task to task list

            // Write task list to file
            this.fileServices.writeToFile(this.taskList);

            // Print success message
            ui.showAddTaskSuccess(newTask, taskList.size());
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Add Deadline task to task list and write task string to file
     *
     * @param taskName Task name to be added to taskList
     * @param deadline Date time of the task deadline
     */
    public void addTask(String taskName, String deadline) {
        try {
            Task newTask = new Deadline(taskName, deadline); // create new deadline task
            this.taskList.add(newTask); // add task to task list

            // Write task list to file
            this.fileServices.writeToFile(this.taskList);

            // Print success message
            ui.showAddTaskSuccess(newTask, taskList.size());
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Add Event task to task list and write task string to file
     *
     * @param taskName Task name to be added to taskList
     * @param startTime start date time of the event task
     * @param endTime end date time of the event task
     */
    public void addTask(String taskName, String startTime, String endTime) {
        try {
            Task newTask = new Event(taskName, startTime, endTime); // create new event task
            this.taskList.add(newTask); // add task to task list

            // Write task list to file
            this.fileServices.writeToFile(this.taskList);

            // Print success message
            ui.showAddTaskSuccess(newTask, taskList.size());
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Remove the task from task list and print confirmation message
     *
     * @param index Task index position in Task List, starting from 1
     * @throws InvalidCommandException if index is out of bound
     */
    public void removeTask(int index) throws InvalidCommandException {
        // Validation for index number
        if (index > this.taskList.size() || index < 1) {
            throw new InvalidCommandException("Invalid task number");
        }

        // Remove the task from task list
        Task task = this.taskList.remove(index - 1); // Index given starts from 1

        try {
            // Write task list to file
            this.fileServices.writeToFile(this.taskList);

            // Print confirmation message and list count
            ui.showRemoveTaskSuccess(task, taskList.size());
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Display every task in taskList
     */
    public void listTasks() {
        ui.showTaskList(this.taskList);
    }

    /**
     * Set task status as done and print confirmation message
     *
     * @param index Task index position in Task List, starts from 1
     * @throws InvalidCommandException if index is out of bound
     */
    public void markTaskAsDone(int index) throws InvalidCommandException {
        // Validation for index number
        if (index > this.taskList.size() || index < 1) {
            throw new InvalidCommandException("Invalid task number");
        }

        Task task = this.taskList.get(index - 1); // Index given starts from 1
        task.markDone(); // Set task status to done

        try {
            // Write task list to file
            this.fileServices.writeToFile(this.taskList);

            // Print confirmation message and new status
            ui.showMarkTaskSuccess(task);
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Set task status as not done and print confirmation message
     *
     * @param index Task index position in Task List, starts from 1
     * @throws InvalidCommandException if index is out of bound
     */
    public void markTaskAsNotDone(int index) throws InvalidCommandException {
        // Validation for index number
        if (index > this.taskList.size() || index < 1) {
            throw new InvalidCommandException("Invalid task number");
        }

        Task task = this.taskList.get(index - 1); // Index given starts from 1
        task.markNotDone(); // Set task status to not done

        try {
            // Write task list to file
            this.fileServices.writeToFile(this.taskList);

            // Print confirmation message and new status
            ui.showUnmarkTaskSuccess(task);
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Loads the task list from the file using the {@code FileServices}.
     * <p>
     * This method attempts to read the task data from the file and populates
     * the instance's task list.
     * Any {@link IOException} that occurs during file reading or an
     * {@link IllegalArgumentException} resulting from improperly formatted data
     * within the file will be caught, an error message will be printed to the
     * console, and the program will continue with an empty task list.
     */
    private void loadTaskList() {
        try {
            this.taskList = fileServices.readFromFile();
        } catch (IOException | IllegalArgumentException e) {
            ui.showError(e.getMessage());
        }
    }
}

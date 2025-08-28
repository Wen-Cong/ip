import java.io.IOException;

public class Bot {
    /** Name of the bot */
    private final String name;

    /** List of tasks **/
    private TaskList taskList;

    /** File service API that write and read from file **/
    private final FileServices fileServices;

    private final Ui ui;

    public Bot(String name) {
        this.name = name;
        this.ui = new Ui();
        this.fileServices = new FileServices("data/taskData.txt");

        try {
            this.taskList = new TaskList(fileServices.readFromFile());
        } catch (IOException | IllegalArgumentException e) {
            ui.showError(e.getMessage());
            taskList = new TaskList();
        }
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
            Task newTask = taskList.addTask(taskName);

            // Write task list to file
            this.fileServices.writeToFile(taskList);

            // Print success message
            ui.showAddTaskSuccess(newTask, taskList.getSize());
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
            Task newTask = taskList.addTask(taskName, deadline);

            // Write task list to file
            this.fileServices.writeToFile(taskList);

            // Print success message
            ui.showAddTaskSuccess(newTask, taskList.getSize());
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
            Task newTask = taskList.addTask(taskName, startTime, endTime);

            // Write task list to file
            this.fileServices.writeToFile(taskList);

            // Print success message
            ui.showAddTaskSuccess(newTask, taskList.getSize());
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
        Task task = taskList.removeTask(index);

        try {
            // Write task list to file
            this.fileServices.writeToFile(taskList);

            // Print confirmation message and list count
            ui.showRemoveTaskSuccess(task, taskList.getSize());
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Display every task in taskList
     */
    public void listTasks() {
        ui.showTaskList(taskList.getTaskList());
    }

    /**
     * Set task status as done and print confirmation message
     *
     * @param index Task index position in Task List, starts from 1
     * @throws InvalidCommandException if index is out of bound
     */
    public void markTaskAsDone(int index) throws InvalidCommandException {
        Task task = taskList.markTaskAsDone(index);

        try {
            // Write task list to file
            this.fileServices.writeToFile(taskList);

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
        Task task = taskList.markTaskAsNotDone(index);

        try {
            // Write task list to file
            this.fileServices.writeToFile(taskList);

            // Print confirmation message and new status
            ui.showUnmarkTaskSuccess(task);
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }
}

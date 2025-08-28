import java.io.IOException;

public class Bot {
    /** Name of the bot */
    private final String name;

    /** List of tasks **/
    private TaskList taskList;

    /** File service API that write and read from file **/
    private final FileServices fileServices;

    private final Ui ui;

    public Bot(String name, String storagePath) {
        this.name = name;
        this.ui = new Ui();
        this.fileServices = new FileServices(storagePath);

        try {
            this.taskList = new TaskList(fileServices.readFromFile());
        } catch (IOException | IllegalArgumentException e) {
            ui.showError(e.getMessage());
            taskList = new TaskList();
        }
    }

    public void run() {
        boolean isExit = false;

        // Print greeting message
        ui.showWelcome(getName());

        // Read user commands and perform actions repeatedly till "bye" command
        // to exit program
        while (!isExit) {
            // Prompt user and read input from console
            String input = ui.promptCommand();

            // Split input by space into command type (first word)
            // and other command information (e.g. input param)
            // commandInfo[0] = command instruction
            // commandInfo[1] = other command info
            String[] commandInfo = input.split(" ", 2);

            try {
                // Redirect command type to invoke respective operations
                switch (commandInfo[0]) {
                case "bye": // Exit program
                    ui.showExitMessage();
                    isExit = true;
                    break;

                case "list": // Display task list
                    listTasks();
                    break;

                case "mark": // Mark a task as done
                    // Validate command format, re-prompt if incorrect command format
                    if (commandInfo.length != 2 || !commandInfo[1].matches("\\d+")) {
                        throw new InvalidCommandException(
                                "Please ensure command is in this format: " +
                                        "mark <Task Index>");
                    }

                    markTaskAsDone(Integer.parseInt(commandInfo[1]));
                    break;

                case "unmark": // Mark a task as not done
                    // Validate command format, re-prompt if incorrect command format
                    if (commandInfo.length != 2 || !commandInfo[1].matches("\\d+")) {
                        throw new InvalidCommandException(
                                "Please ensure command is in this format: " +
                                        "mark <Task Index>");
                    }

                    markTaskAsNotDone(Integer.parseInt(commandInfo[1]));
                    break;

                case "todo": // Add to-do task to task list
                    // Validate command format, re-prompt if incorrect command format
                    if (commandInfo.length != 2) {
                        throw new InvalidCommandException(
                                "Please ensure command is in this format: " +
                                        "todo <Task Name>");
                    }

                    // Add to-do task to task list
                    addTask(commandInfo[1]);
                    break;

                case "deadline": // Add deadline task to task list
                    // Validate command format, re-prompt if incorrect command format
                    if (commandInfo.length != 2) {
                        throw new InvalidCommandException(
                                "Please ensure command is in this format: " +
                                        "deadline <Task Name> /by <Date>");
                    }

                    // Split the command info with by "/by" to extract task name and deadline
                    String[] deadlineInfo = commandInfo[1].split(" /by ");

                    // Validate command format, re-prompt if incorrect command format
                    if (deadlineInfo.length != 2) {
                        throw new InvalidCommandException(
                                "Please ensure command is in this format: " +
                                        "deadline <Task Name> /by <Date>");
                    }

                    // Add deadline task to task list
                    addTask(deadlineInfo[0], deadlineInfo[1]);
                    break;

                case "event": // Add event task to task list
                    // Validate command format, re-prompt if incorrect command format
                    if (commandInfo.length != 2) {
                        throw new InvalidCommandException(
                                "Please ensure command is in this format: " +
                                        "event <Task Name> /from <Start Date> /to <End Date>");
                    }

                    // Split the command info with by "/from" to extract task name
                    // eventInfo[0] = task name
                    // eventInfo[1] = start date and end date
                    String[] eventInfo = commandInfo[1].split(" /from ");

                    // Validate command format, re-prompt if incorrect command format
                    if (eventInfo.length != 2) {
                        throw new InvalidCommandException(
                                "Please ensure command is in this format: " +
                                        "event <Task Name> /from <Start Date> /to <End Date>");
                    }

                    // Extract start and end date from the separated eventInfo (taskName, date)
                    String[] dateInfo = eventInfo[1].split(" /to ");

                    // Validate command format, re-prompt if incorrect command format
                    if (dateInfo.length != 2) {
                        throw new InvalidCommandException(
                                "Please ensure command is in this format: " +
                                        "event <Task Name> /from <Start Date> /to <End Date>");
                    }

                    // Add event task to task list
                    addTask(eventInfo[0], dateInfo[0], dateInfo[1]);
                    break;

                case "delete":
                    // Validate command format, re-prompt if incorrect command format
                    if (commandInfo.length != 2 || !commandInfo[1].matches("\\d+")) {
                        throw new InvalidCommandException(
                                "Please ensure command is in this format: " +
                                        "delete <Task Index>");
                    }

                    // Delete task from task list
                    removeTask(Integer.parseInt(commandInfo[1]));
                    break;

                default:
                    throw new InvalidCommandException("No such command");
                }
            } catch (InvalidCommandException e) {
                ui.showError(e.getMessage());
            }
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

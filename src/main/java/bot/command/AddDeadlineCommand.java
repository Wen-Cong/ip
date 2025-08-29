package bot.command;

import bot.service.FileServices;
import bot.exception.InvalidCommandException;
import bot.task.TaskList;
import bot.ui.Ui;
import bot.task.Task;

/**
 * Represents a command to add a deadline task to the task list.
 * This command parses deadline information from user input and creates a new deadline task.
 * The command format should be: "deadline <task name> /by <deadline date>"
 */
public class AddDeadlineCommand extends Command {
    private final String[] commandInfo;

    /**
     * Constructs an AddDeadlineCommand with the provided command information.
     *
     * @param commandInfo an array containing the command details where:
     *                    - commandInfo[0] should be "deadline"
     *                    - commandInfo[1] should contain the task name and deadline separated by " /by "
     */
    public AddDeadlineCommand(String[] commandInfo) {
        this.commandInfo = commandInfo;
    }

    /**
     * Executes the add deadline command by parsing the command information,
     * validating the format, creating a new deadline task, and saving it to file.
     *
     * @param taskList the task list to which the new deadline task will be added
     * @param ui the user interface for displaying messages and errors
     * @param fileServices the file services for writing the updated task list to storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, FileServices fileServices) {
        try {
            // Validate command format, re-prompt if incorrect command format
            if (commandInfo.length != 2) {
                throw new InvalidCommandException(
                        "Please ensure command is in this format: " +
                                "deadline <Task Name> /by <Date>");
            }

            // Split the command info with by " /by " to extract task name and deadline
            String[] deadlineInfo = commandInfo[1].split(" /by ");

            // Validate command format, re-prompt if incorrect command format
            if (deadlineInfo.length != 2) {
                throw new InvalidCommandException(
                        "Please ensure command is in this format: " +
                                "deadline <bot.task.Task Name> /by <Date>");
            }

            String taskName = deadlineInfo[0];
            String deadline = deadlineInfo[1];

            // Add deadline task
            Task newTask = taskList.addTask(taskName, deadline);

            // Write task list to file
            fileServices.writeToFile(taskList);

            // Print success message
            ui.showAddTaskSuccess(newTask, taskList.getSize());
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

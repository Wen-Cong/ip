package bot.command;

import bot.service.FileServices;
import bot.exception.InvalidCommandException;
import bot.task.TaskList;
import bot.ui.Ui;
import bot.task.Task;

/**
 * Represents a command to unmark a task (mark it as not completed).
 * This command takes a task index and marks the corresponding task as not done.
 * The command format should be: "unmark <task index>"
 */
public class UnmarkTaskCommand extends Command {
    private final String[] commandInfo;

    /**
     * Constructs an UnmarkTaskCommand with the provided command information.
     *
     * @param commandInfo an array containing the command details where:
     *                    - commandInfo[0] should be "unmark"
     *                    - commandInfo[1] should contain the task index as a positive integer
     */
    public UnmarkTaskCommand(String[] commandInfo) {
        this.commandInfo = commandInfo;
    }

    /**
     * Executes the unmark task command by parsing the task index, validating the format,
     * marking the specified task as not completed, and saving the updated task list to file.
     *
     * @param taskList the task list containing the task to be unmarked
     * @param ui the user interface for displaying messages and errors
     * @param fileServices the file services for writing the updated task list to storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, FileServices fileServices) {
        try {
            // Validate command format, re-prompt if incorrect command format
            if (commandInfo.length != 2 || !commandInfo[1].matches("\\d+")) {
                throw new InvalidCommandException(
                        "Please ensure command is in this format: " +
                                "unmark <bot.task.Task Index>");
            }

            int index = Integer.parseInt(commandInfo[1]);

            // Unmark bot.task.Task
            Task task = taskList.markTaskAsNotDone(index);

            // Write task list to file
            fileServices.writeToFile(taskList);

            // Print confirmation message and new status
            ui.showUnmarkTaskSuccess(task);
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

package bot.command;

import bot.service.FileServices;
import bot.exception.InvalidCommandException;
import bot.task.TaskList;
import bot.ui.Ui;
import bot.task.Task;

/**
 * Represents a command to remove a task from the task list.
 * This command takes a task index and removes the corresponding task from the list.
 * The command format should be: "delete <task index>"
 */
public class RemoveTaskCommand extends Command {
    private final String[] commandInfo;

    /**
     * Constructs a RemoveTaskCommand with the provided command information.
     *
     * @param commandInfo an array containing the command details where:
     *                    - commandInfo[0] should be "delete"
     *                    - commandInfo[1] should contain the task index as a positive integer
     */
    public RemoveTaskCommand(String[] commandInfo) {
        this.commandInfo = commandInfo;
    }

    /**
     * Executes the remove task command by parsing the task index, validating the format,
     * removing the specified task from the task list, and saving the updated task list to file.
     *
     * @param taskList the task list from which the task will be removed
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
                                "delete <Task Index>");
            }

            int index = Integer.parseInt(commandInfo[1]);

            Task task = taskList.removeTask(index);

            // Write task list to file
            fileServices.writeToFile(taskList);

            // Print confirmation message and list count
            ui.showRemoveTaskSuccess(task, taskList.getSize());
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

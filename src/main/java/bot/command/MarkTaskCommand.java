package bot.command;

import bot.service.FileServices;
import bot.exception.InvalidCommandException;
import bot.task.TaskList;
import bot.ui.ResponseMessage;
import bot.task.Task;

/**
 * Represents a command to mark a task as completed.
 * This command takes a task index and marks the corresponding task as done.
 * The command format should be: "mark <task index>"
 */
public class MarkTaskCommand extends Command {
    private final String[] commandInfo;

    /**
     * Constructs a MarkTaskCommand with the provided command information.
     *
     * @param commandInfo an array containing the command details where:
     *                    - commandInfo[0] should be "mark"
     *                    - commandInfo[1] should contain the task index as a positive integer
     */
    public MarkTaskCommand(String[] commandInfo) {
        this.commandInfo = commandInfo;
    }

    /**
     * Executes the mark task command by parsing the task index, validating the format,
     * marking the specified task as completed, and saving the updated task list to file.
     *
     * @param taskList the task list containing the task to be marked
     * @param fileServices the file services for writing the updated task list to storage
     */
    @Override
    public void execute(TaskList taskList, FileServices fileServices) {
        try {
            // Validate command format, re-prompt if incorrect command format
            if (commandInfo.length != 2 || !commandInfo[1].matches("\\d+")) {
                throw new InvalidCommandException(
                        "Please ensure command is in this format: " +
                                "mark <Task Index>");
            }

            int index = Integer.parseInt(commandInfo[1]);

            // Mark task
            Task task = taskList.markTaskAsDone(index);

            // Write task list to file
            fileServices.writeToFile(taskList);

            // Set confirmation message and new status as response
            super.setResponse(ResponseMessage.getMarkTaskSuccessMessage(task));
        } catch (Exception e) {
            super.setResponse(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

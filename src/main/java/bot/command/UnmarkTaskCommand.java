package bot.command;

import bot.service.FileServices;
import bot.exception.InvalidCommandException;
import bot.task.TaskList;
import bot.ui.Ui;
import bot.task.Task;

public class UnmarkTaskCommand extends Command {
    private final String[] commandInfo;

    public UnmarkTaskCommand(String[] commandInfo) {
        this.commandInfo = commandInfo;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, FileServices fileServices) {
        try {
            // Validate command format, re-prompt if incorrect command format
            if (commandInfo.length != 2 || !commandInfo[1].matches("\\d+")) {
                throw new InvalidCommandException(
                        "Please ensure command is in this format: " +
                                "unmark <Task Index>");
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

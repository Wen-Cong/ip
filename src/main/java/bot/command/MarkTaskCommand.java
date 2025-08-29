package bot.command;

import bot.service.FileServices;
import bot.exception.InvalidCommandException;
import bot.task.TaskList;
import bot.ui.Ui;
import bot.task.Task;

public class MarkTaskCommand extends Command {
    private final String[] commandInfo;

    public MarkTaskCommand(String[] commandInfo) {
        this.commandInfo = commandInfo;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, FileServices fileServices) {
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

            // Print confirmation message and new status
            ui.showMarkTaskSuccess(task);
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

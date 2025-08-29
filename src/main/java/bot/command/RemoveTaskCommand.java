package bot.command;

import bot.service.FileServices;
import bot.exception.InvalidCommandException;
import bot.task.TaskList;
import bot.ui.Ui;
import bot.task.Task;

public class RemoveTaskCommand extends Command {
    private final String[] commandInfo;

    public RemoveTaskCommand(String[] commandInfo) {
        this.commandInfo = commandInfo;
    }

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

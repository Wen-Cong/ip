package bot.command;

import bot.service.FileServices;
import bot.task.TaskList;
import bot.ui.Ui;

/**
 * Represents an invalid command that is executed when an unrecognized command is entered.
 * This command displays an error message to the user indicating that the command is not valid.
 */
public class InvalidCommand extends Command {
    public InvalidCommand() {}

    /**
     * Executes the invalid command by displaying an error message to the user.
     *
     * @param taskList the task list (not used in this command)
     * @param ui the user interface for displaying the error message
     * @param fileServices the file services (not used in this command)
     */
    @Override
    public void execute(TaskList taskList, Ui ui, FileServices fileServices) {
        ui.showError("No such command");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

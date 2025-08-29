package bot.command;

import bot.service.FileServices;
import bot.task.TaskList;
import bot.ui.Ui;

public class InvalidCommand extends Command {
    public InvalidCommand() {}

    @Override
    public void execute(TaskList taskList, Ui ui, FileServices fileServices) {
        ui.showError("No such command");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

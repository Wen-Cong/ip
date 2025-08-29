package bot.command;

import bot.service.FileServices;
import bot.task.TaskList;
import bot.ui.Ui;

public class ExitCommand extends Command {
    private boolean isExecuted = false;

    public ExitCommand() {}

    @Override
    public void execute(TaskList taskList, Ui ui, FileServices fileServices) {
        ui.showExitMessage();
        isExecuted = true;
    }

    @Override
    public boolean isExit() {
        return isExecuted;
    }
}

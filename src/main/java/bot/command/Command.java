package bot.command;

import bot.service.FileServices;
import bot.task.TaskList;
import bot.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, FileServices fileServices);

    public abstract boolean isExit();
}

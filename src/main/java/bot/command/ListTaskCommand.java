package bot.command;

import bot.service.FileServices;
import bot.task.TaskList;
import bot.ui.Ui;

public class ListTaskCommand extends Command {
    public ListTaskCommand() {}

    @Override
    public void execute(TaskList taskList, Ui ui, FileServices fileServices) {
        ui.showTaskList(taskList.getTaskList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

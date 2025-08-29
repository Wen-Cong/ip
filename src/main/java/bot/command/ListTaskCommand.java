package bot.command;

import bot.service.FileServices;
import bot.task.TaskList;
import bot.ui.Ui;

/**
 * Represents a command to display the list of all tasks.
 * This command retrieves and displays all tasks currently in the task list.
 */
public class ListTaskCommand extends Command {
    public ListTaskCommand() {}

    /**
     * Executes the list task command by displaying all tasks in the task list.
     *
     * @param taskList the task list containing tasks to be displayed
     * @param ui the user interface for displaying the task list
     * @param fileServices the file services (not used in this command)
     */
    @Override
    public void execute(TaskList taskList, Ui ui, FileServices fileServices) {
        ui.showTaskList(taskList.getTaskList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

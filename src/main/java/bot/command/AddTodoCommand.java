package bot.command;

import bot.service.FileServices;
import bot.exception.InvalidCommandException;
import bot.task.TaskList;
import bot.ui.Ui;
import bot.task.Task;

public class AddTodoCommand extends Command {
    private final String[] commandInfo;

    public AddTodoCommand(String[] commandInfo) {
        this.commandInfo = commandInfo;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, FileServices fileServices) {
        try {
            // Validate command format, re-prompt if incorrect command format
            if (commandInfo.length != 2) {
                throw new InvalidCommandException(
                        "Please ensure command is in this format: " +
                                "todo <Task Name>");
            }

            String taskName = commandInfo[1];

            // Add To-do task
            Task newTask = taskList.addTask(taskName);

            // Write task list to file
            fileServices.writeToFile(taskList);

            // Print success message
            ui.showAddTaskSuccess(newTask, taskList.getSize());
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

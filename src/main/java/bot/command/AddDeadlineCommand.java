package bot.command;

import bot.service.FileServices;
import bot.exception.InvalidCommandException;
import bot.task.TaskList;
import bot.ui.Ui;
import bot.task.Task;

public class AddDeadlineCommand extends Command {
    private final String[] commandInfo;

    public AddDeadlineCommand(String[] commandInfo) {
        this.commandInfo = commandInfo;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, FileServices fileServices) {
        try {
            // Validate command format, re-prompt if incorrect command format
            if (commandInfo.length != 2) {
                throw new InvalidCommandException(
                        "Please ensure command is in this format: " +
                                "deadline <bot.task.Task Name> /by <Date>");
            }

            // Split the command info with by " /by " to extract task name and deadline
            String[] deadlineInfo = commandInfo[1].split(" /by ");

            // Validate command format, re-prompt if incorrect command format
            if (deadlineInfo.length != 2) {
                throw new InvalidCommandException(
                        "Please ensure command is in this format: " +
                                "deadline <bot.task.Task Name> /by <Date>");
            }

            String taskName = deadlineInfo[0];
            String deadline = deadlineInfo[1];

            // Add deadline task
            Task newTask = taskList.addTask(taskName, deadline);

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

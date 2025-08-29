package bot.command;

import bot.service.FileServices;
import bot.exception.InvalidCommandException;
import bot.task.TaskList;
import bot.ui.Ui;
import bot.task.Task;

public class AddEventCommand extends Command {
    private final String[] commandInfo;

    public AddEventCommand(String[] commandInfo) {
        this.commandInfo = commandInfo;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, FileServices fileServices) {
        try {
            // Validate command format, re-prompt if incorrect command format
            if (commandInfo.length != 2) {
                throw new InvalidCommandException(
                        "Please ensure command is in this format: " +
                                "event <bot.task.Task Name> /from <Start Date> /to <End Date>");
            }

            // Split the command info with by "/from" to extract task name
            // eventInfo[0] = task name
            // eventInfo[1] = start date and end date
            String[] eventInfo = commandInfo[1].split(" /from ");

            // Validate command format, re-prompt if incorrect command format
            if (eventInfo.length != 2) {
                throw new InvalidCommandException(
                        "Please ensure command is in this format: " +
                                "event <bot.task.Task Name> /from <Start Date> /to <End Date>");
            }

            // Extract start and end date from the separated eventInfo (taskName, date)
            String[] dateInfo = eventInfo[1].split(" /to ");

            // Validate command format, re-prompt if incorrect command format
            if (dateInfo.length != 2) {
                throw new InvalidCommandException(
                        "Please ensure command is in this format: " +
                                "event <Task Name> /from <Start Date> /to <End Date>");
            }

            String taskName = eventInfo[0];
            String from = dateInfo[0];
            String to = dateInfo[1];

            // Add event task
            Task newTask = taskList.addTask(taskName, from, to);

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

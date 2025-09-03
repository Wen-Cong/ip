package bot.command;

import bot.service.FileServices;
import bot.exception.InvalidCommandException;
import bot.task.TaskList;
import bot.ui.ResponseMessage;
import bot.task.Task;

/**
 * Represents a command to add an event task to the task list.
 * This command parses event information from user input and creates a new event task.
 * The command format should be: "event <task name> /from <start date> /to <end date>"
 */
public class AddEventCommand extends Command {
    private final String[] commandInfo;

    /**
     * Constructs an AddEventCommand with the provided command information.
     *
     * @param commandInfo an array containing the command details where:
     *                    - commandInfo[0] should be "event"
     *                    - commandInfo[1] should contain the task name, start date, and end date
     *                      in the format: "<task name> /from <start date> /to <end date>"
     */
    public AddEventCommand(String[] commandInfo) {
        this.commandInfo = commandInfo;
    }

    /**
     * Executes the add event command by parsing the command information,
     * validating the format, creating a new event task, and saving it to file.
     *
     * @param taskList the task list to which the new event task will be added
     * @param fileServices the file services for writing the updated task list to storage
     */
    @Override
    public void execute(TaskList taskList, FileServices fileServices) {
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

            // Set success message
            super.setResponse(ResponseMessage.getAddTaskSuccessMessage(newTask, taskList.getSize()));
        } catch (Exception e) {
            super.setResponse(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

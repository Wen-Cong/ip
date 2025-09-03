package bot.command;

import bot.service.FileServices;
import bot.exception.InvalidCommandException;
import bot.task.TaskList;
import bot.ui.ResponseMessage;
import bot.task.Task;

/**
 * Represents a command to add a to-do task to the task list.
 * This command parses to-do information from user input and creates a new to-do task.
 * The command format should be: "todo <task name>"
 */
public class AddTodoCommand extends Command {
    private final String[] commandInfo;

    /**
     * Constructs an AddTodoCommand with the provided command information.
     *
     * @param commandInfo an array containing the command details where:
     *                    - commandInfo[0] should be "todo"
     *                    - commandInfo[1] should contain the task name
     */
    public AddTodoCommand(String[] commandInfo) {
        this.commandInfo = commandInfo;
    }

    /**
     * Executes the add to-do command by parsing the command information,
     * validating the format, creating a new to-do task, and saving it to file.
     *
     * @param taskList the task list to which the new to-do task will be added
     * @param fileServices the file services for writing the updated task list to storage
     */
    @Override
    public void execute(TaskList taskList, FileServices fileServices) {
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

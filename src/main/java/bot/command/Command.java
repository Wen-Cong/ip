package bot.command;

import bot.service.FileServices;
import bot.task.TaskList;
import bot.ui.Ui;

/**
 * Abstract base class for all commands in the bot application.
 * This class defines the common interface that all command classes must implement.
 * Each concrete command class should extend this class and provide specific
 * implementations for executing the command and determining if it should exit the application.
 */
public abstract class Command {
    /**
     * Executes the command with the given parameters.
     * This method should contain the specific logic for each command type.
     *
     * @param taskList the task list to operate on
     * @param ui the user interface for displaying messages and handling user interaction
     * @param fileServices the file services for reading from and writing to storage
     */
    public abstract void execute(TaskList taskList, Ui ui, FileServices fileServices);

    /**
     * Determines whether this command should cause the application to exit.
     *
     * @return true if the application should exit after executing this command, false otherwise
     */
    public abstract boolean isExit();
}

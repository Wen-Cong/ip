package bot;

import bot.command.Command;
import bot.service.FileServices;
import bot.task.TaskList;
import bot.ui.Ui;
import bot.util.Parser;

import java.io.IOException;

/**
 * Main class for the bot application that manages tasks and user interactions.
 * This class orchestrates the entire application flow, handling user input,
 * command execution, and data persistence.
 */
public class Bot {
    /** Name of the bot */
    private final String name;

    /** List of tasks **/
    private TaskList taskList;

    /** File service API that write and read from file **/
    private final FileServices fileServices;

    private final Ui ui;

    /**
     * Constructs a Bot instance with the specified name and storage path.
     * Initializes the user interface, file services, and loads existing tasks
     * from the storage file if available.
     *
     * @param name the name of the bot to be displayed to users
     * @param storagePath the file path where task data will be stored
     */
    public Bot(String name, String storagePath) {
        this.name = name;
        this.ui = new Ui();
        this.fileServices = new FileServices(storagePath);

        try {
            this.taskList = new TaskList(fileServices.readFromFile());
        } catch (IOException | IllegalArgumentException e) {
            ui.showError(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Runs the main application loop.
     * Displays the welcome message, prompts for user input, parses commands,
     * executes them, and continues until an exit command is received.
     */
    public void run() {
        boolean isExit = false;

        // Print greeting message
        ui.showWelcome(name);

        // Keep running till Exit command is executed
        while (!isExit) {
            // Prompt user and read input from console
            String input = ui.promptCommand();

            // Parse user input into bot.command.Command object
            Command command = Parser.parse(input);

            command.execute(taskList, ui, fileServices);

            isExit = command.isExit();
        }
    }
}

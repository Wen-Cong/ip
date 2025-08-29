package bot;

import bot.command.Command;
import bot.service.FileServices;
import bot.task.TaskList;
import bot.ui.Ui;
import bot.util.Parser;

import java.io.IOException;

public class Bot {
    /** Name of the bot */
    private final String name;

    /** List of tasks **/
    private TaskList taskList;

    /** File service API that write and read from file **/
    private final FileServices fileServices;

    private final Ui ui;

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

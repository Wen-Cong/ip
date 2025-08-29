package bot.command;

import bot.exception.InvalidCommandException;
import bot.service.FileServices;
import bot.task.Task;
import bot.task.TaskList;
import bot.ui.Ui;

import java.util.List;

public class FindCommand extends Command {
    private final String[] commandInfo;

    public FindCommand(String[] commandInfo) {
        this.commandInfo = commandInfo;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, FileServices fileServices) {
        try {
            // Validate command format, re-prompt if incorrect command format
            if (commandInfo.length != 2) {
                throw new InvalidCommandException(
                        "Please ensure command is in this format: " +
                                "find <Search Keyword>");
            }

            String keyword = commandInfo[1];

            // Find all tasks that matches keyword
            List<Task> filteredList = taskList.searchTasksByName(keyword);

            // Show the filtered task list to user
            ui.showSearchTaskList(filteredList);
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

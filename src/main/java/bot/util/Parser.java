package bot.util;

import bot.command.*;
import bot.command.FindCommand;

public class Parser {
    public static Command parse(String input) {
        // Split input by space into command type (first word)
        // and other command information (e.g. input param)
        String[] commandInfo = input.split(" ", 2);

        String instruction = commandInfo[0];

        // Redirect command type to invoke respective operations
        return switch (instruction) {
            case "bye" -> // Exit program
                    new ExitCommand();
            case "list" -> // Display task list
                    new ListTaskCommand();
            case "mark" -> // Mark a task as done
                    new MarkTaskCommand(commandInfo);
            case "unmark" -> // Mark a task as not done
                    new UnmarkTaskCommand(commandInfo);
            case "todo" -> // Add to-do task to task list
                    new AddTodoCommand(commandInfo);
            case "deadline" -> // Add deadline task to task list
                    new AddDeadlineCommand(commandInfo);
            case "event" -> // Add event task to task list
                    new AddEventCommand(commandInfo);
            case "delete" -> // Delete task from task list
                    new RemoveTaskCommand(commandInfo);
            case "find" -> // Search for tasks by name
                    new FindCommand(commandInfo);
            default -> new InvalidCommand();
        };
    }
}

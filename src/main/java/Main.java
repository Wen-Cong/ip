public class Main {
    public static void main(String[] args) {
        // Init a Bot to begin program
        Bot bot = new Bot("Lovely");

        Ui ui = new Ui();

        // Print greeting message
        ui.showWelcome(bot.getName());

        // Read user commands and perform actions repeatedly till "bye" command
        // to exit program
        while (true) {
            // Prompt user and read input from console
            String input = ui.promptCommand();

            // Split input by space into command type (first word)
            // and other command information (e.g. input param)
            // commandInfo[0] = command instruction
            // commandInfo[1] = other command info
            String[] commandInfo = input.split(" ", 2);

            try {
                // Redirect command type to invoke respective operations
                switch (commandInfo[0]) {
                case "bye": // Exit program
                    ui.showExitMessage();
                    System.exit(0);

                case "list": // Display task list
                    bot.listTasks();
                    break;

                case "mark": // Mark a task as done
                    // Validate command format, re-prompt if incorrect command format
                    if (commandInfo.length != 2 || !commandInfo[1].matches("\\d+")) {
                        throw new InvalidCommandException(
                                "Please ensure command is in this format: " +
                                        "mark <Task Index>");
                    }

                    bot.markTaskAsDone(Integer.parseInt(commandInfo[1]));
                    break;

                case "unmark": // Mark a task as not done
                    // Validate command format, re-prompt if incorrect command format
                    if (commandInfo.length != 2 || !commandInfo[1].matches("\\d+")) {
                        throw new InvalidCommandException(
                                "Please ensure command is in this format: " +
                                        "mark <Task Index>");
                    }

                    bot.markTaskAsNotDone(Integer.parseInt(commandInfo[1]));
                    break;

                case "todo": // Add to-do task to task list
                    // Validate command format, re-prompt if incorrect command format
                    if (commandInfo.length != 2) {
                        throw new InvalidCommandException(
                                "Please ensure command is in this format: " +
                                        "todo <Task Name>");
                    }

                    // Add to-do task to task list
                    bot.addTask(commandInfo[1]);
                    break;

                case "deadline": // Add deadline task to task list
                    // Validate command format, re-prompt if incorrect command format
                    if (commandInfo.length != 2) {
                        throw new InvalidCommandException(
                                "Please ensure command is in this format: " +
                                        "deadline <Task Name> /by <Date>");
                    }

                    // Split the command info with by "/by" to extract task name and deadline
                    String[] deadlineInfo = commandInfo[1].split(" /by ");

                    // Validate command format, re-prompt if incorrect command format
                    if (deadlineInfo.length != 2) {
                        throw new InvalidCommandException(
                                "Please ensure command is in this format: " +
                                        "deadline <Task Name> /by <Date>");
                    }

                    // Add deadline task to task list
                    bot.addTask(deadlineInfo[0], deadlineInfo[1]);
                    break;

                case "event": // Add event task to task list
                    // Validate command format, re-prompt if incorrect command format
                    if (commandInfo.length != 2) {
                        throw new InvalidCommandException(
                                "Please ensure command is in this format: " +
                                        "event <Task Name> /from <Start Date> /to <End Date>");
                    }

                    // Split the command info with by "/from" to extract task name
                    // eventInfo[0] = task name
                    // eventInfo[1] = start date and end date
                    String[] eventInfo = commandInfo[1].split(" /from ");

                    // Validate command format, re-prompt if incorrect command format
                    if (eventInfo.length != 2) {
                        throw new InvalidCommandException(
                                "Please ensure command is in this format: " +
                                        "event <Task Name> /from <Start Date> /to <End Date>");
                    }

                    // Extract start and end date from the separated eventInfo (taskName, date)
                    String[] dateInfo = eventInfo[1].split(" /to ");

                    // Validate command format, re-prompt if incorrect command format
                    if (dateInfo.length != 2) {
                        throw new InvalidCommandException(
                                "Please ensure command is in this format: " +
                                        "event <Task Name> /from <Start Date> /to <End Date>");
                    }

                    // Add event task to task list
                    bot.addTask(eventInfo[0], dateInfo[0], dateInfo[1]);
                    break;

                case "delete":
                    // Validate command format, re-prompt if incorrect command format
                    if (commandInfo.length != 2 || !commandInfo[1].matches("\\d+")) {
                        throw new InvalidCommandException(
                                "Please ensure command is in this format: " +
                                        "delete <Task Index>");
                    }

                    // Delete task from task list
                    bot.removeTask(Integer.parseInt(commandInfo[1]));
                    break;

                default:
                    throw new InvalidCommandException("No such command");
                }
            } catch (InvalidCommandException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}

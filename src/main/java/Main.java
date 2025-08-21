import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Init a Bot to begin program
        Bot bot = new Bot("Lovely");
        // Create a Scanner object to read input from the console
        Scanner scanner = new Scanner(System.in);

        // Print greeting message
        bot.greet();

        // Read user commands and perform actions repeatedly till "bye" command
        // to exit program
        while (true) {
            // Prompt user and read input from console
            String input = bot.promptUser(scanner);

            // Split input by space into command type (first word)
            // and other command information (e.g. input param)
            String[] commandInfo = input.split(" ");

            // Redirect command type to invoke respective operations
            switch (commandInfo[0]) {
            case "bye": // Exit program
                bot.exit();
                System.exit(0);
            case "list": // Display task list
                bot.listTasks();
                break;
            case "mark": // Mark a task as done
                bot.markTaskAsDone(Integer.parseInt(commandInfo[1]));
                break;
            case "unmark": // Mark a task as not done
                bot.markTaskAsNotDone(Integer.parseInt(commandInfo[1]));
                break;
            default: // Add task to task list
                bot.addTask(input);
            }

        }
    }
}

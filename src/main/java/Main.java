import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Init a Bot to begin program
        Bot bot = new Bot("Lovely");
        // Create a Scanner object to read input from the console
        Scanner scanner = new Scanner(System.in);

        // Print greeting message
        bot.greet();

        while (true) {
            // Read input from console
            String command = bot.promptUser(scanner);

            // Redirect command to invoke operations
            switch (command) {
            case "bye":
                bot.exit();
                System.exit(0);
            default:
                bot.echo(command);
            }

        }
    }
}

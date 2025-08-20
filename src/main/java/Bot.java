import java.util.Scanner;

public class Bot {
    /** Name of the bot */
    private final String name;

    public Bot(String name) {
        this.name = name;
    }

    /**
     * Print greeting message
     */
    public void greet() {
        this.printSeparator();
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        this.printSeparator();
    }

    /**
     * Print program exit message and exit the program
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        this.printSeparator();
        System.exit(0);
    }

    /**
     * Prompt the user for input
     *
     * @param scanner Scanner object to read input from console
     * @return Command string input entered by the user
     */
    public String promptUser(Scanner scanner) {
        System.out.print("Enter your command: ");
        String cmd = scanner.nextLine();
        this.printSeparator();
        return cmd;
    }

    /**
     * Print the message of the given String
     *
     * @param msg Message to be echoed and printed
     */
    public void echo(String msg) {
        System.out.println(msg);
        this.printSeparator();
    }

    /**
     * Print separator line that can be used between prompts and messages
     */
    private void printSeparator() {
        System.out.println("____________________________________________________________");
    }
}

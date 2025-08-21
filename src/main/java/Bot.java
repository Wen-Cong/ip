import java.util.ArrayList;
import java.util.Scanner;

public class Bot {
    /** Name of the bot */
    private final String name;

    /** List of text entered by user **/
    private final ArrayList<String> textList;

    public Bot(String name) {
        this.name = name;
        this.textList = new ArrayList<>();
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
     * Print program exit message
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        this.printSeparator();
    }

    /**
     * Prompt the user for input
     *
     * @param scanner Scanner object to read input from console
     * @return Command string input entered by the user
     */
    public String promptUser(Scanner scanner) {
        System.out.print("Enter your command: ");
        // Read input from console
        String cmd = scanner.nextLine();
        this.printSeparator();
        return cmd;
    }

    /**
     * Add text to text list
     *
     * @param msg Message to be added to textList and printed
     */
    public void add(String msg) {
        this.textList.add(msg);
        System.out.println("added: " + msg);
        this.printSeparator();
    }

    /**
     * Print every string item in textList
     */
    public void listItems() {
        for (int i = 0; i < this.textList.size(); i++) {
            int indexNum = i + 1; // Index numbering should start from 1 instead of 0
            System.out.println(indexNum + ". " + this.textList.get(i));
        }
        this.printSeparator();
    }


    /**
     * Print separator line that can be used between prompts and messages
     */
    private void printSeparator() {
        System.out.println("____________________________________________________________");
    }
}

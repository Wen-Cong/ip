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
     * Print program exit message
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        this.printSeparator();
    }

    /**
     * Print separator line that can be used between prompts and messages
     */
    private void printSeparator() {
        System.out.println("____________________________________________________________");
    }
}

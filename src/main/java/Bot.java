import java.util.ArrayList;
import java.util.Scanner;

public class Bot {
    /** Name of the bot */
    private final String name;

    /** List of text entered by user **/
    private final ArrayList<Task> taskList;

    public Bot(String name) {
        this.name = name;
        this.taskList = new ArrayList<>();
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
     * Add To-do task to task list
     *
     * @param taskName Task name to be added to taskList
     */
    public void addTask(String taskName) {
        Task newTask = new Todo(taskName); // create new to-do task
        this.taskList.add(newTask); // add task to task list
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + newTask);
        System.out.println("Now you have "
                + this.taskList.size() + " tasks in the list.");
        this.printSeparator();
    }

    /**
     * Add Deadline task to task list
     *
     * @param taskName Task name to be added to taskList
     * @param deadline Date time of the task deadline
     */
    public void addTask(String taskName, String deadline) {
        Task newTask = new Deadline(taskName, deadline); // create new deadline task
        this.taskList.add(newTask); // add task to task list
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + newTask);
        System.out.println("Now you have "
                + this.taskList.size() + " tasks in the list.");
        this.printSeparator();
    }

    /**
     * Add Event task to task list
     *
     * @param taskName Task name to be added to taskList
     * @param startTime start date time of the event task
     * @param endTime end date time of the event task
     */
    public void addTask(String taskName, String startTime, String endTime) {
        Task newTask = new Event(taskName, startTime, endTime); // create new event task
        this.taskList.add(newTask); // add task to task list
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + newTask);
        System.out.println("Now you have "
                + this.taskList.size() + " tasks in the list.");
        this.printSeparator();
    }

    /**
     * Print every task in taskList
     */
    public void listTasks() {
        if (this.taskList.isEmpty()) {
            System.out.println("There are no tasks at the moment");
            return;
        }

        // Iterate task list and print task
        for (int i = 0; i < this.taskList.size(); i++) {
            int indexNum = i + 1; // Index numbering should start from 1 instead of 0
            System.out.println(indexNum + ". " + this.taskList.get(i));
        }

        this.printSeparator();
    }

    /**
     * Set task status as done and print confirmation message
     *
     * @param index Task index position in Task List, starts from 1
     * @throws InvalidCommandException if index is out of bound
     */
    public void markTaskAsDone(int index) throws InvalidCommandException {
        // Validation for index number
        if (index > this.taskList.size() || index < 1) {
            throw new InvalidCommandException("Invalid task number");
        }

        Task task = this.taskList.get(index - 1); // Index given starts from 1
        task.markDone(); // Set task status to done

        // Print confirmation message and new status
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + task);
        this.printSeparator();
    }

    /**
     * Set task status as not done and print confirmation message
     *
     * @param index Task index position in Task List, starts from 1
     * @throws InvalidCommandException if index is out of bound
     */
    public void markTaskAsNotDone(int index) throws InvalidCommandException {
        // Validation for index number
        if (index > this.taskList.size() || index < 1) {
            throw new InvalidCommandException("Invalid task number");
        }

        Task task = this.taskList.get(index - 1); // Index given starts from 1
        task.markNotDone(); // Set task status to not done

        // Print confirmation message and new status
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + task);
        this.printSeparator();
    }

    /**
     * Print separator line that can be used between prompts and messages
     */
    private void printSeparator() {
        System.out.println("____________________________________________________________");
    }
}

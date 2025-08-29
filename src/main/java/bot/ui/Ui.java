package bot.ui;

import bot.task.Task;

import java.util.List;
import java.util.Scanner;

/**
 * Handles the user interface operations for the bot application.
 * This class manages input/output operations, displaying messages,
 * prompts, and formatting the user interface.
 */
public class Ui {
    private final Scanner scanner;
    private static final String SEPARATOR = "____________________________________________________________";

    /**
     * Constructs a Ui instance with a new Scanner for reading user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Show welcome message to user
     *
     * @param botName the name of the bot to be displayed in the welcome message
     */
    public void showWelcome(String botName) {
        this.printSeparator();
        System.out.println("Hello! I'm " + botName);
        System.out.println("What can I do for you?");
        this.printSeparator();
    }

    /**
     * Print program exit message
     */
    public void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        this.printSeparator();
    }

    /**
     * Prompt the user for command input
     *
     * @return Command string input entered by the user
     */
    public String promptCommand() {
        System.out.print("Enter your command: ");
        // Read input from console
        String cmd = scanner.nextLine();
        this.printSeparator();
        return cmd;
    }

    /**
     * Display an error message to the user
     *
     * @param errorMsg the error message to display
     */
    public void showError(String errorMsg) {
        System.out.println(errorMsg);
        this.printSeparator();
    }

    /**
     * Display success message when a task is added
     *
     * @param task the task that was added
     * @param taskCount the current number of tasks in the list
     */
    public void showAddTaskSuccess(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task);
        System.out.println("Now you have "
                + taskCount + " tasks in the list.");
        this.printSeparator();
    }

    /**
     * Display success message when a task is removed
     *
     * @param task the task that was removed
     * @param taskCount the current number of tasks in the list
     */
    public void showRemoveTaskSuccess(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + task);
        System.out.println("Now you have "
                + taskCount + " tasks in the list.");
        this.printSeparator();
    }

    /**
     * Display success message when a task is marked as done
     *
     * @param task the task that was marked as done
     */
    public void showMarkTaskSuccess(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + task);
        this.printSeparator();
    }

    /**
     * Display success message when a task is marked as not done
     *
     * @param task the task that was marked as not done
     */
    public void showUnmarkTaskSuccess(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + task);
        this.printSeparator();
    }

    /**
     * Display the list of tasks to the user
     *
     * @param taskList the list of tasks to display
     */
    public void showTaskList(List<Task> taskList) {
        if (taskList.isEmpty()) {
            System.out.println("No tasks found");
            return;
        }

        // Iterate task list and print task
        for (int i = 0; i < taskList.size(); i++) {
            int indexNum = i + 1; // Index numbering should start from 1 instead of 0
            System.out.println(indexNum + ". " + taskList.get(i));
        }

        this.printSeparator();
    }

    public void showSearchTaskList(List<Task> taskList) {
        System.out.println("Here are the matching tasks in your list:");
        this.showTaskList(taskList);
    }

    /**
     * Print separator line that can be used between prompts and messages
     */
    private void printSeparator() {
        System.out.println(SEPARATOR);
    }
}
package bot.ui;

import bot.task.Task;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private final Scanner scanner;
    private static final String SEPARATOR = "____________________________________________________________";

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Show welcome message to user
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
     * @return bot.command.Command string input entered by the user
     */
    public String promptCommand() {
        System.out.print("Enter your command: ");
        // Read input from console
        String cmd = scanner.nextLine();
        this.printSeparator();
        return cmd;
    }

    public void showError(String errorMsg) {
        System.out.println(errorMsg);
        this.printSeparator();
    }

    public void showAddTaskSuccess(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task);
        System.out.println("Now you have "
                + taskCount + " tasks in the list.");
        this.printSeparator();
    }

    public void showRemoveTaskSuccess(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + task);
        System.out.println("Now you have "
                + taskCount + " tasks in the list.");
        this.printSeparator();
    }

    public void showMarkTaskSuccess(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + task);
        this.printSeparator();
    }

    public void showUnmarkTaskSuccess(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + task);
        this.printSeparator();
    }



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

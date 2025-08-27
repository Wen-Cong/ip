import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bot {
    /** Name of the bot */
    private final String name;

    /** List of text entered by user **/
    private List<Task> taskList;

    /** File service API that write and read from file **/
    private final FileServices fileServices;

    public Bot(String name) {
        this.name = name;
        this.taskList = new ArrayList<>();
        this.fileServices = new FileServices("data/taskData.txt");
        loadTaskList(); // Load task list from file
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
     * Add To-do task to task list and write task string to file
     *
     * @param taskName Task name to be added to taskList
     */
    public void addTask(String taskName) {
        Task newTask = new Todo(taskName); // create new to-do task
        this.taskList.add(newTask); // add task to task list

        // Write task list to file
        boolean isSuccess = saveTaskList();
        if (!isSuccess) {
            return;
        }

        // Print success message
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + newTask);
        System.out.println("Now you have "
                + this.taskList.size() + " tasks in the list.");
        this.printSeparator();
    }

    /**
     * Add Deadline task to task list and write task string to file
     *
     * @param taskName Task name to be added to taskList
     * @param deadline Date time of the task deadline
     */
    public void addTask(String taskName, String deadline) {
        Task newTask = new Deadline(taskName, deadline); // create new deadline task
        this.taskList.add(newTask); // add task to task list

        // Write task list to file
        boolean isSuccess = saveTaskList();
        if (!isSuccess) {
            return;
        }

        // Print success message
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + newTask);
        System.out.println("Now you have "
                + this.taskList.size() + " tasks in the list.");
        this.printSeparator();
    }

    /**
     * Add Event task to task list and write task string to file
     *
     * @param taskName Task name to be added to taskList
     * @param startTime start date time of the event task
     * @param endTime end date time of the event task
     */
    public void addTask(String taskName, String startTime, String endTime) {
        Task newTask = new Event(taskName, startTime, endTime); // create new event task
        this.taskList.add(newTask); // add task to task list

        // Write task list to file
        boolean isSuccess = saveTaskList();
        if (!isSuccess) {
            return;
        }

        // Print success message
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + newTask);
        System.out.println("Now you have "
                + this.taskList.size() + " tasks in the list.");
        this.printSeparator();
    }

    /**
     * Remove the task from task list and print confirmation message
     *
     * @param index Task index position in Task List, starting from 1
     * @throws InvalidCommandException if index is out of bound
     */
    public void removeTask(int index) throws InvalidCommandException {
        // Validation for index number
        if (index > this.taskList.size() || index < 1) {
            throw new InvalidCommandException("Invalid task number");
        }

        // Remove the task from task list
        Task task = this.taskList.remove(index - 1); // Index given starts from 1

        // Write task list to file
        boolean isSuccess = saveTaskList();
        if (!isSuccess) {
            return;
        }

        // Print confirmation message and list count
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + task);
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

        // Write task list to file
        boolean isSuccess = saveTaskList();
        if (!isSuccess) {
            return;
        }

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

        // Write task list to file
        boolean isSuccess = saveTaskList();
        if (!isSuccess) {
            return;
        }

        // Print confirmation message and new status
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + task);
        this.printSeparator();
    }

    /**
     * Saves the current list of tasks to a file using the {@link FileServices}.
     * If the save operation is successful, the method returns true.
     * If an exception occurs during the file write process, an error message
     * is printed to the console and the method returns false.
     *
     * @return {@code true} if the task list was successfully written to the file;
     *         {@code false} otherwise.
     */
    private boolean saveTaskList() {
        // Write updated task list to file
        try {
            this.fileServices.writeToFile(this.taskList);
        } catch (Exception e) {
            // Print error message
            System.out.println(e.getMessage());
            this.printSeparator();
            return false;
        }

        return true;
    }

    /**
     * Loads the task list from the file using the {@code FileServices}.
     * <p>
     * This method attempts to read the task data from the file and populates
     * the instance's task list.
     * Any {@link IOException} that occurs during file reading or an
     * {@link IllegalArgumentException} resulting from improperly formatted data
     * within the file will be caught, an error message will be printed to the
     * console, and the program will continue with an empty task list.
     */
    private void loadTaskList() {
        try {
            this.taskList = fileServices.readFromFile();
        } catch (IOException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            this.printSeparator();
        }
    }

    /**
     * Print separator line that can be used between prompts and messages
     */
    private void printSeparator() {
        System.out.println("____________________________________________________________");
    }
}

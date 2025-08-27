/**
 * Represents a generic task with a description and a completion status.
 * <p>
 * This abstract class serves as the base for more specific types of tasks
 */
public abstract class Task {
    /** Name of the task */
    private final String name;

    /** Status of the task, true if task is marked as done */
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Sets the task as done
     **/
    public void markDone() {
        isDone = true;
    }

    /**
     * Sets the task as not done
     **/
    public void markNotDone() {
        isDone = false;
    }

    /**
     * Return the string format of the Task to be written into a file
     *
     * @return The string format of Task suitable for file writing
     **/
    public String toFileString() {
        String isDoneString = isDone ? "1" : "0";

        return isDoneString + " | " + name;
    }


    /**
     * Creates and returns a {@code Task} object from a formatted string retrieved from a file.
     * <p>
     * This static factory method parses a single line of text. The method
     * determines the specific type of task (To-do, Deadline, or Event) based on a type
     * identifier and then constructs the appropriate subclass instance.
     * <p>
     * The expected format for the input string is a set of pipe-separated values:
     * <ul>
     *     <li>For a To-do: {@code "T | <isDone> | <taskName>"}</li>
     *     <li>For a Deadline: {@code "D | <isDone> | <taskName> | <deadline>"}</li>
     *     <li>For an Event: {@code "E | <isDone> | <taskName> | <from> | <to>"}</li>
     * </ul>
     * The {@code <isDone>} value should be "1" for a completed task and "0" otherwise.
     *
     * @param fileString The single line of text read from the task file.
     * @return A {@code Task} object, which could be a {@code To-do}, {@code Deadline},
     *         or {@code Event} instance, corresponding to the data in the input string.
     * @throws IllegalArgumentException If the file string is not in the expected format.
     */
    public static Task createTaskFromFileString(String fileString)
            throws IllegalArgumentException {
        // Split string by ' | '
        String[] parts = fileString.split(" \\| ");

        // Check if splied string parts is valid
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid task format in file: " + fileString);
        }

        // Extract string parts
        String type = parts[0];
        boolean isDone = parts[1].equals("1"); // True, if isDone = "1".
        String taskName = parts[2];

        Task task;

        // Based on the task type, create the appropriate subclass
        switch (type) {
        case "T":
            task = new Todo(taskName, isDone);
            break;

        case "D":
            String deadline = parts[3];
            task = new Deadline(taskName, deadline, isDone);
            break;

        case "E":
            String from = parts[3];
            String to = parts[4];
            task = new Event(taskName, from, to, isDone);
            break;

        default:
            // Handle unexpected format
            throw new IllegalArgumentException("Invalid task format in file: " + fileString);
        }

        return task;
    }

    /**
     * Display string format of Task instance with status and task name
     **/
    @Override
    public String toString() {
        String status = "[ ]";
        if (isDone) {
            status = "[X]";
        }
        return status + " " + name;
    }
}

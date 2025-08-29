package bot.task;

/**
 * Represents a simple to-do task without any specific date or time constraints.
 * This class extends the base Task class and provides to-do specific functionality.
 */
public class Todo extends Task {

    /**
     * Constructs a To-do task with the specified task name.
     * The task is initially marked as not completed.
     *
     * @param taskName the name/description of the to-do task
     */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Constructs a To-do task with the specified task name and completion status.
     *
     * @param taskName the name/description of the to-do task
     * @param isDone the completion status of the task
     */
    public Todo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    /**
     * Return the string format of the To-do to be written into a file.
     * The format is: "T | [status] | [task name]"
     *
     * @return The string format of To-do suitable for file writing
     **/
    @Override
    public String toFileString() {
        return "T | " + super.toFileString() + "\n";
    }

    /**
     * Display string format of To-do task with status and task name.
     * The format is: "[T][status] [task name]"
     *
     * @return The string format of To-do suitable for display
     **/
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

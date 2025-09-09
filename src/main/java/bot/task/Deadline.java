package bot.task;

import bot.util.DateTimeUtils;

import java.time.LocalDateTime;

/**
 * Represents a deadline task that has a specific due date and time.
 * This class extends the base Task class and adds deadline-specific functionality.
 */
public class Deadline extends Task {
    /** Deadline date time of the task */
    private final LocalDateTime deadline;

    /**
     * Constructs a Deadline task with the specified task name and deadline.
     * The task is initially marked as not completed.
     *
     * @param taskName the name/description of the task
     * @param deadline the deadline date and time as a string in a supported format
     * @throws IllegalArgumentException if the deadline string cannot be parsed into a valid date/time
     */
    public Deadline(String taskName, String deadline)
            throws IllegalArgumentException {
        super(taskName);
        this.deadline = DateTimeUtils.fromString(deadline);
    }

    /**
     * Constructs a Deadline task with the specified task name, deadline, and completion status.
     *
     * @param taskName the name/description of the task
     * @param deadline the deadline date and time as a string in a supported format
     * @param isDone the completion status of the task
     * @throws IllegalArgumentException if the deadline string cannot be parsed into a valid date/time
     */
    public Deadline(String taskName, String deadline, boolean isDone)
            throws IllegalArgumentException {
        super(taskName, isDone);
        this.deadline = DateTimeUtils.fromString(deadline);
    }

    /**
     * Return the string format of the bot.task.Deadline to be written into a file
     *
     * @return The string format of bot.task.Deadline suitable for file writing
     **/
    @Override
    public String toFileString() {
        return "D | " + super.toFileString()
                + " | " + DateTimeUtils.toFileString(deadline) + "\n";
    }

    /**
     * Display string format of bot.task.Deadline task with status, task name
     * and deadline
     *
     * @return The string format of bot.task.Deadline suitable for display
     **/
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + DateTimeUtils.toDisplayString(deadline) + ")";
    }
}

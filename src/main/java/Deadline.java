import java.time.LocalDateTime;

public class Deadline extends Task {
    /** Deadline date time of the task */
    private final LocalDateTime deadline;

    public Deadline(String taskName, String deadline)
            throws IllegalArgumentException {
        super(taskName);
        this.deadline = DateTimeUtils.fromString(deadline);
    }

    public Deadline(String taskName, String deadline, boolean isDone)
            throws IllegalArgumentException {
        super(taskName, isDone);
        this.deadline = DateTimeUtils.fromString(deadline);
    }

    /**
     * Return the string format of the Deadline to be written into a file
     *
     * @return The string format of Deadline suitable for file writing
     **/
    @Override
    public String toFileString() {
        return "D | " + super.toFileString()
                + " | " + DateTimeUtils.toFileString(deadline) + "\n";
    }

    /**
     * Display string format of Deadline task with status, task name
     * and deadline
     *
     * @return The string format of Deadline suitable for display
     **/
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + DateTimeUtils.toDisplayString(deadline) + ")";
    }
}

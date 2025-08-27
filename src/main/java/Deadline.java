public class Deadline extends Task {
    /** Deadline date time of the task */
    private final String deadline;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    public Deadline(String taskName, String deadline, boolean isDone) {
        super(taskName, isDone);
        this.deadline = deadline;
    }

    /**
     * Return the string format of the Deadline to be written into a file
     *
     * @return The string format of Deadline suitable for file writing
     **/
    @Override
    public String toFileString() {
        return "D | " + super.toFileString()
                + " | " + deadline + "\n";
    }

    /**
     * Display string format of Deadline task with status, task name
     * and deadline
     **/
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + this.deadline + ")";
    }
}

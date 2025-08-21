public class Deadline extends Task {
    /** Deadline date time of the task */
    private final String deadline;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
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

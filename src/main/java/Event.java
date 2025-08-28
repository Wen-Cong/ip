import java.time.LocalDateTime;

public class Event extends Task {
    /** Start date time of the event task */
    private final LocalDateTime startTime;

    /** End date time of the event task */
    private final LocalDateTime endTime;

    public Event(String taskName, String startTime, String endTime)
            throws IllegalArgumentException {
        super(taskName);
        this.startTime = DateTimeUtils.fromString(startTime);
        this.endTime = DateTimeUtils.fromString(endTime);
    }

    public Event(String taskName, String startTime, String endTime, boolean isDone)
            throws IllegalArgumentException {
        super(taskName, isDone);
        this.startTime = DateTimeUtils.fromString(startTime);
        this.endTime = DateTimeUtils.fromString(endTime);
    }

    /**
     * Return the string format of the Event to be written into a file
     *
     * @return The string format of Event suitable for file writing
     **/
    @Override
    public String toFileString() {
        return "E | " + super.toFileString()
                + " | " + DateTimeUtils.toFileString(startTime)
                + " | " + DateTimeUtils.toFileString(endTime) + "\n";
    }

    /**
     * Display string format of Event task with status, task name,
     * start time and end time
     *
     * @return The string format of Event suitable for display
     **/
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + DateTimeUtils.toDisplayString(startTime)
                + ", to: " + DateTimeUtils.toDisplayString(endTime) + ")";
    }
}

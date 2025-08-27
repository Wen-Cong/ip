public class Event extends Task {
    /** Start date time of the event task */
    private final String startTime;

    /** End date time of the event task */
    private final String endTime;

    public Event(String taskName, String startTime, String endTime) {
        super(taskName);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Return the string format of the Event to be written into a file
     *
     * @return The string format of Event suitable for file writing
     **/
    @Override
    public String toFileString() {
        return "E | " + super.toFileString()
                + " | " + startTime
                + " | " + endTime + "\n";
    }

    /**
     * Display string format of Event task with status, task name,
     * start time and end time
     **/
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.startTime
                + ", to: " + this.endTime + ")";
    }
}

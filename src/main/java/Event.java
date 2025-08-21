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

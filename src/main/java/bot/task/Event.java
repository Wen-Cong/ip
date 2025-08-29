package bot.task;

import bot.util.DateTimeUtils;

import java.time.LocalDateTime;

/**
 * Represents an event task that has a specific start and end date/time.
 * This class extends the base Task class and adds event-specific functionality.
 */
public class Event extends Task {
    /** Start date time of the event task */
    private final LocalDateTime startTime;

    /** End date time of the event task */
    private final LocalDateTime endTime;

    /**
     * Constructs an Event task with the specified task name, start time, and end time.
     * The task is initially marked as not completed.
     *
     * @param taskName the name/description of the event
     * @param startTime the start date and time as a string in a supported format
     * @param endTime the end date and time as a string in a supported format
     * @throws IllegalArgumentException if the start or end time strings cannot be parsed into valid date/time
     */
    public Event(String taskName, String startTime, String endTime)
            throws IllegalArgumentException {
        super(taskName);
        this.startTime = DateTimeUtils.fromString(startTime);
        this.endTime = DateTimeUtils.fromString(endTime);
    }

    /**
     * Constructs an Event task with the specified task name, start time, end time, and completion status.
     *
     * @param taskName the name/description of the event
     * @param startTime the start date and time as a string in a supported format
     * @param endTime the end date and time as a string in a supported format
     * @param isDone the completion status of the task
     * @throws IllegalArgumentException if the start or end time strings cannot be parsed into valid date/time
     */
    public Event(String taskName, String startTime, String endTime, boolean isDone)
            throws IllegalArgumentException {
        super(taskName, isDone);
        this.startTime = DateTimeUtils.fromString(startTime);
        this.endTime = DateTimeUtils.fromString(endTime);
    }

    /**
     * Return the string format of the bot.task.Event to be written into a file
     *
     * @return The string format of bot.task.Event suitable for file writing
     **/
    @Override
    public String toFileString() {
        return "E | " + super.toFileString()
                + " | " + DateTimeUtils.toFileString(startTime)
                + " | " + DateTimeUtils.toFileString(endTime) + "\n";
    }

    /**
     * Display string format of bot.task.Event task with status, task name,
     * start time and end time
     *
     * @return The string format of bot.task.Event suitable for display
     **/
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + DateTimeUtils.toDisplayString(startTime)
                + ", to: " + DateTimeUtils.toDisplayString(endTime) + ")";
    }
}

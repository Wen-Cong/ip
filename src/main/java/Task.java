public abstract class Task {
    /** Name of the task */
    private final String name;

    /** Status of the task, true if task is marked as done */
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        isDone = false;
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

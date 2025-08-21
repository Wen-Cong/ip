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

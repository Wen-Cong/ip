public class Task {
    /** Name of the task */
    private final String name;

    /** Status of the task, true if task is marked as done */
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    /**
     * Sets the task as done and prints confirmation
     **/
    public void markDone() {
        isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + this);
    }

    /**
     * Sets the task as not done and prints confirmation
     **/
    public void markNotDone() {
        isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + this);
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

public class Todo extends Task {
    public Todo(String taskName) {
        super(taskName);
    }

    public Todo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    /**
     * Return the string format of the To-do to be written into a file
     *
     * @return The string format of To-do suitable for file writing
     **/
    @Override
    public String toFileString() {
        return "T | " + super.toFileString() + "\n";
    }

    /**
     * Display string format of To-do task with status and task name
     *
     * @return The string format of To-do suitable for display
     **/
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

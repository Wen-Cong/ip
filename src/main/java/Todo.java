public class Todo extends Task {
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Display string format of To-do task with status and task name
     **/
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

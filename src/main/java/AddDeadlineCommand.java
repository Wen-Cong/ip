public class AddDeadlineCommand extends Command {
    private final String taskName;
    private final String deadline;

    public AddDeadlineCommand(String taskName, String deadline) {
        this.taskName = taskName;
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, FileServices fileServices) {
        try {
            Task newTask = taskList.addTask(taskName, deadline);

            // Write task list to file
            fileServices.writeToFile(taskList);

            // Print success message
            ui.showAddTaskSuccess(newTask, taskList.getSize());
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

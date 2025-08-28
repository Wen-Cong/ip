public class AddTodoCommand extends Command {
    private final String taskName;

    public AddTodoCommand(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, FileServices fileServices) {
        try {
            Task newTask = taskList.addTask(taskName);

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

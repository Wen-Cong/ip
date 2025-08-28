public class AddEventCommand extends Command {
    private final String taskName;
    private final String startTime;
    private final String endTime;

    public AddEventCommand(String taskName, String startTime, String endTime) {
        this.taskName = taskName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, FileServices fileServices) {
        try {
            Task newTask = taskList.addTask(taskName, startTime, endTime);

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

public class RemoveTaskCommand extends Command {
    int index;

    public RemoveTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, FileServices fileServices) {
        try {
            Task task = taskList.removeTask(index);

            // Write task list to file
            fileServices.writeToFile(taskList);

            // Print confirmation message and list count
            ui.showRemoveTaskSuccess(task, taskList.getSize());
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

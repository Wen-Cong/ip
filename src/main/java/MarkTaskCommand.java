public class MarkTaskCommand extends Command {
    int index;

    public MarkTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, FileServices fileServices) {
        try {
            Task task = taskList.markTaskAsDone(index);

            // Write task list to file
            fileServices.writeToFile(taskList);

            // Print confirmation message and new status
            ui.showMarkTaskSuccess(task);
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

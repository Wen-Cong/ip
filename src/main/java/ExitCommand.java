public class ExitCommand extends Command {
    private boolean isExecuted = false;

    public ExitCommand() {}

    @Override
    public void execute(TaskList taskList, Ui ui, FileServices fileServices) {
        ui.showExitMessage();
        isExecuted = true;
    }

    @Override
    public boolean isExit() {
        return isExecuted;
    }
}

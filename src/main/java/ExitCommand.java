public class ExitCommand extends Command {
    public ExitCommand() {}

    @Override
    public void execute(TaskList taskList, Ui ui, FileServices fileServices) {
        ui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

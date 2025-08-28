public class InvalidCommand extends Command {
    public InvalidCommand() {}

    @Override
    public void execute(TaskList taskList, Ui ui, FileServices fileServices) {
        ui.showError("No such command");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

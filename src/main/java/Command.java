public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, FileServices fileServices);

    public abstract boolean isExit();
}

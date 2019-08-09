package Menu;

public class Operation {
    Command slot;

    public void setCommand(Command newCommand)
    {
        slot = newCommand;
    }
    public void opetate()
    {
        slot.execute();
    }
}

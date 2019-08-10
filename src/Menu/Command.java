package Menu;

import Entities.Worker;
import Memento.CopyStates;

import java.util.List;

public interface Command {
    public void execute(List<Worker> payroll, MyCalendar calendar, List<CopyStates> stack);
}

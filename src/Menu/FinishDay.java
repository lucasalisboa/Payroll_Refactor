package Menu;

import Entities.Worker;
import Memento.CopyStates;

import java.util.List;

public class FinishDay implements Command {
    @Override
    public void execute(List<Worker> payroll, MyCalendar calendar, List<CopyStates> stack) {
        calendar.finishDay();
    }
}

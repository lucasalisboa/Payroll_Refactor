package Menu;

import Entities.Worker;
import Exceptions.DomainExcepciotion;
import Memento.CopyStates;

import java.util.List;

public interface Command {
    public void execute(List<Worker> payroll, MyCalendar calendar, List<CopyStates> stack) throws DomainExcepciotion;
}

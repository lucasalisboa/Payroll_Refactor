package Menu;

import Entities.Worker;
import Exceptions.DomainExcepciotion;
import Memento.CopyStates;

import java.util.List;

public class ShowPayment extends Operation implements Command  {
    @Override
    public void execute(List<Worker> payroll, MyCalendar calendar, List<CopyStates> stack) throws DomainExcepciotion {
        int index = search(payroll);
        if(index != -1)
        {
            payroll.get(index).showPayment(calendar.data);
        }
    }
}

package Menu;

import Entities.Worker;
import Exceptions.DomainExcepciotion;
import Memento.CopyStates;

import java.util.List;

public class ShowInfo extends Operation implements Command  {
    @Override
    public void execute(List<Worker> payroll, MyCalendar calendar, List<CopyStates> stack) throws DomainExcepciotion {
        int index = search(payroll);
        if(index != -1)
        {
            System.out.println(payroll.get(index).toString());
        }
    }
}

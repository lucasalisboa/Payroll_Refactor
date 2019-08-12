package Menu;

import Entities.HouristWorker;
import Entities.Worker;
import Memento.CopyStates;

import java.util.List;

public class PointCard extends Operation implements Command {
    public PointCard() {
        super();
    }

    @Override
    public void execute(List<Worker> payroll, MyCalendar calendar, List<CopyStates> stack) {
        int index = search(payroll);
        if(index != -1)
        {
            if(payroll.get(index) instanceof HouristWorker)
            {
                ((HouristWorker) payroll.get(index)).point();
                save_state(payroll,stack);
                stack_index = 0;
            }
            else
            {
                System.out.println("THIS WORKER IS NOT A HOURIST");
            }
        }
    }
}

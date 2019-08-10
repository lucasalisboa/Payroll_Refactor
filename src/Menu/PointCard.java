package Menu;

import Entities.HouristWorker;
import Entities.Worker;

import java.util.List;

public class PointCard extends Operation implements Command {
    @Override
    public void execute(List<Worker> payroll, MyCalendar calendar) {
        int index = search(payroll);
        if(index != -1)
        {
            if(payroll.get(index) instanceof HouristWorker)
            {
                ((HouristWorker) payroll.get(index)).point();
            }
            else
            {
                System.out.println("THIS WORKER IS NOT A HOURIST");
            }
        }
    }
}

package Menu;

import Entities.Worker;

import java.util.List;

public class Remove extends Operation implements Command {
    @Override
    public void execute(List<Worker> payroll, MyCalendar calendar) {
        int index = search(payroll);
        if (index != -1)
        {
            String worker_name = payroll.get(index).name;
            payroll.remove(index);
            System.out.println("THE WORKER " + worker_name + " WAS REMOVED");
        }
    }
}

package Menu;

import Entities.CommissionedWorker;
import Entities.Worker;

import java.util.List;

public class ServiceTax extends Operation implements Command {
    @Override
    public void execute(List<Worker> payroll, MyCalendar calendar) {
        int index = search(payroll);
        if(index != -1)
        {
            if(payroll.get(index) instanceof CommissionedWorker)
            {
                ((CommissionedWorker) payroll.get(index)).setPercent();
            }
            else
            {
                System.out.println("THIS WORKER IS NOT A COMMISSIONED");
            }
        }
    }
}

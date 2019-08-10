package Menu;

import Entities.CommissionedWorker;
import Entities.Worker;

import java.util.List;

public class SaleResult extends Operation implements Command {
    @Override
    public void execute(List<Worker> payroll, MyCalendar calendar) {
        int index = search(payroll);
        if(index != -1)
        {
            if(payroll.get(index) instanceof CommissionedWorker)
            {
                ((CommissionedWorker) payroll.get(index)).newSale();
            }
            else
            {
                System.out.println("THIS WORKER IS NOT A COMMISSIONED");
            }
        }
    }
}

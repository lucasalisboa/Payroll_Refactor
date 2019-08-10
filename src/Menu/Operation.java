package Menu;

import Entities.Worker;

import java.util.List;
import java.util.Scanner;

public class Operation {
    List<Command> slot;

    public Operation() {
        slot.add(0,new Add());
        slot.add(1,new Remove());
        slot.add(2,new PointCard());
        slot.add(3,new SaleResult());
        slot.add(4,new ServiceTax());
        slot.add(5,new Information());
        slot.add(6,new RunPayroll());
    }

    public void operate(int index, List<Worker> payroll, MyCalendar calendar)
    {
        slot.get(index).execute(payroll,calendar);
    }

    public int search(List <Worker> payroll)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("WHAT'S THE WORKER ID?");
        String worker_id = sc.nextLine();
        for(int i = 0; i < payroll.size(); i++)
        {
            if(payroll.get(i).id.equals(worker_id))
            {
                return i;
            }
        }
        System.out.println("THE WORKER DOESN'T EXIST");
        return -1;
    }
}

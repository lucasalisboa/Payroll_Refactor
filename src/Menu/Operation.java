package Menu;

import Entities.Worker;
import Memento.CopyStates;

import java.util.List;
import java.util.Scanner;

public class Operation {
    List<Command> slot;
    public int stack_index;

    public Operation() {
        stack_index = 0;
        slot.add(0,new Add());
        slot.add(1,new Remove());
        slot.add(2,new PointCard());
        slot.add(3,new SaleResult());
        slot.add(4,new ServiceTax());
        slot.add(5,new Information());
        slot.add(6,new RunPayroll());
        slot.add(7,new UndoRedo());
    }

    public void operate(int index, List<Worker> payroll, MyCalendar calendar, List<CopyStates> stack)
    {
        slot.get(index).execute(payroll,calendar,stack);
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
    public void save_state(List<Worker> payroll, List<CopyStates> stack)
    {
        stack_index--;
        while (stack_index > 0)
        {
            stack.remove(stack_index);
            stack_index--;
        }
        stack.add(0,new CopyStates(payroll));
    }
}

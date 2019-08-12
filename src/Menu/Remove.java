package Menu;

import Entities.Worker;
import Memento.CopyStates;

import java.util.List;

public class Remove extends Operation implements Command {
    public Remove() {
        super();
    }

    @Override
    public void execute(List<Worker> payroll, MyCalendar calendar, List<CopyStates> stack) {
        int index = search(payroll);
        if (index != -1)
        {
            String worker_name = payroll.get(index).name;
            payroll.remove(index);
            System.out.println("THE WORKER " + worker_name + " WAS REMOVED");
            save_state(payroll,stack);
            stack_index = 0;
        }
    }
}

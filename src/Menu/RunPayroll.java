package Menu;

import Entities.Worker;
import Memento.CopyStates;

import java.util.List;

public class RunPayroll implements Command {
    @Override
    public void execute(List<Worker> payroll, MyCalendar calendar, List<CopyStates> stack) {
        int c = 0;
        for (Worker worker : payroll) {
            if (calendar.cal.compareTo(worker.getPay_day()) == 0) {
                System.out.println("ID: " + worker.id);
                System.out.println("NAME: " + worker.name);
                worker.payment(calendar.today);
                c++;
                System.out.println();
            }
        }
        System.out.println(c + " WORKERS WERE PAID TODAY");
    }
}

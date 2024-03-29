package Menu;

import Entities.CommissionedWorker;
import Entities.HouristWorker;
import Entities.SalariedWorker;
import Entities.Worker;
import Memento.CopyStates;

import java.util.List;
import java.util.Scanner;

public class Add extends Operation implements Command {

    public Add() {
        super();
    }

    @Override
    public void execute(List<Worker> payroll, MyCalendar calendar, List<CopyStates> stack) {
        Scanner sc = new Scanner(System.in);
        String type;
        System.out.println("WHAT'S THE WORKER TYPE?");
        System.out.println("H- HOURLY; S- SALARIED; C- COMMISSIONED");
        type = sc.next();
        sc.nextLine();

        if (type.equals("H"))
        {
            payroll.add(new HouristWorker(calendar.today));
        }
        else if(type.equals("S"))
        {
            payroll.add(new SalariedWorker(calendar.today));
        }
        else
        {
            payroll.add(new CommissionedWorker(calendar.today));
        }
        save_state(payroll,stack);
        stack_index = 0;
    }

}

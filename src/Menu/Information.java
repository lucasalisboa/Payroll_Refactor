package Menu;

import Entities.CommissionedWorker;
import Entities.HouristWorker;
import Entities.SalariedWorker;
import Entities.Worker;
import Exceptions.DomainExcepciotion;
import Memento.CopyStates;

import java.util.List;
import java.util.Scanner;

public class Information extends Operation implements Command {
    public Information() {
        super();
    }

    @Override
    public void execute(List<Worker> payroll, MyCalendar calendar, List<CopyStates> stack) throws DomainExcepciotion {
        int index = search(payroll);
        if(index != -1)
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("WHAT INFORMATION YOU WANT TO CHANGE?");
            System.out.println("1- NAME");
            System.out.println("2- ADDRESS");
            System.out.println("3- TYPE");
            System.out.println("4- PAYMENT METHOD");
            System.out.println("5- SYNDICATED");
            System.out.println("6- SYNDICATE IDENTIFICATION");
            System.out.println("7- SYNDICATE TAX");
            int info = sc.nextInt();

            if(info == 1)
            {
                payroll.get(index).changeName();
            }
            else if(info == 2)
            {
                payroll.get(index).changeAddress();
            }
            else if(info == 3)
            {
                String type;
                System.out.println("WHAT'S THE WORKER TYPE?");
                System.out.println("H- HOURLY; S- SALARIED; C- COMMISSIONED");
                type = sc.next();
                sc.nextLine();

                if (type.equals("H"))
                {
                    payroll.add(new HouristWorker(payroll.get(index).id,payroll.get(index).name,payroll.get(index).getAddress(),payroll.get(index).getPayment_method(),payroll.get(index).isSyndicate(),payroll.get(index).getSyndicate_id(),payroll.get(index).getSyndicate_tax(),calendar.today));
                }
                else if(type.equals("S"))
                {
                    payroll.add(new SalariedWorker(payroll.get(index).id,payroll.get(index).name,payroll.get(index).getAddress(),payroll.get(index).getPayment_method(),payroll.get(index).isSyndicate(),payroll.get(index).getSyndicate_id(),payroll.get(index).getSyndicate_tax(),calendar.today));
                }
                else
                {
                    payroll.add(new CommissionedWorker(payroll.get(index).id,payroll.get(index).name,payroll.get(index).getAddress(),payroll.get(index).getPayment_method(),payroll.get(index).isSyndicate(),payroll.get(index).getSyndicate_id(),payroll.get(index).getSyndicate_tax(),calendar.today));
                }
                payroll.remove(index);
            }
            else if(info == 4)
            {
                payroll.get(index).changePayMethod();
            }
            else if(info == 5)
            {
                payroll.get(index).changeSyndicate();
            }
            else if(info == 6)
            {
                payroll.get(index).changeSyndicateId();
            }
            else if(info == 7)
            {
                payroll.get(index).changeSyndicateTax();
            }
            else
            {
                throw new DomainExcepciotion("INVALID OPERATION");
            }
        }
    }
}

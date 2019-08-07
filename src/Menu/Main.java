package Menu;

import Entities.CommissionedWorker;
import Entities.HouristWorker;
import Entities.SalariedWorker;
import Entities.Worker;
import Exceptions.DomainExcepciotion;
import Memento.CopyStates;

import java.util.*;



public class Main {

    public static void main(String[] args)  {
        System.out.println("WELCOME\n");
        MyCalendar calendar = new MyCalendar();
        List<Worker> payroll = new ArrayList<>();
        List<CopyStates> stack = new ArrayList<>();
        save_state(payroll,stack,0);
        action(calendar,payroll,stack,0);
    }

    private static void action(MyCalendar calendar, List <Worker> payroll, List<CopyStates> stack, int stack_index)
    {
        System.out.println("TODAY IS:");
        System.out.println(calendar.data.format(calendar.today) + "," + calendar.dayWeek());
        System.out.println();
        System.out.println("CHOOSE YOUR OPERATION:");
        System.out.println("1- ADD NEW EMPLOYER");
        System.out.println("2- REMOVE A EMPLOYER");
        System.out.println("3- PUT THE POINT CARD");
        System.out.println("4- PUT THE SALE'S RESULT");
        System.out.println("5- PUT THE SERVICE TAX");
        System.out.println("6- CHANGE THE EMPLOYER INFORMATIONS");
        System.out.println("7- RUN THE PAYROLL");
        System.out.println("8- UNDO/REDO");
        System.out.println("9- SHOW THE PAYMENT SCHEDULE");
        System.out.println("10- CREATE A NEW PAYMENT SCHEDULE");
        System.out.println("11 - SHOW THE WORKER INFORMATIONS");
        System.out.println("12- FINISH THE DAY");

        try{
            int operation;
            Scanner sc = new Scanner(System.in);
            operation = sc.nextInt();

            if(operation == 1)
            {
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
                save_state(payroll,stack,stack_index);
                stack_index = 0;
            }
            else if(operation == 8)
            {
                int aux;
                System.out.println("DO YOU MAKE:");
                System.out.println("1- UNDO?");
                System.out.println("2- REDO?");
                aux = sc.nextInt();

                if(aux == 1)
                {
                    stack_index++;
                }
                else if(aux == 2)
                {
                    stack_index--;
                }
                else
                {
                    throw new DomainExcepciotion("INVALID OPERATION");
                }
                stack.get(stack_index).undo_redo(payroll);

            }
            else if (operation == 12)
            {
                calendar.finishDay();
            }
            else if (payroll.isEmpty())
            {
              throw new DomainExcepciotion("THE LIST IS EMPTY");
            }
            else if(operation == 2)
            {
                int index = search(payroll);
                if (index != -1)
                {
                    remove(payroll,index);
                }
                save_state(payroll,stack,stack_index);
                stack_index = 0;
            }

            else if(operation == 3)
            {
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
                save_state(payroll,stack,stack_index);
                stack_index = 0;
            }
            else if(operation == 4)
            {
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
                save_state(payroll,stack,stack_index);
                stack_index = 0;
            }
            else if(operation == 5)
            {
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
                save_state(payroll,stack,stack_index);
                stack_index = 0;
            }

            else if(operation == 6)
            {
                int index = search(payroll);
                if(index != -1)
                {

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
                save_state(payroll,stack,stack_index);
                stack_index = 0;
            }

            else if(operation == 7)
            {
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
                save_state(payroll,stack,stack_index);
                stack_index = 0;
            }
            else if (operation == 9)
            {
                int index = search(payroll);
                if(index != -1)
                {
                    payroll.get(index).showPayment(calendar.data);
                }
            }
            else if(operation == 10)
            {
                int index = search(payroll);
                if(index != -1)
                {
                    System.out.println("DO YOU WANT:");
                    System.out.println("1 - PATTERN");
                    System.out.println("2 - MONTH FIRST DAY");
                    System.out.println("3 - MONTH DAY SEVEN");
                    System.out.println("4 - MONTH LAST DAY");
                    System.out.println("5 - WEEK ON MONDAYS");
                    System.out.println("6 - WEEK ON FRIDAYS");
                    System.out.println("7 - 2 WEEKS ON MONDAYS");
                    int aux = sc.nextInt();

                    Calendar cal_aux = Calendar.getInstance();
                    cal_aux.setTime(calendar.today);

                    if(aux == 1)
                    {
                        payroll.get(index).newPayDay_Pattern(calendar.today);
                    }
                    else if(aux == 2)
                    {
                        payroll.get(index).newPayDay_MFD(cal_aux);
                    }
                    else if(aux == 3)
                    {
                        payroll.get(index).newPayDay_MSD(cal_aux);
                    }
                    else if(aux == 4)
                    {
                        payroll.get(index).newPayDay_MLD(cal_aux);
                    }
                    else if(aux == 5)
                    {
                        payroll.get(index).newPayDay_WM(cal_aux);
                    }
                    else if(aux == 6)
                    {
                        payroll.get(index).newPayDay_WF(cal_aux);
                    }
                    else if(aux == 7)
                    {
                        payroll.get(index).newPayDay_2M(cal_aux);
                    }
                    else
                    {
                        throw new DomainExcepciotion("INVALID OPERATION");
                    }
                }
                save_state(payroll,stack,stack_index);
                stack_index = 0;
            }

            else if(operation == 11)
            {
                int index = search(payroll);
                if(index != -1)
                {
                    System.out.println(payroll.get(index).toString());
                }
            }
            else
            {
                throw new DomainExcepciotion("INVALID OPERATION");
            }
        }
        catch(InputMismatchException e)
        {
            System.out.println("WRONG INPUT DETECTED");
        }
        catch (DomainExcepciotion e)
        {
            System.out.println(e.getMessage());
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("ARRAY POSITION DOESN'T EXIST");
            stack_index = 0;
        }

        System.out.println();
        action(calendar, payroll,stack, stack_index);
    }

    private static void save_state(List<Worker> payroll, List<CopyStates> stack, int index_stack)
    {
        index_stack--;
        while (index_stack > 0)
        {
            stack.remove(index_stack);
            index_stack--;
        }
        stack.add(0,new CopyStates(payroll));
    }

    private static void remove(List <Worker> payroll, int i)
    {
        String worker_name = payroll.get(i).name;
        payroll.remove(i);
        System.out.println("THE WORKER " + worker_name + " WAS REMOVED");
    }
    private static int search(List <Worker> payroll)
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

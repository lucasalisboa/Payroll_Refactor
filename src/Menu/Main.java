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
        System.out.println("0- ADD NEW EMPLOYER");
        System.out.println("1- REMOVE A EMPLOYER");
        System.out.println("2- PUT THE POINT CARD");
        System.out.println("3- PUT THE SALE'S RESULT");
        System.out.println("4- PUT THE SERVICE TAX");
        System.out.println("5- CHANGE THE EMPLOYER INFORMATIONS");
        System.out.println("6- RUN THE PAYROLL");
        System.out.println("7- UNDO/REDO");
        System.out.println("8- SHOW THE PAYMENT SCHEDULE");
        System.out.println("9- CREATE A NEW PAYMENT SCHEDULE");
        System.out.println("10 - SHOW THE WORKER INFORMATIONS");
        System.out.println("11- FINISH THE DAY");

        try{
            int operation;
            Scanner sc = new Scanner(System.in);
            operation = sc.nextInt();

            if(operation == 1)
            {


            }
            else if(operation == 8)
            {


            }
            else if (operation == 12)
            {
                calendar.finishDay();
            }
            else if (payroll.isEmpty())
            {
              throw new DomainExcepciotion("THE LIST IS EMPTY");
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





}

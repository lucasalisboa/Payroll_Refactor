package Menu;


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
        Operation op = new Operation(payroll,stack);
        action(calendar,payroll,stack,op);
    }

    private static void action(MyCalendar calendar, List <Worker> payroll, List<CopyStates> stack, Operation op)
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
            op.operate(operation,payroll,calendar,stack);
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
            System.out.println("INVALID OPERATION");
        }

        System.out.println();
        action(calendar, payroll,stack, op);
    }





}

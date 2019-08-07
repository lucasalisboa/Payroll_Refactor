package Entities;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class SalariedWorker extends Worker {
    private double salary;

    public SalariedWorker(Date today)
    {
        addWorker();
        newPayDay_Pattern(today);
    }
    public SalariedWorker(String id, String name, String address, String payment_method, boolean syndicate, String syndicate_id, double syndicate_tax, Date today) {
        changeType(id,name,address,payment_method,syndicate, syndicate_id, syndicate_tax);
        newPayDay_Pattern(today);
    }
    public SalariedWorker(String id, String name, String address, String payment_method, boolean syndicate, String syndicate_id, double syndicate_tax,double salary)
    {
        super.changeType(id,name,address,payment_method,syndicate, syndicate_id, syndicate_tax);
        this.salary = salary;
    }

    @Override
    public void changeType(String id, String name, String address, String payment_method, boolean syndicate, String syndicate_id, double syndicate_tax)
    {
        super.changeType(id,name,address,payment_method,syndicate, syndicate_id, syndicate_tax);
        newType();
    }
    private void newType()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("WHAT'S THE SALARY?");
        salary = sc.nextDouble();
    }

    @Override
    public void addWorker()
    {
        super.addWorker();
        newType();
    }

    @Override
    public void newPayDay_Pattern(Date today)
    {
        pay_schedule = Schedule.PATTERN;
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));

        int week = checkDay(cal);
        pay_day = cal;
        pay_day.add(Calendar.DAY_OF_MONTH,week);
    }


    private int checkDay(Calendar cal)
    {
        int week = cal.get(Calendar.DAY_OF_WEEK);
        if(week == 7)
        {
            return -1;
        }
        else if(week == 1)
        {
            return -2;
        }
        return 0;
    }

    @Override
    public void payment(Date today)
    {
        System.out.println("PAYMENT: " +(salary - (salary*getSyndicate_tax()/100) ));
        check_schedule(today);
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "SalariedWorker{" +
                "salary=" + salary +
                '}';
    }
}

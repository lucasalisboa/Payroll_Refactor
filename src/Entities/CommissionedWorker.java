package Entities;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class CommissionedWorker extends Worker {
    private double base_salary;
    private double sales;
    private double percent;

    public CommissionedWorker(Date today)
    {
        addWorker();
        newPayDay_Pattern(today);
    }

    public CommissionedWorker(String id, String name, String address, String payment_method, boolean syndicate, String syndicate_id, double syndicate_tax,Date today) {
        changeType(id,name,address,payment_method,syndicate, syndicate_id, syndicate_tax);
        newPayDay_Pattern(today);
    }
    public CommissionedWorker(String id, String name, String address, String payment_method, boolean syndicate, String syndicate_id, double syndicate_tax, double base_salary, double sales, double percent)
    {
        super.changeType(id,name,address,payment_method,syndicate, syndicate_id, syndicate_tax);
        this.base_salary = base_salary;
        this.sales = sales;
        this.percent = percent;
    }

    @Override
    public void changeType(String id, String name, String address, String payment_method, boolean syndicate, String syndicate_id, double syndicate_tax)
    {
        super.changeType(id,name,address,payment_method,syndicate, syndicate_id, syndicate_tax);
        newType();
    }

    @Override
    public void addWorker()
    {
        super.addWorker();
        newType();
    }

    public void newType()
    {
        Scanner sc = new Scanner(System.in);
        sales = 0;
        System.out.println("WHAT'S THE BASE SALARY?");
        base_salary = sc.nextDouble();
        setPercent();
    }
    public void newSale()
    {
        double new_sale;
        System.out.println("WHAT'S IS THE SALE PRICE?");
        new_sale = sc.nextDouble();
        sales += (new_sale*percent/100);

    }
    public void setPercent()
    {
        System.out.println("HOW MANY PERCENTS FOR SALE?");
        percent = sc.nextDouble();
    }

    @Override
    public void newPayDay_Pattern(Date today)
    {
        pay_schedule = Schedule.PATTERN;
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        int week = 6 - cal.get(Calendar.DAY_OF_WEEK);
        pay_day.setTime(today);
        if(week > 0)
        {
            pay_day.add(Calendar.DAY_OF_MONTH,week + 7);
        }
        else
        {
            pay_day.add(Calendar.DAY_OF_MONTH,14 + week);
        }
    }

    @Override
    public void payment(Date today)
    {
        double salary = base_salary + sales;
        System.out.println("PAYMENT: " +(salary - (salary*getSyndicate_tax()/100) ));
        check_schedule(today);
    }

    public double getBase_salary() {
        return base_salary;
    }

    public double getSales() {
        return sales;
    }

    public double getPercent() {
        return percent;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "CommissionedWorker{" +
                "base_salary=" + base_salary +
                ", sales=" + sales +
                ", percent=" + percent +
                '}';
    }
}

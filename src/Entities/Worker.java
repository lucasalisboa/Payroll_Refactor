package Entities;




import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Calendar;

public abstract class Worker {
    public String id;
    public String name;
    private String address;
    private String payment_method;
    private boolean syndicate;
    private String syndicate_id;
    private double syndicate_tax;
    protected Schedule pay_schedule;
    protected Calendar pay_day = Calendar.getInstance();
    Scanner sc = new Scanner(System.in);

    public void addWorker()
    {
        System.out.println("WHAT'S THE WORKER ID?");
        id = sc.nextLine();
        pay_schedule = Schedule.PATTERN;
        changeName();
        changeAddress();
        changePayMethod();
        System.out.println("DOES THE WORKER IS SYNDICATED?");
        System.out.println("Y - YES / N - NO");
        String aux = sc.nextLine();
        if(aux.equals("N")||aux.equals("NO"))
        {
            notSyndicated();
        }
        else
        {
            beSyndicated();
        }
        sc.nextLine();
    }

    public void changeType(String id, String name, String address, String payment_method, boolean syndicate, String syndicate_id, double syndicate_tax) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.payment_method = payment_method;
        this.syndicate = syndicate;
        this.syndicate_id = syndicate_id;
        this.syndicate_tax = syndicate_tax;
    }

    public String getAddress() {
        return address;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public boolean isSyndicate() {
        return syndicate;
    }

    public String getSyndicate_id() {
        return syndicate_id;
    }

    public double getSyndicate_tax() {
        return syndicate_tax;
    }

    public void changeName()
    {

        System.out.println("WHAT'S THE WORKER NEW NAME?");
        name = sc.nextLine();
    }
    public void changeAddress()
    {
        System.out.println("WHAT'S THE WORKER NEW ADDRESS?");
        address = sc.nextLine();
    }
    public void changePayMethod()
    {
        System.out.println("WHAT'S THE WORKER NEW PAYMENT METHOD?");
        payment_method = sc.nextLine();
    }
    public void changeSyndicateId()
    {
        System.out.println("WHAT'S THE SYNDICATE ID?");
        syndicate_id = sc.nextLine();
    }
    public void changeSyndicateTax()
    {
        System.out.println("WHAT'S THE SYNDICATE TAX?");
        syndicate_tax = sc.nextDouble();
    }

    public void beSyndicated()
    {
        syndicate = true;
        changeSyndicateId();
        changeSyndicateTax();
        sc.nextLine();
    }
    public void notSyndicated()
    {
        syndicate = false;
        syndicate_id = null;
        syndicate_tax = 0;
    }

    public void changeSyndicate()
    {
      if(syndicate == true)
      {
          notSyndicated();
      }
      else {
          beSyndicated();
      }
    }

    public Calendar getPay_day() {
        return pay_day;
    }

    public abstract void newPayDay_Pattern(Date today);

    public abstract void payment(Date today);

    public void check_schedule(Date today){
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        if(pay_schedule.equals(Schedule.PATTERN))
        {
            newPayDay_Pattern(today);
        }
        else if(pay_schedule.equals(Schedule.MONTH_FIRST_DAY))
        {
            newPayDay_MFD(cal);
        }
        else if(pay_schedule.equals(Schedule.MONTH_DAY_SEVEN))
        {
            newPayDay_MSD(cal);
        }
        else if(pay_schedule.equals(Schedule.MONTH_LAST_DAY))
        {
            newPayDay_MLD(cal);
        }
        else if(pay_schedule.equals(Schedule.WEEK_ON_MONDAYS))
        {
            newPayDay_WM(cal);
        }
        else if(pay_schedule.equals(Schedule.WEEK_ON_FRIDAYS))
        {
            newPayDay_WF(cal);
        }
        else
        {
            newPayDay_2M(cal);
        }


    }
    public void newPayDay_MFD(Calendar cal)
    {
        pay_schedule = Schedule.MONTH_FIRST_DAY;

        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.add(Calendar.DAY_OF_MONTH,1);
        if(cal.get(Calendar.DAY_OF_WEEK)== Calendar.SATURDAY)
        {
            cal.add(Calendar.DAY_OF_MONTH,2);
        }
        else if(cal.get(Calendar.DAY_OF_WEEK)== Calendar.SUNDAY)
        {
            cal.add(Calendar.DAY_OF_MONTH,1);
        }
        pay_day.setTime(cal.getTime());
    }
    public void newPayDay_MSD(Calendar cal)
    {
        pay_schedule = Schedule.MONTH_DAY_SEVEN;
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.add(Calendar.DAY_OF_MONTH,7);
        if(cal.get(Calendar.DAY_OF_WEEK)== Calendar.SATURDAY)
        {
            cal.add(Calendar.DAY_OF_MONTH,2);
        }
        else if(cal.get(Calendar.DAY_OF_WEEK)== Calendar.SUNDAY)
        {
            cal.add(Calendar.DAY_OF_MONTH,1);
        }
        pay_day.setTime(cal.getTime());
    }
    public void newPayDay_MLD(Calendar cal)
    {
        pay_schedule = Schedule.MONTH_LAST_DAY;
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        if(cal.get(Calendar.DAY_OF_WEEK)== Calendar.SATURDAY)
        {
            cal.add(Calendar.DAY_OF_MONTH,-1);
        }
        else if(cal.get(Calendar.DAY_OF_WEEK)== Calendar.SUNDAY)
        {
            cal.add(Calendar.DAY_OF_MONTH,-2);
        }
        pay_day.setTime(cal.getTime());
    }
    public void newPayDay_WM(Calendar cal)
    {
        pay_schedule = Schedule.WEEK_ON_MONDAYS;
        int week = 7 +(2 - cal.get(Calendar.DAY_OF_WEEK));
        cal.add(Calendar.DAY_OF_MONTH,week);
        pay_day.setTime(cal.getTime());
    }
    public void newPayDay_WF(Calendar cal)
    {
        pay_schedule = Schedule.WEEK_ON_FRIDAYS;
        int week = 7+(5 - cal.get(Calendar.DAY_OF_WEEK));
        cal.add(Calendar.DAY_OF_MONTH,week);
        pay_day.setTime(cal.getTime());
    }
    public void newPayDay_2M(Calendar cal)
    {
        pay_schedule = Schedule.TWO_WEEKS_ON_MONDAYS;
        int week = 2 - cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DAY_OF_MONTH,(14 + week));
        pay_day.setTime(cal.getTime());
    }

    public void showPayment(SimpleDateFormat calendar)
    {
        Date cal = pay_day.getTime();
        System.out.println(pay_schedule);
        System.out.println("NEXT PAYMENT:" + calendar.format(cal));
    }

    public String toString() {
        return "Worker{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", payment_method='" + payment_method + '\'' +
                ", syndicate=" + syndicate +
                ", syndicate_id='" + syndicate_id + '\'' +
                ", syndicate_tax=" + syndicate_tax +
                '}';
    }
}

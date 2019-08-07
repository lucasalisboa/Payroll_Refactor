package Menu;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyCalendar {
    public Date today;
    public Calendar cal;
    public SimpleDateFormat data;

    public MyCalendar()
    {
        data = new SimpleDateFormat("dd/MM/yyyy");
        today = new Date();
        cal = Calendar.getInstance();
        cal.setTime(today);
    }

    public void finishDay()
    {
        cal.add(Calendar.DAY_OF_MONTH,1);
        today = cal.getTime();
    }

    public String dayWeek()
    {
        int week = cal.get(Calendar.DAY_OF_WEEK);
        if(week == 1)
        {
            return "SUNDAY";
        }
        else if(week == 2)
        {
            return "MONDAY";
        }
        else if(week == 3)
        {
            return "TUESDAY";
        }
        else if(week == 4)
        {
            return "WEDNESDAY";
        }
        else if(week == 5)
        {
            return "THURSDAY";
        }
        else if(week == 6)
        {
            return "FRIDAY";
        }
        else
        {
            return "SATURDAY";
        }
    }
}

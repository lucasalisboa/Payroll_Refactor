# Payroll_Refactor
Project for the Software Project(P3) Subject, provided by teacher Baldoino Fonseca dos Santos Neto. This project consists in change the points of code that have "Bad Smells" and apply Patterns Projects.

## Pattern Designs
### Singleton
Used in the class MyCalendar in order to make only one instance.
  
    public class MyCalendar {
    private static MyCalendar uniqueCalendar = new MyCalendar();
    public Date today;
    public Calendar cal;
    public SimpleDateFormat data;

    private MyCalendar()
    {
        data = new SimpleDateFormat("dd/MM/yyyy");
        today = new Date();
        cal = Calendar.getInstance();
        cal.setTime(today);
    }
    public static MyCalendar getInstance(){
        return uniqueCalendar;
    }
    
### Extract Class
### Command
### Memento

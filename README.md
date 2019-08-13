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
Used in the class Worker to extract the commom attributes and methods of the yours subclasses (Hourist, Salaried and Commissioned) 
### Command
Used in class Operation and in the interface Command, creating the polymorphism method Executed() and the arraylist slot (working like a hash) which each position is a class that makes a different Command

    public interface Command {
    public void execute(List<Worker> payroll, MyCalendar calendar, List<CopyStates> stack) throws DomainExcepciotion;
    }
    
    public class Operation {
    List<Command> slot;
    public int stack_index;

    public Operation(List<Worker> payroll, List<CopyStates> stack) {
        this.stack_index = 0;
        save_state(payroll,stack);
        this.slot = new ArrayList<>();
        slot.add(0,new Add());
        slot.add(1,new Remove());
        slot.add(2,new PointCard());
        slot.add(3,new SaleResult());
        slot.add(4,new ServiceTax());
        slot.add(5,new Information());
        slot.add(6,new RunPayroll());
        slot.add(7,new UndoRedo());
        slot.add(8,new ShowPayment());
        slot.add(9,new NewPayment());
        slot.add(10,new ShowInfo());
        slot.add(11,new FinishDay());
      }
    
### Memento
Used in the class CopyState to make copies of different states of object Worker

    public class CopyStates {
    private List<Worker> previous ;

    public CopyStates(List <Worker> payroll)
    {
        previous = new ArrayList<>();
        copy(payroll,previous);
    }
    public void undo_redo(List <Worker> payroll)
    {
        payroll.clear();
        copy(previous,payroll);
    }
    private void copy(List <Worker> a, List <Worker> b )
    {
        for(int i=0; i<a.size();i++)
        {
            if (a.get(i) instanceof HouristWorker)
            {
                b.add(i,new HouristWorker(a.get(i).id,a.get(i).name,a.get(i).getAddress(),a.get(i).getPayment_method(),a.get(i).isSyndicate(),a.get(i).getSyndicate_id(),a.get(i).getSyndicate_tax(),((HouristWorker) a.get(i)).getHour_salary(),((HouristWorker) a.get(i)).getHours()));
            }
            else if(a.get(i) instanceof SalariedWorker)
            {
                b.add(i,new SalariedWorker(a.get(i).id,a.get(i).name,a.get(i).getAddress(),a.get(i).getPayment_method(),a.get(i).isSyndicate(),a.get(i).getSyndicate_id(),a.get(i).getSyndicate_tax(),((SalariedWorker) a.get(i)).getSalary()));
            }
            else if(a.get(i) instanceof CommissionedWorker)
            {
                b.add(i,new CommissionedWorker(a.get(i).id,a.get(i).name,a.get(i).getAddress(),a.get(i).getPayment_method(),a.get(i).isSyndicate(),a.get(i).getSyndicate_id(),a.get(i).getSyndicate_tax(),((CommissionedWorker) a.get(i)).getBase_salary(),((CommissionedWorker) a.get(i)).getSales(),((CommissionedWorker) a.get(i)).getPercent()));
            }
        }
    }
}

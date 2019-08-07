package Memento;

import Entities.CommissionedWorker;
import Entities.HouristWorker;
import Entities.SalariedWorker;
import Entities.Worker;

import java.util.ArrayList;
import java.util.List;


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



package Menu;

import Entities.Worker;
import Exceptions.DomainExcepciotion;
import Memento.CopyStates;

import java.util.List;
import java.util.Scanner;

public class UndoRedo extends Operation implements Command {

    public UndoRedo() {
        super();
    }

    @Override
    public void execute(List<Worker> payroll, MyCalendar calendar, List<CopyStates> stack) throws DomainExcepciotion {
        int aux;
        Scanner sc = new Scanner(System.in);
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
}

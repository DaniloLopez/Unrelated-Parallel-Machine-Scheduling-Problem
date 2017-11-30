package Core;

import Model.Job;
import Model.Machine;
import Model.Solution;
import Utilities.Aleatorio;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Danilo López - dlopezs@unicauca.edu.co
 */
public class Grasp extends Grasp_Abstract{
    
    /**
     * Execute algorith GRASP + ILS to found solution for machine problem
     * @param m lenght of time to do hill climbing
     * @param EFOs number of iterations
     * @param jobs     
     * @param machines     
     * @return best solution found
     */
    @Override
    public Solution run(int m, int EFOs, List<Job> jobs, List<Machine> machines){                                
        
        Solution solution = new Solution();
        solution.setMachines(copyListMachines(machines));
        List<Job> jobsCopy = copyListJobs(jobs); //copy dates to new list from jobs
        orderList(jobsCopy); //order list of jobs
        do{ //function greedy
            int pos = foundMachineLowestFitness(solution);
            int al = new Aleatorio().aleatorioEntero(0, jobsCopy.size());
            solution.addJobToMachine(pos, jobsCopy.get(al));
            jobsCopy.remove(al);                
        }while(!jobsCopy.isEmpty());//s is a complete solution
        
        //System.out.println(best.getFitness());                
        return solution;
    }
    private int foundMachineLowestFitness(Solution solution) {        
        int pos = -1;
        List<Machine> machines = solution.getMachines();
        for (int i = 0; i < solution.getMachines().size(); i++) {
            if(pos == -1 || machines.get(i).getFitness() < machines.get(pos).getFitness())
                pos = i;
        }
        return pos;
    }
          
    public void orderList(List<Job> list){
        list.sort((Job t, Job t1) -> new Integer(t.getId()).compareTo(t1.getId()));
    }
        
    public List<Job> copyListJobs(List<Job> list){
        //metodo para copiar una lista a otra
        List<Job> copy = new ArrayList<>();
        for (Job job : list) {
            copy.add(new Job(job));
        }
        return copy;
    }  

    private List<Machine> copyListMachines(List<Machine> machines) {
        List<Machine> clone = new ArrayList<>();
        machines.stream().forEach((machine) -> {
            clone.add(new Machine(machine));
        });
        return clone;
    }
  

}

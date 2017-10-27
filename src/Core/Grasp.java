package Core;

import Model.Job;
import Model.Machine;
import Model.Solution;
import Utilities.Aleatorio;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Danilo López - dlopezs@unicauca.edu.co
 */
public class Grasp {

    /**
     * Execute algorith GRASP + ILS to found solution for machine problem
     * @param m lenght of time to do hill climbing
     * @param EFOs number of iterations
     * @param jobs     
     * @param machines     
     * @return best solution found
     */
    public Solution grasp(int m, int EFOs, List<Job> jobs, List<Machine> machines){                
        Solution best = null;        
        do{
            Solution solution = new Solution();
            List<Job> jobsCopy = copyListJobs(jobs); //copy dates to new list from jobs
            orderList(jobsCopy); //order list of jobs
            do{ //function greedy
                int pos = foundMachineLowestFitness(solution);
                int al = new Aleatorio().aleatorioEntero(0, jobsCopy.size());
                solution.addJobToMachine(pos, jobsCopy.get(al));
                jobsCopy.remove(al);                
            }while(!jobsCopy.isEmpty());//s is a complete solution                        
            
            for(int i = 0; i < m; i++){  //Hill Climbing
                Solution R = tweak(solution);
                if(solution.getFitness() < R.getFitness())
                    solution = R;
            }
            if(best == null || solution.getFitness() > best.getFitness())
                best = solution;
            if(idealSolution(best)){
                return best;
            }
            
            EFOs--;
        }while(EFOs != 0);
        
        return best;
    }

    private Solution tweak(Solution solution) {
        //alterar la solucion con alguna modificacion de parametros de idle, timeconfig no se debria tocar
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean idealSolution(Solution best) {
        //evaular se la solucion es la ideal
        for (Machine mac : best.getMachines()) {
            //if all the fitness to machines are zero, DO NOT return false;            
            if (mac.getFitness() != 0)
                return false;            
        }
        return true;
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
        List<Job> copy = new ArrayList<>(list);        
        return copy;
    }
  
}

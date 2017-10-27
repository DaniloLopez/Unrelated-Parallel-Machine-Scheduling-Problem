package Core;

import Model.Job;
import Model.Machine;
import Model.Solution;
import Utilities.Aleatorio;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
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
    public Solution run(int m, int EFOs, List<Job> jobs, List<Machine> machines){                
        Solution best = null;        
        do{
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
            
            for(int i = 0; i < m; i++){  //Hill Climbing
                Solution R = tweak(solution);
                if(solution.getFitness() > R.getFitness())
                    solution = R;
            }
            if(best == null || solution.getFitness() < best.getFitness())
                best = solution;
            if(idealSolution(best)){
                return best;
            }
            
            EFOs--;
            System.out.println(best.getFitness());
        }while(EFOs != 0);
        
        return best;
    }

    private Solution tweak(Solution solution) {
        //alterar la solucion con alguna modificacion de parametros de idle, timeconfig no se debria tocar
        int posMaquinaI;
        do{
            posMaquinaI = new Aleatorio().aleatorioEntero(0, solution.getMachines().size());
        }while(solution.getMachines().get(posMaquinaI).getJobs().isEmpty());
      
        int posTrabajoI = new Aleatorio().aleatorioEntero(0, solution.getMachines().get(posMaquinaI).getJobs().size());
        int posMaquinaF ;
        int posTrabajoF ;

        do{
            posMaquinaF = new Aleatorio().aleatorioEntero(0, solution.getMachines().size());
            posTrabajoF = new Aleatorio().aleatorioEntero(0, solution.getMachines().get(posMaquinaF).getJobs().size());
        }while(posMaquinaI==posMaquinaF && posTrabajoI==posTrabajoF);
        
 
        List<Machine> maquinas= new ArrayList<>();  
        List<Job> jobsI= new ArrayList<>();  
        List<Job> jobsF= new ArrayList<>();  
        int pos=0;
        for (Machine maquina : solution.getMachines()) 
        {
            if(pos!=posMaquinaI && pos!=posMaquinaF)
            {
                maquinas.add(maquina);
            }
            else if(posMaquinaI==posMaquinaF)
            {
                jobsI=maquina.getJobs();
                jobsF=maquina.getJobs();
                maquinas.add(new Machine(maquina.getProcessing()));
            }
            else if(pos==posMaquinaI)
            {
                jobsI=maquina.getJobs();
                maquinas.add(new Machine(maquina.getProcessing()));
            }
            else
            {
                jobsF=maquina.getJobs();
                maquinas.add(new Machine(maquina.getProcessing()));
            }
            
            pos++;
        }
        
        Solution nueva = new Solution();
        nueva.setMachines(maquinas);
        if(posMaquinaI==posMaquinaF)
        {
            Job job=jobsI.get(posTrabajoI);
            jobsI.remove(posTrabajoI);
            jobsI.add(posTrabajoF, job);
            for (Job j : jobsI) {
                nueva.addJobToMachine(posMaquinaI, j);
            }
        }
        else
        {                        
            Job job=jobsI.get(posTrabajoI);
            jobsI.remove(posTrabajoI);
            jobsF.add(posTrabajoF, job);
            for (Job j : jobsI) {
                nueva.addJobToMachine(posMaquinaI, j);
            }
            for (Job j : jobsF) {
                nueva.addJobToMachine(posMaquinaF, j);
            }
        }
        return nueva; 
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

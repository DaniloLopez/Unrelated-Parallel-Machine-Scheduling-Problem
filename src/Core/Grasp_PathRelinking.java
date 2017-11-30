package Core;

import Model.Job;
import Model.Machine;
import Model.Solution;
import Utilities.Aleatorio;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Danilo López - dlopezs@unicauca.edu.co
 */
public class Grasp_PathRelinking extends Grasp_Abstract{
            
    Aleatorio al = new Aleatorio();
    
    //mixted path relinking    
    /*Instead of starting from a
    s s o and gradually transforming it into the s s g , this variant performs
    one step from s o to s g , obtaining an intermediate s s 1 . Then s g becomes the
    initial s and s 1 the guiding s, obtaining a new intermediate s
    s 2 . In the next step of the procedure s 1 becomes the initial s and s 2 the
    guiding s, obtaining s 3 and so on. This process is executed until both paths
    joint in the middle. The main advantage of this strategy is that it explores deeply
    neighborhoods of both input solutions*/
    /**
     * Execute algorith GRASP + pathRelinking to found s for machine problem
     * @param m lenght of time to do hill climbing
     * @param EFOs number of iterations
     * @param jobs     
     * @param machines     
     * @return best s found
     */
    @Override
    public Solution run(int m, int EFOs, List<Job> jobs, List<Machine> machines){                
        int esize = 3;        
        Solution so = null;
        ArrayList<Solution> E = new ArrayList<>();
        int i = 1;
        do{                        
            Solution s = new Solution();
            s.setMachines(copyListMachines(machines));
            List<Job> jobsCopy = copyListJobs(jobs); //copy dates to new list from jobs
            orderList(jobsCopy); //order list of jobs
            do{ //function greedyRandomizedContruction
                int pos = foundMachineLowestFitness(s);
                int al = new Aleatorio().aleatorioEntero(0, jobsCopy.size());
                s.addJobToMachine(pos, jobsCopy.get(al));
                jobsCopy.remove(al);                
            }while(!jobsCopy.isEmpty());//s is a complete s
            
            for(int j = 0; j < m; j++){//localSearch(s)                
                Solution R = tweak(s, machines);                
                if(so == null || s.getFitness() > R.getFitness())
                    so = R;                
            }            
            
            if(i >= 2){
                Solution sg = E.get(al.aleatorioEntero(0, E.size()));                     
                so = mixedPathRelinking(so, sg, machines);                
                E = EliteSetUpdate(so,E,esize);
            }else{
                E = EliteSetUpdate(so,E,esize);
            }
            i++;       
            //System.out.println("efos:" + EFOs);
            EFOs--;           
            if(EFOs == 4934){
                int n = 0;
            }
        }while(EFOs != 0);
        
        return E.get(minFitnessE(E));
    }    
    
    private ArrayList<Solution> EliteSetUpdate(Solution so, ArrayList<Solution> E, int esize){
        int max = maxFitnessE(E);
        if(E.size() == esize){
            if(so.getFitness() <= E.get(max).getFitness() && !E.contains(so)){
                E.remove(max);
                E.add(so);
            }
        }
        else{
            if(!E.contains(so))
                E.add(so);            }
        return E;
    }    
    
    public int maxFitnessE(ArrayList<Solution> E){
        double aux = Double.MIN_VALUE;
        int pos = 0;
        for (int i = 0; i< E.size(); i++) {
            if(E.get(i).getFitness() > aux){
                pos = i;
                aux = E.get(i).getFitness();
            }                
        }
        return pos;
    }
    
    public Solution mixedPathRelinking(Solution so, Solution sg, List<Machine> machines){
        List<Machine> initial = so.getMachines(), guia = sg.getMachines();        
        
        int alMachineInitial;
        do
            alMachineInitial = al.aleatorioEntero(0, initial.size());
        while(initial.get(alMachineInitial).getJobs().isEmpty());
        int alJobInitial = al.aleatorioEntero(0, initial.get(alMachineInitial).getJobs().size());
        
        
        int alMachineInitial2;        
        int alJobInitial2;
        do{
            do
                alMachineInitial2 = al.aleatorioEntero(0, initial.size());
            while(initial.get(alMachineInitial2).getJobs().isEmpty());                        
            alJobInitial2 = al.aleatorioEntero(0, initial.get(alMachineInitial2).getJobs().size());        
        }while(alJobInitial == alJobInitial2 && alMachineInitial == alMachineInitial2);
        
        Solution nueva = new Solution();
        nueva.setMachines(copyListMachines(machines));
        
        for (int i = 0; i < initial.size(); i++) {
            for (int j = 0; j < initial.get(i).getJobs().size(); j++) {
                Job value = initial.get(i).getJobs().get(j);                
                if(i == alMachineInitial && j == alJobInitial){                    
                    value = initial.get(alMachineInitial2).getJobs().get(alJobInitial2);
                }
                if(i == alMachineInitial2 && j == alJobInitial2){                    
                    value = initial.get(alMachineInitial).getJobs().get(alJobInitial);
                }
                nueva.addJobToMachine(i,value);                
            }            
        }
        
//        System.out.println("========");
//        System.out.println(nueva.toString());
//        System.out.println("========");
        return nueva;
    }
    
    public int minFitnessE(ArrayList<Solution> E){
        double aux = Double.MAX_VALUE;
        int pos = 0;
        for (int i = 0; i< E.size(); i++) {
            if(E.get(i).getFitness() < aux){
                pos = i;
                aux = E.get(i).getFitness();
            }                
        }
        return pos;
    }    
    
    private Solution tweak(Solution solution, List<Machine> machine) {
        //alterar la solucion con alguna modificacion de parametros de idle, timeconfig no se debria tocar                
        List<Machine> initial = solution.getMachines();
        int alMachineInitial;
        do
            alMachineInitial = al.aleatorioEntero(0, initial.size());
        while(initial.get(alMachineInitial).getJobs().isEmpty());
        int alJobInitial = al.aleatorioEntero(0, initial.get(alMachineInitial).getJobs().size());
        
        
        int alMachineInitial2;        
        int alJobInitial2;
        do{
            do
                alMachineInitial2 = al.aleatorioEntero(0, initial.size());
            while(initial.get(alMachineInitial2).getJobs().isEmpty());                        
            alJobInitial2 = al.aleatorioEntero(0, initial.get(alMachineInitial2).getJobs().size());        
        }while(alJobInitial == alJobInitial2 && alMachineInitial == alMachineInitial2);        
        
        Solution nueva = new Solution();
        nueva.setMachines(copyListMachines(machine));
        
        for (int i = 0; i < initial.size(); i++) {
            for (int j = 0; j < initial.get(i).getJobs().size(); j++) {
                Job value = initial.get(i).getJobs().get(j);                
                if(i == alMachineInitial && j == alJobInitial){                    
                    value = initial.get(alMachineInitial2).getJobs().get(alJobInitial2);
                }
                if(i == alMachineInitial2 && j == alJobInitial2){                    
                    value = initial.get(alMachineInitial).getJobs().get(alJobInitial);
                }
                nueva.addJobToMachine(i,value);                
            }            
        }
        return nueva; 
    }

    private boolean idealSolution(Solution best) {
        //evaular se la solucion es la ideal
        for (Machine mac : best.getMachines()) {
            //if all the fitness to machines are zero, DO NOT return false;            
            if (mac.getFitness() != 0.1)
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

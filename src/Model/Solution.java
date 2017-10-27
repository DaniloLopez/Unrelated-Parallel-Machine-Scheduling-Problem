package Model;

import Utilities.Aleatorio;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Danilo López - dlopezs@unicauca.edu.co
 */
public class Solution {
    List<Machine> machines;    

    public Solution() {
        machines = new ArrayList<>();        
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }   
    
    public boolean addJobToMachine(int m, Job job){
        job.setProcesingTime(machines.get(m).getProcessing()); // set procesing time                
        if(machines.get(m).getJobs().size() > 0){            
            //add idle and configuration time in respecty list
            machines.get(m).addIdle(generateIdle(job.getProcesingTime()));
            machines.get(m).addConfigTime(generateConfigTime(job.getProcesingTime()));            
        }
        double start = calculateTimeStarProcesing(m);
        //asks if the job can begin at the exact time for its due date
        if(start < (job.getDueDate() - job.getProcesingTime())){
            job.setTimeStartProcesing(start);//set start procesing to job
        }else{
            job.setTimeStartProcesing(start);//set start procesing to job
        }      
        machines.get(m).addJob(job);
        machines.get(m).calculateObjetiveFunction();
        return  true;//add job in jobs list of a machine
    }        

    private double generateIdle(double procesingTime) {
        //se generan numero aleatoriamente entre o y el tiempo de procesamiento del nuevo job
        return new Aleatorio().aleatorioReal(0, procesingTime);
    }

    private double generateConfigTime(double procesingTime) {
        //se generan numero aleatoriamente entre o y el tiempo de procesamiento del nuevo job        
        return new Aleatorio().aleatorioReal(0, procesingTime);
    }

    private double calculateTimeStarProcesing(int m) {
        //se debe sumar los timepos de procesamiento, configuracion y espera anteriores
        double suma = 0;
        for (Machine machine : machines) {
            List<Job> jobs = machine.getJobs();            
            for (int i = 0; i < jobs.size(); i++) {
                suma += jobs.get(i).getProcesingTime();
                if(i < machine.getIdle().size()){
                    suma += machine.getConfigTime().get(i) + machine.getIdle().get(i);                    
                }
            }
        }        
        return suma;
    }

 
    /**
     * clone objet Solution
     * @return copy of Solution
     * @throws java.lang.CloneNotSupportedException
     */
    @Override
    public Solution clone() throws CloneNotSupportedException{        
        return (Solution)super.clone();       
    } 
    
    public double getFitness(){
        int sum = 0;
        for (Machine machine : machines) {
            sum += machine.getFitness();
        }
        return sum;
    }
}

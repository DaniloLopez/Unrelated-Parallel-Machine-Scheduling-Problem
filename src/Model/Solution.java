package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Danilo López - dlopezs@unicauca.edu.co
 */
public class Solution {
    List<Machine> machines;
    double fitness;

    public Solution() {
        machines = new ArrayList<>();
        fitness = Double.MAX_VALUE;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
    
    public boolean addJobToMachine(int m, Job job){
        job.setProcesingTime(machines.get(m).getProcessing()); // set procesing time                
        if(machines.get(m).getJobs().size() > 0){            
            //add idle and configuration time in respecty list
            machines.get(m).addIdle(generateIdle(job.getProcesingTime()));
            machines.get(m).addConfigTime(generateConfigTime(job.getProcesingTime()));            
        }
        job.setTimeStartProcesing(calculateTimeStarProcesing(m));//set start procesing to job
        return machines.get(m).addJob(job); //add job in jobs list of a machine
    }        

    private double generateIdle(double procesingTime) {
        //se generan a aleatoriamente entre o y el tiempo de procesamiento del nuevo job
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private double generateConfigTime(double procesingTime) {
        //se generan a aleatoriamente entre o y el tiempo de procesamiento del nuevo job
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private double calculateTimeStarProcesing(int m) {
        //se debe sumar los timepos de procesamiento, configuracion y espera anteriores
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        
    public void calculateObjetiveFunction(){
        double sum = 0;
        for (Machine machine : machines) {
            for (Job job : machine.getJobs()) {
                sum = job.getAj()*job.getEarlines() + job.getBj()*job.getTardiness();
            }
        }
        fitness = sum;
    }
}

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
        return machines.get(m).addJob(job);
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
        
    }
    
    
}

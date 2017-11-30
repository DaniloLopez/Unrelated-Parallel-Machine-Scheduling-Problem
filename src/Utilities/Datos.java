package Utilities;

import Model.Job;
import Model.Machine;
import java.util.ArrayList;

/**
 *
 * @author Danilo López - dlopezs@unicauca.edu.co
 */
public class Datos {
    
    ArrayList<Machine> machines;
    ArrayList<Job> jobs;

    public Datos() {
        this.machines = new ArrayList<>();
        this.jobs = new ArrayList<>();
    }

    public Datos(ArrayList<Machine> machines, ArrayList<Job> jobs) {
        this.machines = machines;
        this.jobs = jobs;
    }
       
    public ArrayList<Machine> getMachines() {
        return machines;
    }
    
    public boolean addMachine(Machine machine){
        return machines.add(machine);
    }

    public void setMachines(ArrayList<Machine> machines) {
        this.machines = machines;
    }

    public ArrayList<Job> getJobs() {
        return jobs;
    }
    
    public boolean addJob(Job job){
        return jobs.add(job);
    }

    public void setJobs(ArrayList<Job> jobs) {
        this.jobs = jobs;
    }
    
    

}

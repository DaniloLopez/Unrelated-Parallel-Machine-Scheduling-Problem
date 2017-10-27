package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Danilo López - dlopezs@unicauca.edu.co
 */
public class Machine implements Cloneable{
    
    List<Job> jobs;
    List<Double> configTime;
    List<Double> idle;
    double processing; //capacity processing        
    double fitness;

    public Machine(double processing) {
        this.processing = processing;
        this.configTime = new ArrayList<>();
        this.idle = new ArrayList<>();
        jobs = new ArrayList<>();     
        fitness = Double.MIN_VALUE;
    }

    public Machine(Machine machine) {
        processing = machine.getProcessing();
        this.configTime = new ArrayList<>();
        this.idle = new ArrayList<>();
        jobs = new ArrayList<>();     
        fitness = Double.MIN_VALUE;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
        
    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public double getProcessing() {
        return processing;
    }

    public void setProcessing(double processing) {
        this.processing = processing;
    }                    

    public List<Double> getConfigTime() {
        return configTime;
    }

    public void setConfigTime(List<Double> configTime) {
        this.configTime = configTime;
    }

    public List<Double> getIdle() {
        return idle;
    }

    public void setIdle(List<Double> idle) {
        this.idle = idle;
    }
    
    //<editor-fold defaultstate="collapsed" desc="add elements in lists">    
    public boolean addConfigTime(double time){
        return this.configTime.add(time);        
    }
    
    public boolean  addIdle(double idle){
        return this.idle.add(idle);
    }
    
     public void calculateObjetiveFunction(){ 
        double sum = 0;
        for (Job job : jobs) {
            sum += job.getAj()*job.getEarlines() + job.getBj()*job.getTardiness();
        }
        fitness = sum;
    }
     
    public boolean addJob(int pos, Job job){
        try{
            jobs.add(pos, job);
            return true;
        }catch(Exception e){
            System.out.println("error adding job in position: " + pos +". err. " + e.getMessage());
        }
        return false;
    }
    
    public boolean addJob(Job job){
        return jobs.add(job);
    }
    //</editor-fold>
    
        
    public Machine copy(){
        try{
            return (Machine)super.clone();
        }catch(Exception e){
            System.out.println("error clonando machine: " + e.getMessage());
        }
        return null;
    }
            
}

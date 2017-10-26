package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Danilo López - dlopezs@unicauca.edu.co
 */
public class Machine{
    
    List<Job> jobs;
    double processing; //capacity processing        

    public Machine(double processing) {
        this.processing = processing;
        jobs = new ArrayList<>();        
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
    
    public boolean addJob(Job job){
        return jobs.add(job);
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
        
    public Machine copy(){
        try{
            return (Machine)super.clone();
        }catch(Exception e){
            System.out.println("error clonando machine: " + e.getMessage());
        }
        return null;
    }
            
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metaheuristicas_grasp;

import Core.Grasp;
import Model.Job;
import Model.Machine;
import Model.Solution;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Danilo
 */
public class Metaheuristicas_Grasp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int EFOs = 5000;
        int numberJobs = 6; //number of jobs
        int numberMachines = 2; //number of machines        
        int numberRepeatHC = 5; //number of repetitions to run algorithm hill climbing search
        
        List<Job> jobs = new ArrayList<>();
        List<Machine> machines = new ArrayList<>();
        
        jobs.add(new Job(1, 3, 0.2, 0.2, 14));
        jobs.add(new Job(2, 3, 0.3, 0.2, 3));
        jobs.add(new Job(3, 3, 0.2, 0.3, 15));
        jobs.add(new Job(4, 3, 0.1, 0.2, 13));
        jobs.add(new Job(5, 3, 0.2, 0.2, 13));
        jobs.add(new Job(6, 3, 0.2, 0.1, 9));
        
        machines.add(new Machine(3));
        machines.add(new Machine(4));
                
        Grasp grasp = new Grasp();                
        Solution res = grasp.run(numberRepeatHC, EFOs, jobs, machines);
        
        System.out.println(res.toString());
    }
    
}


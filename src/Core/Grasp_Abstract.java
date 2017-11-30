package Core;

import Model.Job;
import Model.Machine;
import Model.Solution;
import java.util.List;

/**
 *
 * @author Danilo López - dlopezs@unicauca.edu.co
 */
public abstract class Grasp_Abstract {
    
    public abstract Solution run(int m, int EFOs, List<Job> jobs, List<Machine> machines);

}

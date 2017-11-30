package Archivo;

/**
 *
 * @author Danilo López - dlopezs@unicauca.edu.co
 */
public class Resultado {
        
    private int machines;
    private int jobs;
    private String algoritmo;
    private long time;
    private double fitness;    
    private String solution;

    /**
     * 
     * @param machines
     * @param jobs
     * @param algoritmo
     * @param time
     * @param fitness
     * @param solution 
     */
    public Resultado(int machines, int jobs, String algoritmo, long time, double fitness, String solution) {
        this.machines = machines;
        this.jobs = jobs;
        this.algoritmo = algoritmo;
        this.time = time;
        this.fitness = fitness;
        this.solution = solution;
    }

    public int getMachines() {
        return machines;
    }

    public void setMachines(int machines) {
        this.machines = machines;
    }

    public int getJobs() {
        return jobs;
    }

    public void setJobs(int jobs) {
        this.jobs = jobs;
    }

    public String getAlgoritmo() {
        return algoritmo;
    }

    public void setAlgoritmo(String algoritmo) {
        this.algoritmo = algoritmo;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
    

    

}

package Model;

import java.util.Objects;

/**
 *
 * @author Danilo López - dlopezs@unicauca.edu.co
 */
public class Job {
    int id;
    double complexity; //indica la complejidad del trabajo que se ingresa
    double procesingTime;  //Pjk - Ci,k = Completion time of job i at machine k
    double timeStartProcesing;
    double dueDate;         //dj - due date
    double aj;              //alpha Aj.
    double bj;              //betha Bj.
    double earlines;        //  Ei = Earliness of job i
    double tardiness;       // Ti = Tardiness of job i

    public Job(int id, double dueDate, double aj, double bj, double complex ) {
        this.id = id;
        this.dueDate = dueDate;
        this.aj = aj;
        this.bj = bj;
        this.complexity = complex;
    }

    public Job(Job get) {
        id = get.getId();
        complexity = get.getComplexity();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getProcesingTime() {
        return procesingTime;
    }

    public void setProcesingTime(double capacityProcessingMachine) {
        this.procesingTime = complexity / capacityProcessingMachine;
    }

    public double getDueDate() {
        return dueDate;
    }

    public void setDueDate(double dueDate) {
        this.dueDate = dueDate;
    }

    public double getAj() {
        return aj;
    }

    public void setAj(double aj) {
        this.aj = aj;
    }

    public double getBj() {
        return bj;
    }

    public void setBj(double bj) {
        this.bj = bj;
    }

    public double getEarlines() {
        return earlines;
    }

    public void setEarlines(double earlines) {
        this.earlines = earlines;
    }

    public double getTardiness() {
        return tardiness;
    }

    public void setTardiness(double tardiness) {
        this.tardiness = tardiness;
    }

    public double getComplexity() {
        return complexity;
    }

    public void setComplexity(double complexity) {
        this.complexity = complexity;
    }

    public double getTimeStartProcesing() {
        return timeStartProcesing;
    }

    public void setTimeStartProcesing(double timeStartProcesing) {
        this.timeStartProcesing = timeStartProcesing;
        this.earlines = Math.max(0, (this.dueDate - (this.timeStartProcesing + this.procesingTime)));
        this.tardiness = Math.max(0, ((this.timeStartProcesing + this.procesingTime) - this.dueDate));
    }       
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.dueDate);
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.aj) ^ (Double.doubleToLongBits(this.aj) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.bj) ^ (Double.doubleToLongBits(this.bj) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Job other = (Job) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Job{" + "id=" + id + ", dueDate=" + dueDate + ", aj=" + aj + ", bj=" + bj + '}';
    }
    
    
    
}

package Model;

import java.util.Objects;

/**
 *
 * @author Danilo López - dlopezs@unicauca.edu.co
 */
public class Job {
    int id;
    double complex; //indica la complejidad del trabajo que se ingresa
    double processingTime;  //Pjk - Ci,k = Completion time of job i at machine k
    String dueDate;         //dj - due date
    double aj;              //alpha Aj.
    double bj;              //betha Bj.
    double earlines;        //  Ei = Earliness of job i
    double tardiness;       // Ti = Tardiness of job i

    public Job(int id, String dueDate, double aj, double bj) {
        this.id = id;
        this.dueDate = dueDate;
        this.aj = aj;
        this.bj = bj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(double processingTime) {
        this.processingTime = processingTime * complex;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
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

    public double getComplex() {
        return complex;
    }

    public void setComplex(double complex) {
        this.complex = complex;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Archivo.EscribirArchivo;
import Archivo.LeerArchivoTrabajo;
import Archivo.Resultado;
import Core.Grasp;
import Core.Grasp_Abstract;
import Core.Grasp_HillClimbing;
import Core.Grasp_PathRelinking;
import Core.Grasp_Tabu;
import Model.Job;
import Model.Machine;
import Model.Solution;
import Utilities.Datos;
import com.sun.org.apache.xml.internal.security.encryption.AgreementMethod;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Danilo
 */
public class Metaheuristicas_Grasp {
     
    private static List<String> nombreArchivos= new ArrayList<>();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        int EFOs = 5000;             
        int numberRepeatHC = 7; //number of repetitions to run algorithm hill climbing search                
        
        agregarnombreArchivos();
        
        ArrayList<Grasp_Abstract> algoritms = new ArrayList<>();
        algoritms.add(new Grasp());
        algoritms.add(new Grasp_HillClimbing());
        algoritms.add(new Grasp_PathRelinking());
        algoritms.add(new Grasp_Tabu());                                
        
        ArrayList<Resultado> resFinal = new ArrayList<>();               
        ArrayList<ArrayList<Resultado>> resultadoGlobal = new ArrayList<>();        
        for (String archivo : nombreArchivos) {            
            LeerArchivoTrabajo la = new LeerArchivoTrabajo();
            Datos datos = la.leerArchivo(archivo);            
            List<Job> jobs = (List<Job>)datos.getJobs().clone();               //leer archivos de tareas            
            List<Machine> machines = (List<Machine>)datos.getMachines().clone();   //leer archivo maquinas                        
                for (Grasp_Abstract algoritm : algoritms) {        
                long ti = System.currentTimeMillis();
                Solution res = algoritm.run(numberRepeatHC, EFOs, jobs, machines);
                long tf = System.currentTimeMillis();
                long tt = tf - ti;
                //System.out.println("Algoritmo: " + algoritm.getClass().getName() +" tamaño: " + machines.size() + "/" + jobs.size()+  " fitness: " + res.getFitness() + " solucion: " + res.toString() );
//                System.out.println(res.toString());
//                System.out.println("fitness: " + res.getFitness()+ "   tiempo " +tt+"\n\n");
                resFinal.add(new Resultado(machines.size(),jobs.size(),algoritm.getClass().getSimpleName(), tt, res.getFitness(), res.toString()));
            }                        
           //System.out.println("");
            resultadoGlobal.add((ArrayList<Resultado>)resFinal.clone());
            resFinal.clear();
        }
        
        EscribirArchivo ea = new EscribirArchivo();
        ea.excribirResultados("resultados.txt", resultadoGlobal);    
        System.out.println("El programa a terminado, puede ver los resultados en la carpeta Archivos/resultados del directorio del proyecto.");
    }         
   
    
    public static void agregarnombreArchivos() {
        nombreArchivos.add("DataSet_1.txt");
        nombreArchivos.add("DataSet_2.txt");
        nombreArchivos.add("DataSet_3.txt");
        nombreArchivos.add("DataSet_4.txt");
        nombreArchivos.add("DataSet_5.txt");
        nombreArchivos.add("DataSet_6.txt");
        nombreArchivos.add("DataSet_7.txt");
        nombreArchivos.add("DataSet_8.txt");
        nombreArchivos.add("DataSet_9.txt");
        nombreArchivos.add("DataSet_10.txt");        
    }
}


//            jobs.add(new Job(1, 3, 0.2, 0.2, 14));
    //            jobs.add(new Job(2, 3, 0.3, 0.2, 3));
    //            jobs.add(new Job(3, 3, 0.2, 0.3, 15));
    //            jobs.add(new Job(4, 3, 0.1, 0.2, 13));
    //            jobs.add(new Job(5, 3, 0.2, 0.2, 13));
    //            jobs.add(new Job(6, 3, 0.2, 0.1, 9));
    //            
    //            machines.add(new Machine(3));
    //            machines.add(new Machine(4));}
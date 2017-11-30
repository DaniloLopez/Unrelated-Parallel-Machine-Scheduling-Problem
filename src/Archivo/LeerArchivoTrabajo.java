package Archivo;

import Model.Job;
import Model.Machine;
import Utilities.Datos;

/**
 *
 * @author Danilo
 */
public class LeerArchivoTrabajo{
    Archivo file;    

    public LeerArchivoTrabajo() {
        file = new Archivo();        
    }    
    
    public Datos leerArchivo(String nombre){                
        Datos datos = new Datos();
        String linea;
        file.abrirArchivo(nombre, false, false, false);
        linea = file.leerArchivo();
        String[] cantidad = linea.split(" ");
        while(file.puedeLeer()){
            for (int i = 0; i < Integer.parseInt(cantidad[0]); i++) {
                datos.addMachine(new Machine(Integer.parseInt(file.leerArchivo())));
            }
            for (int j = 0; j < Integer.parseInt(cantidad[1]); j++) {
                linea = file.leerArchivo();
                String[] d = linea.split(",");
                datos.addJob(new Job(Integer.parseInt(d[0]), Double.parseDouble(d[1]), Double.parseDouble(d[2]),Double.parseDouble(d[3]), Double.parseDouble(d[4])));
            }                            
        }
        file.cerrarArchivo();
        return datos;
    }                  
}
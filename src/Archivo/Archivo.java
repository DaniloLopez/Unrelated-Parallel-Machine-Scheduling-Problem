/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Archivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Danilo
 */
public class Archivo {
        
    private BufferedWriter archivoEscritura;
    private BufferedReader archivoLectura;
    private String rutaRelativa;

    /**
     * abre un buffer de lectura o de escritura para un archivo
     * @param nombre nombre del archivo que se desea manejar
     * @param escritura TRUE si se desea escribir en el archivo, FALSE si se desea leer
     * @param sobreescribir indica si se va a sobrescribir el archivo o no
     * @param esResultados true si se desea abrir archivo de resultados, FALSE para archivos de dataset
     */
    public void abrirArchivo(String nombre, boolean escritura, boolean sobreescribir, boolean esResultados){     
        String ubicacion;
        if(esResultados){ubicacion = obtenerRutaResultados()+ nombre;}
        else            {ubicacion = obtenerRutaDataset()+ nombre;}        
        
        try {
            if (escritura)
            {                
                this.archivoEscritura = new BufferedWriter(new FileWriter(ubicacion,sobreescribir));                      
            }
            else{
                this.archivoLectura=new BufferedReader(new FileReader(ubicacion));
            }
        
        }catch(FileNotFoundException fnfe){
            System.out.println("El archivo: " + nombre + ", no se enceuntra en la carpeta 'Archivos' del directorio del proyecto.");
            System.exit(0);
        }catch (IOException ex) {
            System.out.println("Ocurrio un erro abriendo el archivo. err:" + ex.getMessage());
        }
    }

    /**
     * escribir contenido en un archivo
     * @param datos contenido que se desea escribir en el archivo
     * @param saltar indica si se coloca un salto de linea lueo de terminar de escribir en el archivo
     * 
     */
    public void escribirArchivo(String datos, boolean saltar){
        try {
            archivoEscritura.write(datos);
            //colocar el puntero en la siguiente linea(simula enter)
            if (saltar) archivoEscritura.newLine();
        } catch (IOException ex) {
            System.out.println("Ocurrio un error escribiendo en el archivo. err:" + ex.getMessage());
        }
    }	    

    /**
     * lee una linea del archivo
     * @return cadena con el contenido de la linea que se leyo
     */
    public String leerArchivo(){
        try {
            return archivoLectura.readLine();
        } catch (IOException ex) {
            System.out.println("Ocurrio un error al leer el archivo. err:" + ex.getMessage());
        }
        return "";
    }	

    /**
     * cierra el buffer de lectura o escitura del archivo
     */
    public void cerrarArchivo(){
        try {
            if(archivoEscritura!=null) archivoEscritura.close();
            if(archivoLectura!=null) archivoLectura.close();
        } catch (IOException ex) {
            System.out.println("ocurrio un erro cerrando el archivo. err:" + ex.getMessage());
        }
    }

    /**
     * indica si se puede leer el archivo
     * @return verdadero si se puede leer, falso si no se puede leer
     */
    public boolean puedeLeer(){
        try {
            return archivoLectura.ready();
        } catch (IOException ex) {
            System.out.println("ocurrio un error consultando estado del archivo. err:" + ex.getMessage());            
        }
        return false;
    }

    /**
     * lee una cantidad de palabras indicada por el usuario. el archvo debe estar abierto
     * @param cantidad cantidad de palabras que se desean leer
     * @return vector con las palabras leidas del archivo
     */
    public String[] LeerPalabras(int cantidad){
        String[] palabras= new String[cantidad];
        int i=0;        
        while(this.puedeLeer() && i< cantidad){
            palabras[i] = this.leerArchivo();
            i ++;
        }          
        return palabras;
    }
		    
    /**
     * Cuenta la cantidad de lineas que tiene un archivo
     * @param nombre nombre del archivo del cual se desean contar la cantidad de lineas
     * @return numero entero con la cantidad de lineas
     */
    public int contarLineas(String nombre)
    {
        String ubicacion = Archivo.obtenerRutaDataset()+ nombre;
        abrirArchivo(ubicacion,false, false, false);
        int lineas = 0;
        while (puedeLeer()){
                leerArchivo();
                lineas++;
        }
        cerrarArchivo();
        return lineas;		
    }	
    
    /**
     * retorna la ubicacion de la carpeta archivos en el directorio del proyecto
     * @return 
     */
    public static String obtenerRutaResultados() {
            File miDir = new File(".");
            String ruta = "";
            try {                
                String sep = System.getProperty("file.separator"); //consulta el separador usado depediendo el sistema operativo
                ruta = miDir.getCanonicalPath()+sep + "Archivos"+sep+"Resultados" + sep;                
            } catch (Exception e) {
                System.out.println("Error al leer el archivo. clase Archivo, metodo obtenerRuta resultados");
            }
            return ruta;
    }
    
    /**
     * retorna la ubicacion de la carpeta archivos en el directorio del proyecto
     * @return 
     */
    public static String obtenerRutaDataset() {
            File miDir = new File(".");
            String ruta = "";
            try {                
                String sep = System.getProperty("file.separator"); //consulta el separador usado depediendo el sistema operativo
                ruta = miDir.getCanonicalPath()+sep + "Archivos"+sep+"DataSet" + sep;                
            } catch (Exception e) {
                System.out.println("Error al leer el archivo. clase Archivo, metodo obtenerRuta dataset");
            }
            return ruta;
    }
    
}

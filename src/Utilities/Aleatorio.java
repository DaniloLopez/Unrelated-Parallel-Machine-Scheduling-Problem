package Utilities;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Danilo
 */
public class Aleatorio {
    
    private int semilla ;
    private static Random numAleatorio;
    
    /**
     * inicializa la semilla de la clase, luego de esto se ignora la semilla     
     */
    public Aleatorio(){   
        if(null == numAleatorio){
            this.semilla = 3;
            numAleatorio = new Random(semilla);
        }
    }
    
    /**
     * encuentra un numero aleatorio entre 0 y 1
     * @param cantDec cantidad de decimales con los que se desea el numero
     * @return numero aleatorio entre 0 a 1
     */
    public double nextAleatorio(int cantDec){
        double numero = numAleatorio.nextDouble();
        String number = String.format("%."+cantDec+"f", numero);
        String str = String.valueOf(number);
        String num = str.substring(0, str.indexOf(','));
        String numDec = str.substring(str.indexOf(',') + 1);
        return Double.parseDouble(num +"." + numDec);
    }
    
    
    
    /**
     * funcion para calcular un vector de booleanos aleatorios
     * @param min valor minimo del rango
     * @param max valor maximo del rango
     * @param cantidad tamaño del vector
     * @param prob probabilidad de colocar un numero diferente de cero
     * @param cantDecimales cantidad de decimales a manejar
     * @return numero aleatorio entre min y max
     */    
    public Double[] vectorAleatorioDecimalProb(double min, double max, int cantidad, double prob, int cantDecimales){                
        ArrayList<Double> vec = new ArrayList<>();   
        
        //ciclo desde cero hasta cantidad-1
        for (int i = 0; i < cantidad; i++) {                        
            if(numAleatorio.nextDouble() <= prob){
                // Numero real aleatorio entre min y max          
                double al = nextAleatorio(cantDecimales) * (max - min) + min;
                vec.add(redondearDecimales(al, cantDecimales));           
            }else{
                vec.add(0.0);
            }                    
        }                        
        return  vec.toArray(new Double[cantidad]);
    }
    
 
    /**
     * genera un vector de booleanos aleatorios
     * @param longitud longitud del vector que se desea crear
     * @return vector con valores booleanos aleatorios
     */
    public Boolean[] vectorAleatorioBooleano(int longitud) {        
        Boolean[] datos = new Boolean[longitud];

        for (int i = 0; i < longitud; i++) {                        
            if(numAleatorio.nextDouble() <= 0.5){
                // Numero real aleatorio entre min y max          
                datos[i] = false;                
            }else{
                datos[i] = true;
            }                    
        }
        return  datos;
    }
    
    /**
     * genera un vector de booleanos aleatorios
     * @param longitud longitud del vector que se desea crear
     * @return vector con valores booleanos aleatorios
     */
    public int[] vectorAleatorioBinarioEntero(int longitud) {        
        int[] datos = new int[longitud];

        for (int i = 0; i < longitud; i++) {                        
            if(numAleatorio.nextDouble() <= 0.5){
                // Numero real aleatorio entre min y max          
                datos[i] = 0;
            }else{
                datos[i] = 1;
            }                    
        }
        return  datos;
    }
    
    
    /**
     * funcion para calcular un vector de numero aleatorios     
     * @param min valor minimo del rango
     * @param max valor maximo del rango
     * @param cantidad tamaño del veector
     * @return numero aleatorio entre min y max
     */    
    public Double[] vectorAleatorioDecimal(double min, double max, int cantidad){                
        ArrayList<Double> vec = new ArrayList<>();   
        
        //ciclo desde cero hasta cantidad-1
        for (int i = 0; i < cantidad; i++) {                                    
                // Numero real aleatorio entre min y max                
                vec.add(numAleatorio.nextDouble() * (max - min) + min);                       
        }                        
        return  vec.toArray(new Double[cantidad]);
    }
    
    public double[] vectorAleatorioDecimal2(double min, double max, int cantidad){                        
        double[] vec= new double[cantidad];
        //ciclo desde cero hasta cantidad-1
        for (int i = 0; i < cantidad; i++) {                                    
                // Numero real aleatorio entre min y max                
                vec[i] = numAleatorio.nextDouble() * (max - min) + min;
        }                        
        return vec;
    }

    
    public double numberRandomByGaussian(){
        double u = 0;
        double o = 0;
        double x = 0, y = 0, w = 0;
        
        do{
            x = numAleatorio.nextDouble();
            y = numAleatorio.nextDouble();
            w = (x*x) + (y*y);
        }while(0 < w || w < 1);
        
        double g = u + x*o*(Math.sqrt(-2*((Math.log(w))/w)));
        double h = u + y*o*(Math.sqrt(-2*((Math.log(w))/w)));
        
        return g;                
    }           
    
    
    /**
     * funcion para calcular un numero entero aleatorio entre un rango de valores
     * @param min valor minimo del rango
     * @param max valor maximo del rango
     * @return numero aleatorio entre min y max
     */    
    public int aleatorioEntero(int min, int max){                
        // Numero entero entre min y max
        int al;
        if(max==0){
             al=0;
        }else{
            if(max - min <= 0)
                System.out.println("min: " + min + " max: " + max);                
            al = numAleatorio.nextInt(max-min) + min; 
        }                                                       
        return al;
    }    
    
    /**
     * funcion para calcular un numero real aleatorio entre un rango de valores
     * @param min valor minimo del rango
     * @param max valor maximo del rango
     * @return  numero aleatorio enntre min y max
     */
    public double aleatorioReal(double min, double max){                
        // Numero entero entre min y max
        if(max - min <= 0)
            System.out.println("min: " + min + " max: " + max);
                
        double al = numAleatorio.nextDouble() * (max - min) + min;                
        return al;
    }
    
    /**
     * redondea el numero de decimales del numero que llega por parametro
     * @param valorInicial numero a redndear
     * @param numeroDecimales cantdad de decimales con los cuales se desea dejar
     * @return valorInicial con numeroDecimales numero de decimales
     */
    public double redondearDecimales(double valorInicial, int numeroDecimales){                     
        int n = 1;
        for (int i = 0; i < numeroDecimales; i++) {
            n = n*10;
        }        
        double rounded = (double) Math.round(valorInicial * n) / n;                     
        return rounded;
    }

   
    
    
}

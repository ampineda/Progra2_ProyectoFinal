/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author mack
 */
public class pruebaImprimir {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // TODO code application logic here
        
        String[] imprime = new String[3];
        imprime[0] = "IMPRIMIR LINEA 1";
        imprime[1] = "\n";
        imprime[2] = "IMPRIMIR LINEA 3";

        //MiMensaje.MuestraMensaje(" Atencion","El vehiculo se saco del parqueo exitosamente");

        //System.out.println("Imprimiendo la Factura. Espere un momento por favor...");
        //MiMensaje.MuestraMensaje("Atencion","Imprimiendo la Factura. Espere un momento por favor...");
        ImprimePdf.imprime(imprime,"Factura");

     }
    
}

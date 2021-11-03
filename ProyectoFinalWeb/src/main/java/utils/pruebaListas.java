/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;


/**
 *
 * @author mack
 */
public class pruebaListas {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // TODO code application logic here

        /*
        String prueba[][] = new String[1][5];
        ArrayList<String> lista;
        
        prueba[0][0] = "1,1";
        prueba[0][1] = "1,2";
        prueba[0][2] = "1,3";
        prueba[0][3] = "1,4";
        prueba[0][4] = "1,5";
        
        lista.add(e);
        */
        
        int fila = 2;
        int columna = 5;

        String encabezado[][] = new String[1][columna];
        encabezado[0][0] = "ENCABEZADO 1";
        encabezado[0][1] = "ENCABEZADO 2";
        encabezado[0][2] = "ENCABEZADO 3";
        encabezado[0][3] = "ENCABEZADO 4";
        encabezado[0][4] = "ENCABEZADO 5";
        
        
        String detalle[][] = new String[fila][columna];
   
        for(int i=0;i<fila;i++){
            
            for(int j=0;j<columna;j++){
                
                detalle[i][j] = "DETALLE ["+String.valueOf(i)+"]]"+String.valueOf(j)+"]";
            }
            
        }

        ImprimePdfV2.imprime(encabezado, detalle, "REPORTE PRUEBA", "prueba");

        /*
        String detalle[][] = new String[fila][columna];
        
        for(int i=0;i<fila;i++){
            
            for(int j=0;j<columna;j++){
                
                detalle[i][j] = "linea ["+String.valueOf(i)+"]]"+String.valueOf(j)+"]";
            }
            
        }
        */

        /*
        int fila1 = detalle[0].length;
        int col1 = detalle[1].length;
        
        System.out.println("tamaño: "+detalle.length);
        System.out.println("tamaño: "+detalle[0].length);
        */
        
        /*
        for(int i=0;i<fila;i++){
            
            for(int j=0;j<columna;j++){

                System.out.println("valor es: "+detalle[i][j]);

            }
            
        }
        */

    }
}

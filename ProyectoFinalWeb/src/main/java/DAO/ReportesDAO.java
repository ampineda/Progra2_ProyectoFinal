/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DataBaseAccess;
import utils.ImprimePdfV2;

/**
 *
 * @author mack
 */
public class ReportesDAO {
    
    public String imprimeReporte1(){
        String error = "";

        ResultSet rset = null;
        int retorno = 0;
        int rentados = 0;
        
        int filas = 0;
        int columnas = 5;
        
        
        try{
            
            // juegos rentados 
            PreparedStatement stmt1 = DataBaseAccess.conn.prepareStatement(
              "SELECT a.idjuego, a.titulo, a.genero, a.plataforma, "
            + "if(ifnull(b.cantidad,0)>0,'RENTADO   ','DISPONIBLE') AS estado "
            + " FROM juegos a LEFT JOIN rentas b ON a.idjuego = b.juegoref AND b.fecha_recibido IS NULL");

            // stmt1.setString(1,idJuego);

            rset = stmt1.executeQuery();
            
            if(rset.last()){//Nos posicionamos al final
                filas = rset.getRow();//sacamos la cantidad de filas/registros
                rset.beforeFirst();//nos posicionamos antes del inicio (como viene por defecto)
            }   

            if (filas > 0) {
                
                String encabezado[][] = new String[1][columnas];
                String detalle[][] = new String[filas][columnas];

                encabezado[0][0] = "JUEGO";
                encabezado[0][1] = "NOMBRE";
                encabezado[0][2] = "GENERO";
                encabezado[0][3] = "PLATAFORMA";
                encabezado[0][4] = "ESTADO";
                
                int i = 0;
                
                while (rset.next()) {

                    detalle[i][0] = rset.getString("idjuego");
                    detalle[i][1] = rset.getString("titulo");
                    detalle[i][2] = rset.getString("genero");
                    detalle[i][3] = rset.getString("plataforma");
                    detalle[i][4] = rset.getString("estado");
                    
                    i = i + 1;            
                }
                
                ImprimePdfV2.imprime(encabezado, detalle, "REPORTE DE ESTADO DE JUEGOS", "estadojuegos");
                
                error = "OK impresion 1";
            }
            

        }catch(SQLException ex){
            // System.out.println("Exception: = "+ex.getMessage());
            error = ex.getMessage();
        }

        return error;
    
    }
    
    // reportes 2
    public String imprimeReporte2(){

        String error = "";

        ResultSet rset = null;
        int retorno = 0;
        int rentados = 0;
        
        int filas = 0;
        int columnas = 8;
        
        
        try{
            
            // juegos rentados 
            PreparedStatement stmt1 = DataBaseAccess.conn.prepareStatement(
               "SELECT a.juegoref, b.titulo, a.clienteref, c.nombre, a.fecha_entregar, "
             + "a.extensiones_otorgadas,  if(CURDATE()>a.fecha_entregar,"
             + "TIMESTAMPDIFF(DAY,fecha_entregar,CURDATE()),0) AS dias_vencidos,"
             + " (if(CURDATE()>a.fecha_entregar,"
             + "TIMESTAMPDIFF(DAY,fecha_entregar,CURDATE()),0)*5) AS multa_calculada "
             + "FROM rentas a "
             + "JOIN juegos b ON b.idjuego = a.juegoref "
             + "JOIN clientes c ON c.idcliente = a.clienteref  "
             + "WHERE fecha_recibido IS NULL"     
               );

            // stmt1.setString(1,idJuego);

            rset = stmt1.executeQuery();
            
            if(rset.last()){//Nos posicionamos al final
                filas = rset.getRow();//sacamos la cantidad de filas/registros
                rset.beforeFirst();//nos posicionamos antes del inicio (como viene por defecto)
            }   

            if (filas > 0) {
                
                String encabezado[][] = new String[1][columnas];
                String detalle[][] = new String[filas][columnas];

                encabezado[0][0] = "JUEGO";
                encabezado[0][1] = "NOMBRE";
                encabezado[0][2] = "CLIENTE";
                encabezado[0][3] = "NOMBRE";
                encabezado[0][4] = "FECHA ENTREGA";
                encabezado[0][5] = "EXTENSIONES";
                encabezado[0][6] = "DIAS VENCIDOS";
                encabezado[0][7] = "MULTA CALCULADA";
                
                int i = 0;
                
                while (rset.next()) {

                    detalle[i][0] = rset.getString("juegoref");
                    detalle[i][1] = rset.getString("titulo");
                    detalle[i][2] = rset.getString("clienteref");
                    detalle[i][3] = rset.getString("nombre");
                    detalle[i][4] = rset.getString("fecha_entregar");
                    detalle[i][5] = rset.getString("extensiones_otorgadas");
                    detalle[i][6] = rset.getString("dias_vencidos");
                    detalle[i][7] = rset.getString("multa_calculada");
                    
                    i = i + 1;            
                }
                
                ImprimePdfV2.imprime(encabezado, detalle, "REPORTE PRESTAMOS REALIZADOS", "listaprestamos");
                
                error = "OK impresion 2";
            }
            

        }catch(SQLException ex){
            // System.out.println("Exception: = "+ex.getMessage());
            error = ex.getMessage();
        }

        return error;

    }


    public String imprimeReporte3(){
        
        String error = "";

        ResultSet rset = null;
        int retorno = 0;
        int rentados = 0;
        
        int filas = 0;
        int columnas = 4;
        
        
        try{
            
            // juegos rentados 
            PreparedStatement stmt1 = DataBaseAccess.conn.prepareStatement(
                "SELECT a.clienteref, b.nombre, SUM(multa) AS multas_cobradas, "
              + "(if(CURDATE()>a.fecha_entregar,"
              + "TIMESTAMPDIFF(DAY,fecha_entregar,CURDATE()),0)*5) AS multas_proyectadas "
              + "FROM rentas a "
              + "join clientes b ON a.clienteref = b.idcliente "
              + "group BY a.clienteref order BY multas_cobradas desc, multas_proyectadas desc"
            );

            // stmt1.setString(1,idJuego);

            rset = stmt1.executeQuery();
            
            if(rset.last()){//Nos posicionamos al final
                filas = rset.getRow();//sacamos la cantidad de filas/registros
                rset.beforeFirst();//nos posicionamos antes del inicio (como viene por defecto)
            }   

            if (filas > 0) {
                
                String encabezado[][] = new String[1][columnas];
                String detalle[][] = new String[filas][columnas];

                encabezado[0][0] = "CLIENTE";
                encabezado[0][1] = "NOMBRE";
                encabezado[0][2] = "TOTAL MULTAS COBRADAS";
                encabezado[0][3] = "TOTAL MULTRAS PROYECTADAS";
                
                int i = 0;
                
                while (rset.next()) {

                    detalle[i][0] = rset.getString("clienteref");
                    detalle[i][1] = rset.getString("nombre");
                    detalle[i][2] = rset.getString("multas_cobradas");
                    detalle[i][3] = rset.getString("multas_proyectadas");
                    
                    i = i + 1;            
                }
                
                ImprimePdfV2.imprime(encabezado, detalle, "REPORTE DE USUARIOS CON MAS MULTAS", "listamultas");
                
                error = "OK impresion 3";
            }
            

        }catch(SQLException ex){
            // System.out.println("Exception: = "+ex.getMessage());
            error = ex.getMessage();
        }

        return error;
    }


    public String imprimeReporte4(){

        String error = "";

                ResultSet rset = null;
        int retorno = 0;
        int rentados = 0;
        
        int filas = 0;
        int columnas = 4;
        
        
        try{
            
            // juegos rentados 
            PreparedStatement stmt1 = DataBaseAccess.conn.prepareStatement(
                "SELECT a.juegoref, b.titulo, COUNT(a.juegoref) AS prestamos, "
              + "CONCAT(MONTHNAME(fecha),'-',EXTRACT(year from fecha)) AS mes "
              + "FROM rentas a "
              + "JOIN juegos b ON b.idjuego = a.juegoref "
              + "GROUP BY a.juegoref ORDER BY prestamos DESC, a.juegoref"
              );

            // stmt1.setString(1,idJuego);

            rset = stmt1.executeQuery();
            
            if(rset.last()){//Nos posicionamos al final
                filas = rset.getRow();//sacamos la cantidad de filas/registros
                rset.beforeFirst();//nos posicionamos antes del inicio (como viene por defecto)
            }   

            if (filas > 0) {
                
                String encabezado[][] = new String[1][columnas];
                String detalle[][] = new String[filas][columnas];

                encabezado[0][0] = "MES";
                encabezado[0][1] = "JUEGO";
                encabezado[0][2] = "NOMBRE";
                encabezado[0][3] = "TOTAL PRESTAMOS";
                
                int i = 0;
                
                while (rset.next()) {

                    detalle[i][0] = rset.getString("mes");
                    detalle[i][1] = rset.getString("juegoref");
                    detalle[i][2] = rset.getString("titulo");
                    detalle[i][3] = rset.getString("prestamos");
                    
                    i = i + 1;            
                }
                
                ImprimePdfV2.imprime(encabezado, detalle, "REPORTE DE JUEGOS MAS PRESTADOS", "listamasprestados");
                
                error = "OK impresion 4";
            }
            

        }catch(SQLException ex){
            // System.out.println("Exception: = "+ex.getMessage());
            error = ex.getMessage();
        }

        
        
        return error;

    }

        
}

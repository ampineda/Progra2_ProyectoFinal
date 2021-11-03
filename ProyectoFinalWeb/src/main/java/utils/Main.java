package utils;

import DAO.ClientesDAO;
import DAO.JuegosDAO;
import DAO.PlatosDAO;
import DAO.RentaDAO;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        try{

            // ClientesDAO cliente = new ClientesDAO();

            // crear
            //cliente.crear(new Object());

            //cliente.crear("10","Prueba por codigo","ciudad","1234","789");

            // actualizar
            // cliente.actualizar(new Object());
            // cliente.actualizar("10","Prueba actualizado","ciudad 2","12345","78991");


            // eliminar
            // cliente.eliminar(new Object());
            // cliente.eliminar("10");

            // cliente.leer();
            
            RentaDAO juego = new RentaDAO();
            juego.crear("1", new Date().toString(), "100", "xx");

            // juego.leer();         

        }catch(Exception e){
            System.out.println("Error: "+ e.getMessage());
        }

    }
}

package DAO;

import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import java.sql.Date;
import utils.DataBaseAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RentaDAO implements DataBaseDAO{

    public String crear(String idSolicitud, String fecha, String idCliente,String idJuego){

        int nretorno = 0;
        double precio = 0.0;
        String error = "";
        boolean clienteExiste = false;
        boolean clienteDevolucionPendiente = false;
        int existenciaJuego = 0;
        
        try{

            
            // validaciones 
            // hay que validar que el articulo exista 
            // validar que el cliente exista 
            // validar que el cliente que tenga prestamos pendientes de devolucion no puede prestar
            JuegosDAO juego = new JuegosDAO();
            precio = juego.buscaJuego(idJuego) ;

            ClientesDAO cliente = new ClientesDAO();
            clienteExiste = cliente.buscaCliente(idCliente);
            
            // el articulo existe y seguimos el proceso
            if(precio >= 0.00){
                
                if(clienteExiste){

                    existenciaJuego = juego.validaExistenciaJuego(idJuego);
                    
                    if(existenciaJuego > 0){

                        clienteDevolucionPendiente = cliente.validaRentasPendientes(idCliente);
                
                        if(clienteDevolucionPendiente){

                            error = "El cliente tiene un prestamo pendiente de devolver";

                        } else {

                            PreparedStatement stmt = DataBaseAccess.conn.prepareStatement("insert into rentas "
                            + " (solicitud, fecha, clienteref, juegoref, cantidad, precio, fecha_entregar) "
                            + " values (?,?,?,?,?,?,TIMESTAMPADD(day,7,?))");
            
                            stmt.setString(1,idSolicitud);
                            stmt.setDate(2,Date.valueOf(fecha));
                            stmt.setString(3,idCliente);
                            stmt.setString(4,idJuego);
                            stmt.setInt(5,1);
                            stmt.setDouble(6,precio);
                            stmt.setDate(7,Date.valueOf(fecha));

                            nretorno = stmt.executeUpdate();
                        
                            error = "Grabacion OK";
                        }

                    } else {
                        error = "El juego no cuenta con existencia";
                        
                    }
                    
                } else {
                    error = "El cliente no existe en la base de datos";
                }
                
                
            } else{
                error = "El juego no existe en la base de datos";
            }  
            
            
            System.out.println("numero de filas insertadas: "+nretorno);

        } catch (Exception e){
            System.out.println("Error de creacion: "+e.getMessage());
            error = e.getMessage();
        }
        
        return error;

    }




    public void actualizar(String idCliente, String nombreCliente, String direccionCliente,String dpiCliente, String telefonoCliente) {

        int nretorno = 0;

        try{

            PreparedStatement stmt = DataBaseAccess.conn.prepareStatement(
                    "update Clientes set nombre=?,direccion=?,dpi=?,telefono=? where idcliente=?");

            stmt.setString(1,nombreCliente);
            stmt.setString(2,direccionCliente);
            stmt.setInt(3,Integer.parseInt(dpiCliente));
            stmt.setInt(4,Integer.parseInt(telefonoCliente));
            stmt.setString(5,idCliente);

            nretorno = stmt.executeUpdate();

            System.out.println("numero de filas actualizadas: "+nretorno);

        } catch (Exception e){
            System.out.println("Error de actualizacion: "+e.getMessage());
        }

    }

    public void eliminar(String idCliente) {

        int nretorno = 0;

        try{

            PreparedStatement stmt = DataBaseAccess.conn.prepareStatement("delete from Clientes where idcliente=?");
            stmt.setString(1,idCliente);

            nretorno = stmt.executeUpdate();

            System.out.println("numero de filas borradas: "+nretorno);

        } catch (Exception e){
            System.out.println("Error de eliminacion: "+e.getMessage());
        }

    }



    @Override
    public ResultSet leer() {

        ResultSet rset = null;

        try{
            Statement stmt = DataBaseAccess.conn.createStatement();
            String sql = "Select * from clientes";
            rset = stmt.executeQuery(sql);

            while(rset.next()){
                System.out.println(rset.getInt("idCliente")+"  "+
                        rset.getString("nombre")+"  "+
                        rset.getString("direccion")+"  "+
                        rset.getString("dpi")+"  "+
                        rset.getString("telefono") );
            }

        }catch(SQLException ex){
            System.out.println("Exception: = "+ex.getMessage());
        }


        return null;
    }

    // por objetos
    @Override
    public void crear(Object data) {

        int nretorno = 0;

        try{

            Statement stmt = DataBaseAccess.conn.createStatement();
            String sql = "insert into clientes values ('1','Cliente Prueba','ciudad','15645','6548')";
            nretorno = stmt.executeUpdate(sql);
            System.out.println("numero de filas afectadas: "+nretorno);

        } catch (Exception e){
            System.out.println("Error de creacion: "+e.getMessage());
        }


    }

    @Override
    public void actualizar(Object data) {

        int nretorno = 0;

        try{

            Statement stmt = DataBaseAccess.conn.createStatement();
            String sql = "update Clientes set nombre = 'Cliente Prueba Actu' where idcliente = '1'";
            nretorno = stmt.executeUpdate(sql);
            System.out.println("numero de filas actualizadas: "+nretorno);

        } catch (Exception e){
            System.out.println("Error de actualizacion: "+e.getMessage());
        }

    }

    @Override
    public void eliminar(Object data) {

        int nretorno = 0;

        try{

            Statement stmt = DataBaseAccess.conn.createStatement();
            String sql = "delete from Clientes where idcliente = '1'";
            nretorno = stmt.executeUpdate(sql);
            System.out.println("numero de filas afectadas: "+nretorno);

        } catch (Exception e){
            System.out.println("Error de eliminacion: "+e.getMessage());
        }

    }

    
    /// actualiza la fecha de entrega con la extension 
    public String extiendeFechaEntrega(String idSolicitud, String idCliente, String idJuego){

        int nretorno = 0;
        double precio = 0.0;
        String error = "";
        boolean clienteExiste = false;
        boolean clienteMaximoMultas = false;
        boolean clientePuedeExtender = false;
        int existenciaJuego = 0;
        
        try{

            
            // validaciones 
            // hay que validar que el articulo exista 
            // validar que el cliente exista 
            // validar que el cliente que tenga prestamos pendientes de devolucion no puede prestar
            JuegosDAO juego = new JuegosDAO();
            precio = juego.buscaJuego(idJuego) ;

            ClientesDAO cliente = new ClientesDAO();
            clienteExiste = cliente.buscaCliente(idCliente);
            
            // el articulo existe y seguimos el proceso
            if(precio >= 0.00){
                
                if(clienteExiste){

                    clienteMaximoMultas = cliente.validaMaximoMultas(idCliente);
                
                    if(clienteMaximoMultas){

                        error = "El cliente tiene2 multas, no se puede extender";

                    } else {

                        /// validamos que tenga extensiones disponibles 
                        clientePuedeExtender = validaExtenderEntrega(idSolicitud,idCliente,idJuego);
                            
                        if (clientePuedeExtender){
                            
                            PreparedStatement stmt = DataBaseAccess.conn.prepareStatement("update rentas "
                                + " set fecha_entregar = TIMESTAMPADD(day,3,fecha_entregar), "
                                + " extensiones_otorgadas = (extensiones_otorgadas+1) "    
                                + " where solicitud=? and clienteref=? and juegoref=?");
            
                            stmt.setString(1,idSolicitud);
                            stmt.setString(2,idCliente);
                            stmt.setString(3,idJuego);

                            nretorno = stmt.executeUpdate();
                        
                            error = "Grabacion OK";
                                
                        } else {
                            
                            error = "El cliente alcanzo el maximo de extensiones y no se puede extender";
                                
                        }
                            
                    }

                    
                } else {
                    error = "El cliente no existe en la base de datos";
                }
                
                
            } else{
                error = "El juego no existe en la base de datos";
            }  
            
            
            System.out.println("numero de filas insertadas: "+nretorno);

        } catch (Exception e){
            System.out.println("Error de creacion: "+e.getMessage());
            error = e.getMessage();
        }
        
        return error;

    }


    /// actualiza la fecha de entrega con la extension 
    public boolean validaExtenderEntrega(String idSolicitud, String idCliente, String idJuego){

        boolean clientePuedeExtender = false;
        int nretorno = 0;
        ResultSet rset = null;
        int dias = 0;    
        
        try{

            PreparedStatement stmt = DataBaseAccess.conn.prepareStatement(
                    "select TIMESTAMPDIFF(DAY,fecha,fecha_entregar) as difdias from rentas "
                  + " where solicitud=? and clienteref=? and juegoref=?");
            
                stmt.setString(1,idSolicitud);
                stmt.setString(2,idCliente);
                stmt.setString(3,idJuego);

                rset = stmt.executeQuery();
            
            if(rset.next()){
                
                dias = rset.getInt("difdias");
                
                if (dias<13){
                    clientePuedeExtender = true;
                }

            }

        } catch (Exception e){
            System.out.println("Error de creacion: "+e.getMessage());
        }
        
        return clientePuedeExtender;

    }
    
    

}

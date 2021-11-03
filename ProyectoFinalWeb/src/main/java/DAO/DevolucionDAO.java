package DAO;

import java.sql.Date;
import utils.DataBaseAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DevolucionDAO implements DataBaseDAO{

        /// actualiza la fecha de entrega con la extension 
    public String crear(String idSolicitud, String idCliente, String idJuego){

        int nretorno = 0;
        double precio = 0.0;
        String error = "";
        boolean clienteExiste = false;
        boolean clienteLimiteMultas = false;
        boolean clienteLimiteExtension = false;
        int existenciaJuego = 0;
        
        int nDiasMulta = 0;
        double nTotalMulta = 0.00;
        ResultSet rset = null;
        
        int nDiasRenta = 0;
        
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
                    
                    nDiasRenta = diasRentadoJuego(idSolicitud, idCliente, idJuego);
                    
                    if (nDiasRenta >= 0) {
                      error = "";    
                      
                      // si los dias de renta son mayor a 7 se cobra multa de Q5 por cada dia de retrazo
                      if (nDiasRenta > 0 ){
                          
                          nDiasMulta = nDiasRenta;
                          nTotalMulta = nDiasMulta * 5;
                          
                      }
                      
                      
                      // se actualiza la devolucion del juego rentado 
                      PreparedStatement stmt1 = DataBaseAccess.conn.prepareStatement(
                         "update rentas set fecha_recibido = CURDATE(), multa =?, "
                                 + "        total = (precio+?) "
                       + "where solicitud =? and clienteref =? and juegoref =?"
                       );

                      /*
                                               "update rentas set fecha_recibido = CURDATE(), multa =?, "
                                 + "        total = (precio+?), extensiones_otorgadas = (extensiones_otorgadas+?) "
                       + "where solicitud =? AND clienteref =? AND juegoref =?"

                      */
                      
                       stmt1.setDouble(1,nTotalMulta);
                       stmt1.setDouble(2,nTotalMulta);
                       stmt1.setString(3,idSolicitud);
                       stmt1.setString(4,idCliente);
                       stmt1.setString(5,idJuego);
            
                       nretorno = stmt1.executeUpdate();
            
                       error = "OK grabacion";
                       
                    } else if(nDiasRenta == -1) {
                        error = "El videojuego para esta solicitud ya fue devuelto";
                        
                    } else {
                        error = "No se encontro la solicitud en la base de datos";
                        
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

    
    /*
    public void crear(String idCliente, String nombreCliente, String direccionCliente,String dpiCliente, String telefonoCliente){

        int nretorno = 0;

        try{

            PreparedStatement stmt = DataBaseAccess.conn.prepareStatement("insert into Clientes values (?,?,?,?,?)");
            stmt.setString(1,idCliente);
            stmt.setString(2,nombreCliente);
            stmt.setString(3,direccionCliente);
            stmt.setInt(4,Integer.parseInt(dpiCliente));
            stmt.setInt(5,Integer.parseInt(telefonoCliente));

            nretorno = stmt.executeUpdate();
            System.out.println("numero de filas insertadas: "+nretorno);

        } catch (Exception e){
            System.out.println("Error de creacion: "+e.getMessage());
        }

    }
    */
    


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
            String sql = "insert into Clientes values ('1','Cliente Prueba','ciudad','15645','6548')";
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
    public int diasRentadoJuego(String idSolicitud, String idCliente, String idJuego){

        ResultSet rset = null;
        int retorno = 0;
        int rentados = 0;
        Date fechaDevuelto = null;
        
        try{
            
            //Statement stmt = DataBaseAccess.conn.createStatement();
            //rset = stmt.executeQuery(sql);

            /*
            PreparedStatement stmt = DataBaseAccess.conn.prepareStatement(
            "SELECT a.IdJuego, a.titulo, (a.unidades-sum(b.cantidad)) AS existencia "
          + "FROM juegos a JOIN rentas b ON b.juegoref = a.IdJuego WHERE fecha_recibido IS NULL AND clienteref =?"
            );
            */
            
            // juegos rentados 
            PreparedStatement stmt1 = DataBaseAccess.conn.prepareStatement(
               "select fecha_entregar, CURDATE() AS fecha_actual, fecha_recibido, "
             + "TIMESTAMPDIFF(DAY, fecha_entregar,CURDATE()) AS diasrenta  "
             + "from rentas where solicitud =? and clienteref =? and juegoref =?"
            );

            stmt1.setString(1,idSolicitud);
            stmt1.setString(2,idCliente);
            stmt1.setString(3,idJuego);
            
            rset = stmt1.executeQuery();
            
            if(rset.next()){
                
                fechaDevuelto = rset.getDate("fecha_recibido");
                retorno = rset.getInt("diasrenta");
                
                if (fechaDevuelto!= null){
                  retorno = -1;
                }
                
            }else{
                retorno = -2;
            }

        }catch(SQLException ex){
            System.out.println("Exception: = "+ex.getMessage());
        }


        return retorno;
        
    }

    
    
}

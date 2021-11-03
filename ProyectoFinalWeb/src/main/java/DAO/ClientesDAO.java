package DAO;

import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import utils.DataBaseAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientesDAO implements DataBaseDAO{

    public String crear(String idCliente, String nombreCliente, String direccionCliente,String dpiCliente, String telefonoCliente, String emailCliente){

        int nretorno = 0;
        String cretorno = "";

        try{

            PreparedStatement stmt = DataBaseAccess.conn.prepareStatement("insert into clientes (nombre,direccion,dpi,telefono,email) values (?,?,?,?,?)");
            
            // stmt.setString(1,idCliente);
            
            stmt.setString(1,nombreCliente);
            stmt.setString(2,direccionCliente);
            stmt.setInt(3,Integer.parseInt(dpiCliente));
            stmt.setInt(4,Integer.parseInt(telefonoCliente));
            stmt.setString(5,emailCliente);

            nretorno = stmt.executeUpdate();
            System.out.println("numero de filas insertadas: "+nretorno);
            
            cretorno = "OK grabacion";

        } catch (Exception e){
            System.out.println("Error de creacion: "+e.getMessage());
            cretorno = e.getMessage();            
        }

        return cretorno;

    }




    public void actualizar(String idCliente, String nombreCliente, String direccionCliente,String dpiCliente, String telefonoCliente, String emailCliente) {

        int nretorno = 0;

        try{

            PreparedStatement stmt = DataBaseAccess.conn.prepareStatement(
                                 "update clientes set nombre=?,direccion=?,dpi=?,telefono=? where idcliente=?");

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

            PreparedStatement stmt = DataBaseAccess.conn.prepareStatement("delete from clientes where idcliente=?");
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
            String sql = "select * from clientes";
            rset = stmt.executeQuery(sql);

            while(rset.next()){
                System.out.println(rset.getInt("idcliente")+"  "+
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
            String sql = "update clientes set nombre = 'cliente Prueba Actu' where idcliente = '1'";
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
            String sql = "delete from clientes where idcliente = '1'";
            nretorno = stmt.executeUpdate(sql);
            System.out.println("numero de filas afectadas: "+nretorno);

        } catch (Exception e){
            System.out.println("Error de eliminacion: "+e.getMessage());
        }

    }


    public boolean buscaCliente(String idCliente) {

        ResultSet rset = null;
        String nombreCliente = "";
        boolean retorno = false;
        
        try{
            
            //Statement stmt = DataBaseAccess.conn.createStatement();
            //rset = stmt.executeQuery(sql);

            PreparedStatement stmt = DataBaseAccess.conn.prepareStatement("select nombre from clientes where idcliente = ?");
            stmt.setString(1,idCliente);

            rset = stmt.executeQuery();
            
            if(rset.next()){
                nombreCliente = rset.getString("nombre");
                retorno = true;
            }
            
            // System.out.println("el precio es: "+retorno);

        }catch(SQLException ex){
            System.out.println("Exception: = "+ex.getMessage());
        }


        return retorno;
    }

    public boolean validaRentasPendientes(String idCliente) {

        ResultSet rset = null;
        String fechaRecibido = "";
        boolean retorno = false;
        
        try{
            
            //Statement stmt = DataBaseAccess.conn.createStatement();
            //rset = stmt.executeQuery(sql);

            PreparedStatement stmt = DataBaseAccess.conn.prepareStatement(
                    "select juegoref from rentas where fecha_recibido IS null and clienteref =?");
            stmt.setString(1,idCliente);

            rset = stmt.executeQuery();
            
            if(rset.next()){
                fechaRecibido = rset.getString("juegoref");
                retorno = true;
            }
            
            // System.out.println("el precio es: "+retorno);

        }catch(SQLException ex){
            System.out.println("Exception: = "+ex.getMessage());
        }


        return retorno;
    }
    

    public boolean validaMaximoMultas(String idCliente) {

        ResultSet rset = null;
        int numeromultas = 0;
        boolean retorno = false;
        
        try{
            
            //Statement stmt = DataBaseAccess.conn.createStatement();
            //rset = stmt.executeQuery(sql);

            PreparedStatement stmt = DataBaseAccess.conn.prepareStatement(
                    "select count(clienteref) AS nummultas from rentas where multa > 0 and clienteref =? group by clienteref order by clienteref");
            stmt.setString(1,idCliente);

            rset = stmt.executeQuery();
            
            if(rset.next()){

                numeromultas = rset.getInt("nummultas");
                
                if (numeromultas >=2){
                    retorno = true;
                }
                
            }
            
            // System.out.println("el precio es: "+retorno);

        }catch(SQLException ex){
            System.out.println("Exception: = "+ex.getMessage());
        }


        return retorno;
    }


    
}

package DAO;

import utils.DataBaseAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class JuegosDAO implements DataBaseDAO{

    public String crear(String idJuego, String titulo, String Genero,String unidades, String plataforma, String precio){

        int nretorno = 0;
        String cretorno = "";

        try{

            PreparedStatement stmt = DataBaseAccess.conn.prepareStatement("insert into juegos values (?,?,?,?,?,?)");
            stmt.setString(1,idJuego);
            stmt.setString(2,titulo);
            stmt.setString(3,Genero);
            stmt.setInt(4,Integer.parseInt(unidades));
            stmt.setString(5,plataforma);
            stmt.setInt(6,Integer.parseInt(precio));

            nretorno = stmt.executeUpdate();
            System.out.println("numero de filas insertadas: "+nretorno);
            cretorno = "OK grabacion";

        } catch (Exception e){
            System.out.println("Error de creacion: "+e.getMessage());
            cretorno = e.getMessage();            
        }

        return cretorno;
        
    }




    public void actualizar(String idJuego, String titulo, String Genero,String unidades, String plataforma, String precio) {

        int nretorno = 0;

        try{

            PreparedStatement stmt = DataBaseAccess.conn.prepareStatement(
                    "update juegos set titulo=?,genero=?,unidades=?,plataforma=?,precio=? where idJuego=?");

            stmt.setString(1,titulo);
            stmt.setString(2,Genero);
            stmt.setInt(3,Integer.parseInt(unidades));
            stmt.setInt(4,Integer.parseInt(plataforma));
            stmt.setInt(5,Integer.parseInt(precio));
            stmt.setString(6,idJuego);


            nretorno = stmt.executeUpdate();

            System.out.println("numero de filas actualizadas: "+nretorno);

        } catch (Exception e){
            System.out.println("Error de actualizacion: "+e.getMessage());
        }

    }

    public void eliminar(String idJuego) {

        int nretorno = 0;

        try{

            PreparedStatement stmt = DataBaseAccess.conn.prepareStatement("delete from juegos where idJuego=?");
            stmt.setString(1,idJuego);

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
            String sql = "Select * from juegos";
            rset = stmt.executeQuery(sql);

            while(rset.next()){
                System.out.println(rset.getInt("idJuego")+"  "+
                        rset.getString("titulo")+"  "+
                        rset.getString("genero")+"  "+
                        rset.getString("unidades")+"  "+
                        rset.getString("plataforma")+"   "+
                        rset.getString("precio") );
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
            String sql = "insert into juegos values ('1','Cliente Prueba','ciudad','15645','6548')";
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
            String sql = "update juegos set nombre = 'Cliente Prueba Actu' where idJuego = '1'";
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
            String sql = "delete from juegos where idJuego = '1'";
            nretorno = stmt.executeUpdate(sql);
            System.out.println("numero de filas afectadas: "+nretorno);

        } catch (Exception e){
            System.out.println("Error de eliminacion: "+e.getMessage());
        }

    }

    
    public double buscaJuego(String idJuego) {

        ResultSet rset = null;
        double retorno = 0.00;
        
        try{
            
            //Statement stmt = DataBaseAccess.conn.createStatement();
            //rset = stmt.executeQuery(sql);

            PreparedStatement stmt = DataBaseAccess.conn.prepareStatement("Select precio from juegos where idjuego = ?");
            stmt.setString(1,idJuego);

            rset = stmt.executeQuery();
            
            if(rset.next()){
                retorno = rset.getDouble("precio");
            }else{
                retorno = -1.0;
            }
            
            // System.out.println("el precio es: "+retorno);

        }catch(SQLException ex){
            System.out.println("Exception: = "+ex.getMessage());
        }


        return retorno;
    }


    public int validaExistenciaJuego(String idJuego) {

        ResultSet rset = null;
        int retorno = 0;
        int rentados = 0;
        
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
            "SELECT SUM(cantidad) AS rentados FROM rentas WHERE fecha_recibido IS NULL and juegoref =?"
            );

            stmt1.setString(1,idJuego);

            rset = stmt1.executeQuery();
            
            if(rset.next()){
                
                rentados = rset.getInt("rentados");
                
                retorno = 1 - rentados;
                
            }else{
                retorno = -1;
            }
            

            /*    
            // inventario de juegos 
            // juegos rentados 
            PreparedStatement stmt2 = DataBaseAccess.conn.prepareStatement(
            "SELECT unidades AS inventario FROM juegos WHERE idjuego =?"
            );

            stmt2.setString(1,idJuego);

            rset = stmt2.executeQuery();
            
            if(rset.next()){
                retorno = rset.getInt("rentados");
            }else{
                retorno = -1;
            }
            */

            
            // System.out.println("el precio es: "+retorno);

        }catch(SQLException ex){
            System.out.println("Exception: = "+ex.getMessage());
        }


        return retorno;
    }

    
}

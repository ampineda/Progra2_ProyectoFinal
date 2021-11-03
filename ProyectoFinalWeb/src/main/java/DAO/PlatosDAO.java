package DAO;

import utils.DataBaseAccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PlatosDAO implements DbDAO{

    @Override
    public ResultSet getAll() {
        ResultSet rset = null;

        try{
            Statement stmt = DataBaseAccess.conn.createStatement();
            String sql = "Select * from platos";
            rset = stmt.executeQuery(sql);

            while(rset.next()){
                System.out.println(rset.getInt("id_platos")+"  "+rset.getString("nombre_platos"));
            }

        }catch(SQLException ex){
            System.out.println("Exception: = "+ex.getMessage());
        }


        return null;
    }

    @Override
    public void save(Object data) {

    }
}

package DAO;

import java.sql.ResultSet;

public interface DataBaseDAO {

    public void crear(Object data);
    public void actualizar(Object data);
    public void eliminar(Object data);
    public ResultSet leer();

}

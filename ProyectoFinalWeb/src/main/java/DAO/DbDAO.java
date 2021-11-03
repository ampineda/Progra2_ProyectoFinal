package DAO;

import java.sql.ResultSet;

public interface DbDAO {
    public ResultSet getAll();
    public void save(Object data);
}

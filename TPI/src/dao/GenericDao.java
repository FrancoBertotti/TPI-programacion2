package dao;

import config.DatabaseConnection;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> {

    public void agregar(Connection conn, T entity) throws SQLException;
    public T leer(Connection conn, int id) throws SQLException;
    List<T> leerTodos(Connection conn) throws SQLException;
    void actualizar(Connection conn, int id) throws SQLException;
    void eliminar(Connection conn, int id) throws SQLException;

}

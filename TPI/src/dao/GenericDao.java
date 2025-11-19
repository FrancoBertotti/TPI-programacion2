package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> {

    public void agregar(Connection conn, T entity) throws SQLException;

    public T leer(Connection conn, long id) throws SQLException;

    List<T> leerTodos(Connection conn) throws SQLException;

    int actualizar(Connection conn, T entity) throws SQLException;

    int eliminar(Connection conn, long id) throws SQLException;

}

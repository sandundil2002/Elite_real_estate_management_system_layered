package lk.ijse.elite.dao.custom;

import lk.ijse.elite.dao.SuperDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO {
    ArrayList<T> getAll() throws SQLException,ClassNotFoundException;

    boolean save(T dto) throws SQLException,ClassNotFoundException;

    boolean update(T dto) throws SQLException,ClassNotFoundException;

    T search(String id) throws SQLException,ClassNotFoundException;

    void delete(String id) throws SQLException,ClassNotFoundException;

    String generateId() throws SQLException,ClassNotFoundException;
}

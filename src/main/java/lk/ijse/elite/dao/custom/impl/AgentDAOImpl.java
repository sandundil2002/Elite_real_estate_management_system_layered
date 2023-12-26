package lk.ijse.elite.dao.custom.impl;

import lk.ijse.elite.dao.custom.AgentDAO;
import lk.ijse.elite.entity.Agent;

import java.sql.SQLException;
import java.util.List;

public class AgentDAOImpl implements AgentDAO {
    @Override
    public List<Agent> loadAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Agent dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Agent dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Agent search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        return null;
    }
}

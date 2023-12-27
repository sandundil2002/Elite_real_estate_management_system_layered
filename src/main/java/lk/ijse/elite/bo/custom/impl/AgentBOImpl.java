package lk.ijse.elite.bo.custom.impl;

import lk.ijse.elite.bo.custom.AgentBO;
import lk.ijse.elite.model.dto.AgentDTO;

import java.sql.SQLException;
import java.util.List;

public class AgentBOImpl implements AgentBO {
    @Override
    public List<AgentDTO> loadAllAgent() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveAgent(AgentDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateAgent(AgentDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public AgentDTO searchAgent(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean deleteAgent(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateAgentId() throws SQLException, ClassNotFoundException {
        return null;
    }
}

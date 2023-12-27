package lk.ijse.elite.bo.custom;

import lk.ijse.elite.bo.SuperBO;
import lk.ijse.elite.model.dto.AgentDTO;

import java.sql.SQLException;
import java.util.List;

public interface AgentBO extends SuperBO {

    List<AgentDTO> loadAllAgent() throws SQLException,ClassNotFoundException;

    boolean saveAgent(AgentDTO dto) throws SQLException,ClassNotFoundException;

    boolean updateAgent(AgentDTO dto) throws SQLException,ClassNotFoundException;

    AgentDTO searchAgent(String id) throws SQLException,ClassNotFoundException;

    boolean deleteAgent(String id) throws SQLException,ClassNotFoundException;

    String generateAgentId() throws SQLException,ClassNotFoundException;
}

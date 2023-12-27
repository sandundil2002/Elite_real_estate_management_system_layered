package lk.ijse.elite.bo.custom.impl;

import lk.ijse.elite.bo.custom.AgentBO;
import lk.ijse.elite.dao.DAOFactory;
import lk.ijse.elite.dao.custom.AgentDAO;
import lk.ijse.elite.entity.Agent;
import lk.ijse.elite.model.dto.AgentDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AgentBOImpl implements AgentBO {
    AgentDAO agentDAO = (AgentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.AGENT);
    @Override
    public List<AgentDTO> loadAllAgent() throws SQLException, ClassNotFoundException {
        List<Agent> agents = agentDAO.loadAll();
        List<AgentDTO> agentDTOS = new ArrayList<>();
        for (Agent agent : agents){
            agentDTOS.add(new AgentDTO(agent.getAgent_id(),agent.getName(),agent.getAddress(),agent.getMobile(),agent.getEmail()));
        }
        return agentDTOS;
    }

    @Override
    public boolean saveAgent(AgentDTO dto) throws SQLException, ClassNotFoundException {
        return agentDAO.save(new Agent(dto.getAgent_id(),dto.getName(),dto.getAddress(),dto.getMobile(),dto.getEmail()));
    }

    @Override
    public boolean updateAgent(AgentDTO dto) throws SQLException, ClassNotFoundException {
        return agentDAO.update(new Agent(dto.getAgent_id(),dto.getName(),dto.getAddress(),dto.getMobile(),dto.getEmail()));
    }

    @Override
    public AgentDTO searchAgent(String id) throws SQLException, ClassNotFoundException {
        Agent agent = agentDAO.search(id);
        AgentDTO agentDTO = new AgentDTO(agent.getAgent_id(),agent.getName(),agent.getAddress(),agent.getMobile(),agent.getEmail());
        return agentDTO;
    }

    @Override
    public boolean deleteAgent(String id) throws SQLException, ClassNotFoundException {
        return agentDAO.delete(id);
    }

    @Override
    public String generateAgentId() throws SQLException, ClassNotFoundException {
        return agentDAO.generateId();
    }
}

package lk.ijse.elite.dao.custom.impl;

import lk.ijse.elite.dao.custom.AgentDAO;
import lk.ijse.elite.entity.Agent;
import lk.ijse.elite.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AgentDAOImpl implements AgentDAO {
    @Override
    public List<Agent> loadAll() throws SQLException{
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM agent");
        List<Agent> agentList = new ArrayList<>();

        while (resultSet.next()) {
            Agent agentDto = new Agent(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5));
            agentList.add(agentDto);
        }
        return agentList;
    }

    @Override
    public boolean save(Agent dto) throws SQLException{
        return SQLUtil.sql("INSERT INTO agent VALUES (?,?,?,?,?)",
                dto.getAgent_id(),
                dto.getName(),
                dto.getAddress(),
                dto.getMobile(),
                dto.getEmail());
    }

    @Override
    public boolean update(Agent dto) throws SQLException{
        return SQLUtil.sql("UPDATE agent SET Name = ?, Address = ?, Mobile = ?, Email = ? WHERE agent_id = ?",
                dto.getName(),
                dto.getAddress(),
                dto.getMobile(),
                dto.getEmail(),
                dto.getAgent_id());
    }

    @Override
    public Agent search(String id) throws SQLException{
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM agent WHERE agent_id = ?", id);
        if (resultSet.next()) {
            return new Agent(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5));
        }
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException{
        return SQLUtil.sql("DELETE FROM agent WHERE agent_id = ?", id);
    }

    @Override
    public String generateId() throws SQLException{
        ResultSet resultSet = SQLUtil.sql("SELECT agent_id FROM agent ORDER BY agent_id DESC LIMIT 1");
        if (resultSet.next()) {
            String id = resultSet.getString("agent_id");
            String numericPart = id.replaceAll("\\D", "");
            int newAgentId = Integer.parseInt(numericPart) + 1;
            return String.format("Agent%03d", newAgentId);
        } else {
            return "Agent001";
        }
    }
}
